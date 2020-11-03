package infrastructure.dborder;

import core.Cart_item;
import core.Order;
import domain.order.OrderFactory;
import infrastructure.Database;
import infrastructure.LoginSampleException;

import java.sql.*;

public class DBOrderFactory implements OrderFactory {

    private final Database database;

    public DBOrderFactory (Database database) {
        this.database = database;
    }

    @Override
    public Order createOrder(Order order) throws LoginSampleException {
        try (Connection conn = database.getConnection()){

            //Prepare a SQL statement from the DB connection
            String SQL = "INSERT INTO orders (order_pay, order_info, order_time, order_total, order_ship, fk_user_id, fk_ship_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement( SQL, Statement.RETURN_GENERATED_KEYS );

            //Link variables to the SQL statement
            ps.setString(1, order.getOrder_pay());
            ps.setString(2, order.getOrder_info());
            ps.setString(3, order.getOrder_time());
            ps.setDouble(4, order.getOrder_total());
            ps.setString(5, order.getOrder_ship());
            ps.setInt(6, order.getUser().getId());
            ps.setInt(7, order.getShipping().getShipping_id());

            //Execute the SQL statement to update the DB
            ps.executeUpdate();

            //Optional: Get result from the SQL execution, that returns the executed keys (user_id, user_name etc..)
            ResultSet rs = ps.getGeneratedKeys();

            //Search if there is a result from the DB execution
            if (rs.next()) {
                //Create user from the user_id key that is returned form the DB execution
                return order.orderWhitId(rs.getInt(1));

            } else {
                //Return null, if no result is returned form the execution
                return null;
            }
        } catch ( SQLException ex ) {
            throw new LoginSampleException( ex.getMessage() );
        }
    }

    @Override
    public void createOrderDetails(Cart_item cart_item, int  order_id) throws LoginSampleException {
        try (Connection conn = database.getConnection()){

            //Prepare a SQL statement from the DB connection
            String SQL = "INSERT INTO order_details "
                    + "(fk_item_id, fk_topping_id, fk_bottom_id, detail_qty, detail_price, detail_subtotal, fk_order_id) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement( SQL);

            //Link variables to the SQL statement
            ps.setInt(1, cart_item.getItem().getItem_id());
            ps.setInt(2, cart_item.getTopping().getTopping_id());
            ps.setInt(3, cart_item.getBottom().getBottom_id());
            ps.setInt(4, cart_item.getQty());
            ps.setDouble(5, cart_item.getItem().getItem_price() + cart_item.getTopping().getTopping_price() + cart_item.getBottom().getBottom_price());
            ps.setDouble(6, cart_item.getSubTotal());
            ps.setInt(7, order_id);

            //Execute the SQL statement to update the DB
            ps.executeUpdate();

        } catch ( SQLException ex ) {
            throw new LoginSampleException( ex.getMessage() );
        }
    }
}
