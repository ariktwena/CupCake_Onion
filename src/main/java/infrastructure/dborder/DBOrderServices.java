package infrastructure.dborder;

import domain.order.OrderServices;
import infrastructure.Database;
import infrastructure.LoginSampleException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBOrderServices implements OrderServices {

    private final Database database;

    public DBOrderServices(Database database) {
        this.database = database;
    }

    @Override
    public void deleteOrderById(int order_id) throws LoginSampleException {
        try (Connection conn = database.getConnection()){

            //Prepare a SQL statement from the DB connection
            String SQL = "UPDATE orders SET order_active = 0 "
                    + " WHERE order_id = ? ";
            PreparedStatement ps = conn.prepareStatement( SQL );

            //Link variables to the SQL statement
            ps.setInt(1, order_id);

            //Execute the SQL statement to update the DB
            ps.executeUpdate();

        } catch ( SQLException ex ) {
            throw new LoginSampleException( ex.getMessage() );
        }
    }

    @Override
    public void updatedOrderToPending(int order_id) throws LoginSampleException {
        try (Connection conn = database.getConnection()){

            //Prepare a SQL statement from the DB connection
            String SQL = "UPDATE orders SET order_status = 'pending' "
                    + " WHERE order_id = ? ";
            PreparedStatement ps = conn.prepareStatement( SQL );

            //Link variables to the SQL statement
            ps.setInt(1, order_id);

            //Execute the SQL statement to update the DB
            ps.executeUpdate();

        } catch ( SQLException ex ) {
            throw new LoginSampleException( ex.getMessage() );
        }
    }

    @Override
    public void updatedOrderToDelivered(int order_id) throws LoginSampleException {
        try (Connection conn = database.getConnection()){

            //Prepare a SQL statement from the DB connection
            String SQL = "UPDATE orders SET order_status = 'delivered' "
                    + " WHERE order_id = ? ";
            PreparedStatement ps = conn.prepareStatement( SQL );

            //Link variables to the SQL statement
            ps.setInt(1, order_id);

            //Execute the SQL statement to update the DB
            ps.executeUpdate();

        } catch ( SQLException ex ) {
            throw new LoginSampleException( ex.getMessage() );
        }
    }
}
