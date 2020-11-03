package api;

import core.*;
import domain.bottom.BottomRepository;
import domain.bottom.NoBottomListInDB;
import domain.bottom.NoBottomWithThatId;
import domain.cart.CartFactory;
import domain.cart.CartRepository;
import domain.cart.CartServices;
import domain.item.ItemRepository;
import domain.item.NoItemWithThatId;
import domain.order.OrderFactory;
import domain.order.OrderRepository;
import domain.order.OrderServices;
import domain.shipping.ShippingFactory;
import domain.topping.NoToppingListInDB;
import domain.topping.NoToppingWithThatId;
import domain.topping.ToppingRepository;
import domain.user.*;
import infrastructure.DBexception;
import infrastructure.LoginSampleException;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Api {

    private final UserFactory userFactory;
    private final UserRepository userRepository;
    private final UserServices userServices;
    private final ItemRepository itemRepository;
    private final BottomRepository bottomRepository;
    private final ToppingRepository toppingRepository;
    private final CartFactory cartFactory;
    private final CartRepository cartRepository;
    private final CartServices cartServices;
    private final ShippingFactory shippingFactory;
    private final OrderFactory orderFactory;
    private final OrderRepository orderRepository;
    private final OrderServices orderServices;

    public Api(UserFactory userFactory, UserRepository userRepository, UserServices userServices, ItemRepository itemRepository,
               BottomRepository bottomRepository, ToppingRepository toppingRepository, CartFactory cartFactory,
               CartRepository cartRepository, CartServices cartServices, ShippingFactory shippingFactory, OrderFactory orderFactory,
               OrderRepository orderRepository, OrderServices orderServices) {
        this.userFactory = userFactory;
        this.userRepository = userRepository;
        this.userServices = userServices;
        this.itemRepository = itemRepository;
        this.bottomRepository = bottomRepository;
        this.toppingRepository = toppingRepository;
        this.cartFactory = cartFactory;
        this.cartRepository = cartRepository;
        this.cartServices = cartServices;
        this.shippingFactory = shippingFactory;
        this.orderFactory = orderFactory;
        this.orderRepository = orderRepository;
        this.orderServices = orderServices;
    }

    /**
     * Create a user from the registration page
     * @param user_email
     * @param password1 needs to be the same as password2
     * @param password2 needs to be the same as password1
     * @return User that is signing up
     * @throws LoginSampleException A custom made exception message
     * @throws UserExists User is already registered in the DB
     * @throws InvalidPassword Passwords do not match
     */
    public User createUser(String user_email, String password1, String password2) throws LoginSampleException, UserExists, InvalidPassword {
        //Generate salt
        byte[] salt = User.generateSalt();
        //Generate secret
        byte[] secret = User.calculateSecret(salt, password1);
        //Create user

        //Validate user input
        if(userServices.userAldreadyExistsInDB(user_email)){
            throw new UserExists();

        } else if (!password1.equals(password2)){
            throw new InvalidPassword();

        } else {
            //Create user
            User user = new User(user_email, "customer", 0, salt, secret);
            //Save/create the user in the DB and return the users (No longer id -1)
            user = userFactory.createUser(user);
            return user;
        }
    }

    /**
     * Login process
     * @param email
     * @param password
     * @return The user that is logging in
     * @throws LoginSampleException
     * @throws InvalidPassword
     */
    public User login(String email, String password) throws LoginSampleException, InvalidPassword, UserNotFound {

        //Get user from the DB with a specific name
        User user = userServices.login(email);

        //Username is null => no user was found in DB
        if(user.getUserEmail() == null){
            throw new UserNotFound();
        }

        //Validate the DB password with the provided one
        else if (!user.isPasswordCorrect(password)) {
            throw new InvalidPassword();
        } else  {
            //Return user if password is validated
            return user;
        }
    }

    /**
     * Get all the users from the DB that has user_active = 1
     * @return
     * @throws DBexception
     * @throws DBexception
     */
    public ArrayList<User> getAllUsersFromDB() throws DBexception, DBexception {
        ArrayList<User> allUsersFromDB = userRepository.getAllUsersFromDB();
        return allUsersFromDB;
    }

    /**
     * The admin can create new users from the admin page
     * @param user_email
     * @param password
     * @param user_role
     * @param user_credit
     * @return The created user that the admin has created
     * @throws LoginSampleException
     * @throws UserExists
     */
    public User createUserFromAdminPage(String user_email, String password, String user_role, double user_credit) throws LoginSampleException, UserExists {

        //Generate salt
        byte[] salt = User.generateSalt();
        //Generate secret
        byte[] secret = User.calculateSecret(salt, password);
        //Create user

        //Validate user input
        if(userServices.userAldreadyExistsInDB(user_email)){
            throw new UserExists();

        } else {
            //Create user
            User user = new User(user_email, user_role, user_credit, salt, secret);

            //Save/create the user in the DB and return the users (No longer id -1)
            user = userFactory.createUser(user);

            return user;
        }
    }

    /**
     * Get a specific user from the DB through user_id
     * @param user_id
     * @return
     * @throws DBexception
     */
    public User getUserById(int user_id) throws DBexception {
        User user = userRepository.getUserById(user_id);
        return user;
    }

    /**
     * Update user in the DB after admin has edited the user
     * @param user_id
     * @param user_role
     * @param user_credit
     * @throws LoginSampleException
     */
    public void updateUserById(int user_id, String user_role, double user_credit) throws LoginSampleException {
        userServices.updateUserById(user_id, user_role, user_credit);
    }

    /**
     * Delete the user by setting the user_active to 0
     * @param user_id
     * @throws LoginSampleException
     */
    public void deleteUserById(int user_id) throws LoginSampleException {
        userServices.deleteUserById(user_id);
    }

    /**
     * Set user_role to "customer"
     * @param user_id
     * @throws LoginSampleException
     */
    public void changeUserRoleToCustomer(int user_id) throws LoginSampleException {
        userServices.changeUserRoleToCustomer(user_id);
    }

    /**
     * Set user_role to "admin"
     * @param user_id
     * @throws LoginSampleException
     */
    public void changeUserRoleToAdmin(int user_id) throws LoginSampleException {
        userServices.changeUserRoleToAdmin(user_id);
    }

    /**
     * Get all the saved item info from the DB to use in the product and index page
     * @return
     * @throws DBexception
     */
    public ArrayList<Item> getAllItemsFromDB() throws DBexception {
        return itemRepository.getAllItemsFromDB();
    }

    /**
     * Get a specific item through it's id
     * @param item_id
     * @return
     * @throws DBexception
     * @throws NoItemWithThatId
     */
    public Item getItemById(int item_id) throws DBexception, NoItemWithThatId {
        if(itemRepository.getItemById(item_id) == null) {
            throw new NoItemWithThatId();
        } else {
            return itemRepository.getItemById(item_id);
        }
    }

    /**
     * Get an ArrayList of all the toppings in the DB
     * @return
     * @throws DBexception
     * @throws NoToppingListInDB
     */
    public ArrayList<Topping> getAllToppingFromDB() throws DBexception, NoToppingListInDB {
        if(toppingRepository.getAllToppingsFromDb().size() == 0 || toppingRepository.getAllToppingsFromDb().isEmpty()) {
            throw new NoToppingListInDB();
        } else {
            return toppingRepository.getAllToppingsFromDb();
        }
    }

    /**
     * Get an ArrayList of all the bottoms in the DB
     * @return
     * @throws DBexception
     * @throws NoBottomListInDB
     */
    public ArrayList<Bottom> getAllBottomFromDB() throws DBexception, NoBottomListInDB {
        if(toppingRepository.getAllToppingsFromDb().size() == 0 || toppingRepository.getAllToppingsFromDb().isEmpty()) {
            throw new NoBottomListInDB();
        } else {
            return bottomRepository.getAllBottomsFromDB();
        }
    }

    /**
     * Get Topping ID fromDB
     * @param topping_id
     * @return
     * @throws DBexception
     * @throws NoToppingWithThatId
     */
    public Topping getToppingById(int topping_id) throws DBexception, NoToppingWithThatId {
        if(toppingRepository.getToppingById(topping_id) == null) {
            throw new NoToppingWithThatId();
        } else {
            return toppingRepository.getToppingById(topping_id);
        }
    }

    /**
     * Get Buttom ID from DB
     * @param bottom_id
     * @return
     * @throws DBexception
     * @throws NoBottomWithThatId
     */
    public Bottom getBottomById(int bottom_id) throws DBexception, NoBottomWithThatId {
        if(bottomRepository.getBottomById(bottom_id) == null) {
            throw new NoBottomWithThatId();
        } else {
            return bottomRepository.getBottomById(bottom_id);
        }
    }

    /**
     * Add Cart_items to local shopping cart and evaluate if the item: exists -> increase qty, else -> new entry
     * @param cart_item
     */
    public void addToShoppingCart(Cart_item cart_item) {
        boolean cartItemAlreadyInTheShoppingCart = false;

        ArrayList<Cart_item> listOfCartItems = cartRepository.getShoppingCart();

        //Check if cart item already exist in the shopping cart
        for(Cart_item currentItem : listOfCartItems){
            //Validate if the cart item is the same
            if(currentItem.equals(cart_item)){
//            if(currentItem.getItem().getItem_name().equals(cart_item.getItem().getItem_name())
//                    && currentItem.getTopping().getTopping_name().equals(cart_item.getTopping().getTopping_name())
//                    && currentItem.getBottom().getBottom_name().equals(cart_item.getBottom().getBottom_name())){
                //Increase the qty
                currentItem.setQty(currentItem.getQty() + cart_item.getQty());
                //Recalculate subTotal: new price = old price + (new qty * (item.price + topping.price + bottom.price))
                currentItem.setSubTotal(currentItem.getSubTotal() + (cart_item.getQty() * (cart_item.getItem().getItem_price() + cart_item.getTopping().getTopping_price() + cart_item.getBottom().getBottom_price())));
                //Set the boolean to true if the cart item is already resent in the shopping list
                cartItemAlreadyInTheShoppingCart = true;
            }
        }
        //Add the cart item to the shopping cart if not the same. else jut return the modified shopping cart
        if(!cartItemAlreadyInTheShoppingCart){
            listOfCartItems.add(cart_item);
            cartFactory.setCart(listOfCartItems);
        } else {
            cartFactory.setCart(listOfCartItems);
        }
    }

    /**
     * Get the local saved cart (ArrayList<Cart_item>)from Cart
     * @return
     */
    public ArrayList<Cart_item> getShoppingCart() {
        return cartRepository.getShoppingCart();
    }

    /**
     * Get teh total amount in the current session cart
     * @return
     */
    public double getCartTotalAmount(){
        return cartServices.getCartTotalAmount();
    }

    /**
     * Reset the cart with a empty or custom cart
     * @param shoppingCart
     */
    public void resetCart(ArrayList<Cart_item> shoppingCart){
        cartServices.resetCart(shoppingCart);
    }

    /**
     * Create shipping deatils in the DB
     * @param shipping
     * @return
     * @throws LoginSampleException
     */
    public Shipping createShipping(Shipping shipping) throws LoginSampleException {
        return shippingFactory.createShipping(shipping);
    }

    /**
     * Create order in the DB
     * @param order
     * @return
     * @throws LoginSampleException
     */
    public Order createOrder(Order order) throws LoginSampleException {
        return orderFactory.createOrder(order);
    }

    /**
     * Add cart details to the DB by lopping the Cart's cart item's
     * @param order
     * @throws LoginSampleException
     */
    public void creatOrderDetails(Order order) throws LoginSampleException {
        int order_id = order.getOrder_id();
        ArrayList<Cart_item> shoppingCart = order.getCart().getListOfCartItems();

        for(Cart_item cart_item : shoppingCart){
            orderFactory.createOrderDetails(cart_item, order_id);
        }
    }

    /**
     * Update users credit balance after a purchase
     * @param user_id
     * @param newCreditBallance
     * @throws LoginSampleException
     */
    public void updateUserCreditBalance(int user_id, double newCreditBallance) throws LoginSampleException {
        userServices.updateUserCreditBalance(user_id, newCreditBallance);
    }

    /**
     * Get all the orders in the DB -> admin area
     * @return Array list of orders that are active
     * @throws DBexception
     */
    public ArrayList<Order> getAllOrders() throws DBexception {
        return orderRepository.getAllOrders();
    }

    /**
     * Get a specific order by it's ID
     * @param order_id
     * @return
     * @throws DBexception
     */
    public Order getOrderById(int order_id) throws DBexception {
        return orderRepository.getOrderById(order_id);
    }

    /**
     * Get all the orders from a specific user -> profile area
     * @param user_id
     * @return Array list of orders that are active
     * @throws DBexception
     */
    public ArrayList<Order> getAllOrdersByUserId(int user_id) throws DBexception {
        return orderRepository.getAllOrdersByUserId(user_id);
    }

    /**
     * Set order_status to "pending"
     * @param order_id
     * @throws LoginSampleException
     */
    public void updatedOrderToPending(int order_id) throws LoginSampleException {
        orderServices.updatedOrderToPending(order_id);
    }

    /**
     * Set order_status to "delivered"
     * @param order_id
     * @throws LoginSampleException
     */
    public void updatedOrderToDelivered(int order_id) throws LoginSampleException {
        orderServices.updatedOrderToDelivered(order_id);
    }

    /**
     * Delete order from DB (Set the order_active to 0)
     * @param order_id
     * @throws LoginSampleException
     */
    public void deleteOrderById(int order_id) throws LoginSampleException {
        orderServices.deleteOrderById(order_id);
    }



}
