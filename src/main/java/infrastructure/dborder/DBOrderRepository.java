package infrastructure.dborder;

import core.*;
import domain.order.OrderRepository;
import infrastructure.DBexception;
import infrastructure.Database;

import java.sql.*;
import java.util.ArrayList;

public class DBOrderRepository implements OrderRepository {

    private final Database database;

    public DBOrderRepository(Database database) {
        this.database = database;
    }

    @Override
    public Order getOrderById(int order_id) throws DBexception {
        Item item;
        Topping topping;
        Bottom bottom;
        Cart_item cart_item;
        ArrayList<Cart_item> shoppingCart = new ArrayList<>();
        Cart cart;
        User user;
        Shipping shipping;
        Order order = null;

        try (Connection conn = database.getConnection()){

            String SQL = "SELECT orders.order_pay, orders.order_info, orders.order_time, "
                    + "orders.order_total, orders.order_ship, users.user_email, order_details.detail_qty, "
                    + "item.item_name, item.item_price, item.item_image, topping.topping_name, topping.topping_price, "
                    + "bottom.bottom_name, bottom.bottom_price, shipping.ship_adr, shipping.ship_zip, shipping.ship_city "
                    + "FROM orders "
                    + "INNER JOIN users ON orders.fk_user_id = users.user_id "
                    + "INNER JOIN shipping ON orders.fk_ship_id = shipping.ship_id "
                    + "INNER JOIN order_details ON orders.order_id = order_details.fk_order_id "
                    + "INNER JOIN item ON order_details.fk_item_id = item.item_id "
                    + "INNER JOIN topping ON order_details.fk_topping_id = topping.topping_id "
                    + "INNER JOIN bottom ON order_details.fk_bottom_id = bottom.bottom_id "
                    + "WHERE orders.order_id = ?";

            PreparedStatement ps = conn.prepareStatement( SQL );
            ps.setInt(1, order_id);
            ResultSet rs = ps.executeQuery();

            while ( rs.next() ) {
                String order_pay = rs.getString( "order_pay" );
                String order_info = rs.getString( "order_info" );
                String order_time = rs.getString("order_time");
                double order_total = rs.getDouble("order_total");
                String order_ship = rs.getString( "order_ship" );
                String user_email = rs.getString( "user_email" );
                int detail_qty = rs.getInt("detail_qty");
                String item_name = rs.getString( "item_name" );
                double item_price = rs.getDouble("item_price");
                String item_image = rs.getString( "item_image" );
                String topping_name = rs.getString( "topping_name" );
                double topping_price = rs.getDouble("topping_price");
                String bottom_name = rs.getString( "bottom_name" );
                double bottom_price = rs.getDouble("bottom_price");
                String ship_adr = rs.getString( "ship_adr" );
                int ship_zip = rs.getInt("ship_zip");
                String ship_city = rs.getString( "ship_city" );

                topping = new Topping(topping_name, topping_price);
                bottom = new Bottom(bottom_name, bottom_price);
                item = new Item(item_name, item_price, item_image);
                cart_item = new Cart_item(item, topping, bottom, detail_qty);
                shoppingCart.add(cart_item);
                cart = new Cart(shoppingCart);
                user = new User(user_email);
                shipping = new Shipping(ship_adr, ship_zip, ship_city);
                order = new Order(order_id, order_pay, order_info, order_time, order_total, order_ship, user, shipping, cart);
            }

            return order;

        } catch ( SQLException ex ) {
            throw new DBexception();
        }
    }

    @Override
    public ArrayList<Order> getAllOrders() throws DBexception {
        ArrayList<Order> allOrders = new ArrayList<>();
        User user;
        Order order;

        try (Connection conn = database.getConnection()){

            String SQL = "SELECT orders.order_id, orders.order_pay, orders.order_time, orders.order_total, "
                    + "orders.order_ship, orders.order_status, users.user_email FROM orders "
                    + "INNER JOIN users ON orders.fk_user_id = users.user_id "
                    + "WHERE order_active = 1";

            PreparedStatement ps = conn.prepareStatement( SQL );
            ResultSet rs = ps.executeQuery();

            while ( rs.next() ) {
                int order_id = rs.getInt("order_id");
                String order_pay = rs.getString( "order_pay" );
                String order_time = rs.getString("order_time");
                double order_total = rs.getDouble("order_total");
                String order_ship = rs.getString( "order_ship" );
                String order_status = rs.getString( "order_status" );
                String user_email = rs.getString( "user_email" );

                user = new User(user_email);
                order = new Order(order_id, order_pay, order_time, order_total, order_ship, order_status, user);
                allOrders.add(order);
            }

            return allOrders;

        } catch ( SQLException ex ) {
            throw new DBexception();
        }
    }

    @Override
    public ArrayList<Order> getAllOrdersByUserId(int user_id) throws DBexception {
        ArrayList<Order> allOrders = new ArrayList<>();
        User user;
        Order order;

        try (Connection conn = database.getConnection()){

            String SQL = "SELECT orders.order_id, orders.order_pay, orders.order_time, orders.order_total, "
                    + "orders.order_ship, orders.order_status, users.user_email FROM orders "
                    + "INNER JOIN users ON orders.fk_user_id = users.user_id "
                    + "WHERE order_active = 1 AND user_id = ?";

            PreparedStatement ps = conn.prepareStatement( SQL );
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();

            while ( rs.next() ) {
                int order_id = rs.getInt("order_id");
                String order_pay = rs.getString( "order_pay" );
                String order_time = rs.getString("order_time");
                double order_total = rs.getDouble("order_total");
                String order_ship = rs.getString( "order_ship" );
                String order_status = rs.getString( "order_status" );
                String user_email = rs.getString( "user_email" );

                user = new User(user_email);
                order = new Order(order_id, order_pay, order_time, order_total, order_ship, order_status, user);
                allOrders.add(order);
            }

            return allOrders;

        } catch ( SQLException ex ) {
            throw new DBexception();
        }
    }
}
