package intergration;

import api.Api;
import core.*;
import domain.bottom.NoBottomListInDB;
import domain.bottom.NoBottomWithThatId;
import domain.item.NoItemWithThatId;
import domain.topping.NoToppingListInDB;
import domain.topping.NoToppingWithThatId;
import domain.user.InvalidPassword;
import domain.user.UserExists;
import domain.user.UserNotFound;
import infrastructure.DBexception;
import infrastructure.Database;
import infrastructure.LoginSampleException;
import infrastructure.dbbottoms.DBBottomRepository;
import infrastructure.dbitem.DBItemRepository;
import infrastructure.dborder.DBOrderFactory;
import infrastructure.dborder.DBOrderRepository;
import infrastructure.dborder.DBOrderServices;
import infrastructure.dbshipping.DBShippingFactory;
import infrastructure.dbtoppings.DBToppingRepository;
import infrastructure.dbuser.DBUserFactory;
import infrastructure.dbuser.DBUserRepository;
import infrastructure.dbuser.DBUserServices;
import infrastructure.local_cart.LocalCartFactory;
import infrastructure.local_cart.LocalCartRepository;
import infrastructure.local_cart.LocalCartServices;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

@Tag("intergration-test")
public class IntergrationTest {

    Api api;

    /**
     * Before you run this script create a user 'cupcaketest' and grant access to the database:
     *
     * <pre>
     * DROP USER IF EXISTS cupcaketest@localhost;
     * CREATE USER cupcaketest@localhost;
     * GRANT ALL PRIVILEGES ON cupcaketest.* TO cupcaketest@localhost;
     * </pre>
     */
    static void resetTestDatabase() {
        String URL = "jdbc:mysql://localhost/cupcaketest?serverTimezone=Europe/Copenhagen&allowPublicKeyRetrieval=true";
        String USER = "cupcaketest";
//        String PASS = System.getenv(null);

        InputStream stream = IntergrationTest.class.getClassLoader().getResourceAsStream("init.sql");
        if (stream == null) throw new RuntimeException("init.sql");
        try (Connection conn = DriverManager.getConnection(URL, USER, null)) {
            conn.setAutoCommit(false);
            ScriptRunner runner = new ScriptRunner(conn);
            runner.setStopOnError(true);
            runner.runScript(new BufferedReader(new InputStreamReader(stream)));
            conn.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Done running migration");
    }

    @BeforeEach
    void setupAPI() {
        resetTestDatabase();

        String url = "jdbc:mysql://localhost:3306/cupcaketest?serverTimezone=CET";
        String user = "cupcaketest";

        Database db = new Database(url, user);
        Cart cart = new Cart();
        db.runMigrations();

        DBUserFactory userFactory = new DBUserFactory(db);
        DBUserRepository userRepository = new DBUserRepository(db);
        DBUserServices userServices = new DBUserServices(db);
        DBItemRepository itemRepository = new DBItemRepository(db);
        DBBottomRepository bottomRepository = new DBBottomRepository(db);
        DBToppingRepository toppingRepository = new DBToppingRepository(db);
        LocalCartFactory cartFactory = new LocalCartFactory(cart);
        LocalCartRepository cartRepository = new LocalCartRepository(cart);
        LocalCartServices cartServices = new LocalCartServices(cart);
        DBShippingFactory shippingFactory = new DBShippingFactory(db);
        DBOrderFactory orderFactory = new DBOrderFactory(db);
        DBOrderRepository orderRepository = new DBOrderRepository(db);
        DBOrderServices orderServices = new DBOrderServices(db);

        api = new Api(userFactory, userRepository, userServices, itemRepository, bottomRepository, toppingRepository,
                cartFactory, cartRepository, cartServices, shippingFactory, orderFactory, orderRepository,
                orderServices);
    }

    @Test
    void loginTest(){
        String email =  "test@test.dk";
        String password1 = "1234";
        String password2 = "1234";
        User user = null;

        //Create user
        try {
            user = api.createUser(email, password1, password2);
            assertEquals(email, user.getUserEmail());
        } catch (UserExists userExists) {
            userExists.printStackTrace();
        } catch (LoginSampleException e) {
            e.printStackTrace();
        } catch (InvalidPassword invalidPassword) {
            invalidPassword.printStackTrace();
        }

        //Login user
        try {
            user = api.login(user.getUserEmail(), password1);
            assertEquals(email, user.getUserEmail());
        } catch (LoginSampleException e) {
            e.printStackTrace();
        } catch (InvalidPassword invalidPassword) {
            invalidPassword.printStackTrace();
        } catch (UserNotFound e){
            e.getMessage();
        }

        //Invalid login
        try {
            user = api.login(user.getUserEmail(), "2233");
        } catch (LoginSampleException e) {
            e.printStackTrace();
        } catch (InvalidPassword invalidPassword) {
            String exceptionMessage = "" + invalidPassword.getMessage();
            assertTrue(exceptionMessage.contains(exceptionMessage));
        } catch (UserNotFound e){
            e.getMessage();
        }
    }

    @Test
    void addCreditToAccount(){
        String email =  "test@test.dk";
        String password1 = "1234";
        String password2 = "1234";
        User user = null;

        //Create user
        try {
            user = api.createUser(email, password1, password2);
            assertEquals(email, user.getUserEmail());
        } catch (UserExists userExists) {
            userExists.printStackTrace();
        } catch (LoginSampleException e) {
            e.printStackTrace();
        } catch (InvalidPassword invalidPassword) {
            invalidPassword.printStackTrace();
        }

        assert user != null;
        assertEquals(0, user.getUser_credit());

        double credit = 450;

        //Add 450 credit
        try {
            api.updateUserById(user.getId(), user.getUserRole(), user.getUser_credit() + credit);
            user = api.getUserById(user.getId());
            assertEquals(450, user.getUser_credit());
        } catch (LoginSampleException | DBexception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getItemById(){
        try {
            Item item = api.getItemById(2);
            assertEquals(2, item.getItem_id());
            assertEquals("Sour Bomb", item.getItem_name());
            assertEquals(13.00, item.getItem_price());
        } catch (NoItemWithThatId noItemWithThatId) {
            noItemWithThatId.printStackTrace();
        } catch (DBexception dBexception) {
            dBexception.printStackTrace();
        }

        try {
            Item item = api.getItemById(5);
            assertNull(item);
        } catch (NoItemWithThatId noItemWithThatId) {
            noItemWithThatId.printStackTrace();
        } catch (DBexception dBexception) {
            dBexception.printStackTrace();
        }
    }

    @Test
    void addToShoppingCartSameItem(){
        //Create cart_item
        try{
            Item item = api.getItemById(2);
            Topping topping = api.getToppingById(1);
            Bottom bottom = api.getBottomById(1);
            Cart_item cart_item1 = new Cart_item(item, topping, bottom, 1);
            Cart_item cart_item2 = new Cart_item(item, topping, bottom, 2);

            //Add to shopping cart
            ArrayList<Cart_item> shoppingCart = api.getShoppingCart();
            shoppingCart = api.addToShoppingCart(cart_item1, shoppingCart);
            shoppingCart = api.addToShoppingCart(cart_item2, shoppingCart);
            assertEquals(1, shoppingCart.size());
            assertEquals(3, shoppingCart.get(0).getQty());
            assertEquals(39.00, shoppingCart.get(0).getSubTotal());
            double cartTotal = api.getCartTotalAmount(shoppingCart);
            assertEquals(39.00, cartTotal);

        } catch (NoItemWithThatId noItemWithThatId) {
            noItemWithThatId.printStackTrace();
        } catch (NoBottomWithThatId noBottomWithThatId) {
            noBottomWithThatId.printStackTrace();
        } catch (NoToppingWithThatId noToppingWithThatId) {
            noToppingWithThatId.printStackTrace();
        } catch (DBexception dBexception) {
            dBexception.printStackTrace();
        }
    }

    @Test
    void addToShoppingCartDifferentItem(){
        //Create cart_item
        try {
            Item item = api.getItemById(2);
            Topping topping = api.getToppingById(1);
            Bottom bottom = api.getBottomById(1);
            Cart_item cart_item = new Cart_item(item, topping, bottom, 1);
            assertEquals(13.00, cart_item.getSubTotal());

            Item item1 = api.getItemById(3);
            Topping topping1 = api.getToppingById(1);
            Bottom bottom1 = api.getBottomById(1);
            Cart_item cart_item1 = new Cart_item(item1, topping1, bottom1, 2);
            assertEquals(26.00, cart_item1.getSubTotal());


            //Add to shopping cart
            ArrayList<Cart_item> shoppingCart = api.getShoppingCart();
            shoppingCart = api.addToShoppingCart(cart_item, shoppingCart);
            shoppingCart = api.addToShoppingCart(cart_item1, shoppingCart);
            assertEquals(2, shoppingCart.size());
            assertEquals(1, shoppingCart.get(0).getQty());
            assertEquals(2, shoppingCart.get(1).getQty());
            assertEquals(13.00, shoppingCart.get(0).getSubTotal());
            assertEquals(26.00, shoppingCart.get(1).getSubTotal());
            double cartTotal = api.getCartTotalAmount(shoppingCart);
            assertEquals(39.00, cartTotal);

        } catch (NoItemWithThatId noItemWithThatId) {
            noItemWithThatId.printStackTrace();
        } catch (DBexception dBexception) {
            dBexception.printStackTrace();
        } catch (NoToppingWithThatId noToppingWithThatId) {
            noToppingWithThatId.printStackTrace();
        } catch (NoBottomWithThatId noBottomWithThatId) {
            noBottomWithThatId.printStackTrace();
        }
    }

    @Test
    void GetToppingAndBottoms() {
        //Get Toppings and Bottoms
        try {
            ArrayList<Topping> allTopping = api.getAllToppingFromDB();
            assertEquals(10, allTopping.size());

            ArrayList<Bottom> allBottoms = api.getAllBottomFromDB();
            assertEquals(6, allBottoms.size());
        } catch (NoBottomListInDB noBottomListInDB) {
            noBottomListInDB.printStackTrace();
        } catch (NoToppingListInDB noToppingListInDB) {
            noToppingListInDB.printStackTrace();
        } catch (DBexception dBexception) {
            dBexception.printStackTrace();
        }
    }

    @Test
    void CreateAndGetOrder() {
        try {
            //Create user
            User user = api.createUser("test@test.dk", "1234", "1234");

            //Create Cart_item
            Item item1 = api.getItemById(3);
            Topping topping1 = api.getToppingById(1);
            Bottom bottom1 = api.getBottomById(1);
            Cart_item cart_item = new Cart_item(item1, topping1, bottom1, 2);
            assertEquals(26.00, cart_item.getSubTotal());

            //Add to shopping cart
            ArrayList<Cart_item> shoppingCart = api.getShoppingCart();
            shoppingCart = api.addToShoppingCart(cart_item, shoppingCart);

            //Create Cart
            Cart cart = new Cart(shoppingCart);

            //Create shipping
            Shipping shipping = new Shipping("Testvej", 2300, "København", user);
            shipping = api.createShipping(shipping);

            //Cart total
            double cartTotal = api.getCartTotalAmount(shoppingCart);
            assertEquals(26.00, cartTotal);

            //Create order
            Order order = new Order("credit", "", "14-11-2020", cartTotal, "shop", "pending", 1, user, shipping, cart);
            order = api.createOrder(order);
            assertEquals(1, order.getOrder_id());

            //Create order details
            api.creatOrderDetails(order);

            //Reset shopping cart
            shoppingCart = new ArrayList<>();
            assertEquals(0, shoppingCart.size());

            //Get all orders from DB
            ArrayList<Order> allOrdersFromDB = api.getAllOrders();
            assertEquals(1, allOrdersFromDB.size());

            //Get order by ID
            Order orderFromDB = api.getOrderById(1);
            assertEquals(1, orderFromDB.getOrder_id());
            assertEquals(26.00, orderFromDB.getOrder_total());

            //Check if orders are the same
            assertTrue(orderFromDB.getCart().getListOfCartItems().get(0).equals(cart_item));

        } catch (NoItemWithThatId noItemWithThatId) {
            noItemWithThatId.printStackTrace();
        } catch (NoBottomWithThatId noBottomWithThatId) {
            noBottomWithThatId.printStackTrace();
        } catch (NoToppingWithThatId noToppingWithThatId) {
            noToppingWithThatId.printStackTrace();
        } catch (DBexception dBexception) {
            dBexception.printStackTrace();
        } catch (UserExists userExists) {
            userExists.printStackTrace();
        } catch (LoginSampleException e) {
            e.printStackTrace();
        } catch (InvalidPassword invalidPassword) {
            invalidPassword.printStackTrace();
        }
    }

}
