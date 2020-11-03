package infrastructure.dbshipping;

import core.Shipping;
import domain.shipping.ShippingFactory;
import infrastructure.Database;
import infrastructure.LoginSampleException;

import java.sql.*;

public class DBShippingFactory implements ShippingFactory {

    private final Database database;

    public DBShippingFactory(Database database) {
        this.database = database;
    }

    @Override
    public Shipping createShipping(Shipping shipping) throws LoginSampleException {
        try (Connection conn = database.getConnection()){

            //Prepare a SQL statement from the DB connection
            String SQL = "INSERT INTO shipping (ship_adr, ship_zip, ship_city, fk_user_id) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement( SQL, Statement.RETURN_GENERATED_KEYS );

            //Link variables to the SQL statement
            ps.setString(1, shipping.getShipping_address());
            ps.setInt(2, shipping.getShipping_zip());
            ps.setString(3, shipping.getShipping_city());
            ps.setInt(4, shipping.getUser().getId());

            //Execute the SQL statement to update the DB
            ps.executeUpdate();

            //Optional: Get result from the SQL execution, that returns the executed keys (user_id, user_name etc..)
            ResultSet rs = ps.getGeneratedKeys();

            //Search if there is a result from the DB execution
            if (rs.next()) {
                //Create user from the user_id key that is returned form the DB execution
                return shipping.shippingWhitId(rs.getInt(1));

            } else {
                //Return null, if no result is returned form the execution
                return null;
            }
        } catch ( SQLException ex ) {
            throw new LoginSampleException( ex.getMessage() );
        }
    }
}
