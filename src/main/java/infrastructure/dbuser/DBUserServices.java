package infrastructure.dbuser;

import core.User;
import domain.user.UserServices;
import infrastructure.Database;
import infrastructure.LoginSampleException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUserServices implements UserServices {

    private final Database database;

    public DBUserServices(Database database) {
        this.database = database;
    }

    @Override
    public void updateUserById(int user_id, String user_role, double user_credit) throws LoginSampleException {

        try (Connection conn = database.getConnection()){

            //Prepare a SQL statement from the DB connection
            String SQL = "UPDATE users SET user_role = ?, user_credit = ? "
                    + " WHERE user_id = ? ";
            PreparedStatement ps = conn.prepareStatement( SQL );

            //Link variables to the SQL statement
            ps.setString(1, user_role);
            ps.setDouble(2, user_credit);
            ps.setInt(3, user_id);

            //Execute the SQL statement to update the DB
            ps.executeUpdate();

        } catch ( SQLException ex ) {
            throw new LoginSampleException( ex.getMessage() );
        }
    }

    @Override
    public void deleteUserById(int user_id) throws LoginSampleException {

        try (Connection conn = database.getConnection()){

            //Prepare a SQL statement from the DB connection
            String SQL = "UPDATE users SET user_active = 0 "
                    + " WHERE user_id = ? ";
            PreparedStatement ps = conn.prepareStatement( SQL );

            //Link variables to the SQL statement
            ps.setInt(1, user_id);

            //Execute the SQL statement to update the DB
            ps.executeUpdate();

        } catch ( SQLException ex ) {
            throw new LoginSampleException( ex.getMessage() );
        }
    }

    @Override
    public void changeUserRoleToCustomer(int user_id) throws LoginSampleException {

        try (Connection conn = database.getConnection()){

            //Prepare a SQL statement from the DB connection
            String SQL = "UPDATE users SET user_role = 'customer' "
                    + " WHERE user_id = ? ";
            PreparedStatement ps = conn.prepareStatement( SQL );

            //Link variables to the SQL statement
            ps.setInt(1, user_id);

            //Execute the SQL statement to update the DB
            ps.executeUpdate();

        } catch ( SQLException ex ) {
            throw new LoginSampleException( ex.getMessage() );
        }
    }

    @Override
    public void changeUserRoleToAdmin(int user_id) throws LoginSampleException {

        try (Connection conn = database.getConnection()){

            //Prepare a SQL statement from the DB connection
            String SQL = "UPDATE users SET user_role = 'admin' "
                    + " WHERE user_id = ? ";
            PreparedStatement ps = conn.prepareStatement( SQL );

            //Link variables to the SQL statement
            ps.setInt(1, user_id);

            //Execute the SQL statement to update the DB
            ps.executeUpdate();

        } catch ( SQLException ex ) {
            throw new LoginSampleException( ex.getMessage() );
        }
    }

    @Override
    public boolean userAldreadyExistsInDB(String email) {

        try (Connection conn = database.getConnection()){

            //Prepare a SQL statement from the DB connection
            String SQL = "SELECT user_email FROM users WHERE user_email = (?)";
            PreparedStatement ps = conn.prepareStatement( SQL );

            //Link variables to the SQL statement
            ps.setString(1, email);

            //Execute the SQL query and save the result
            ResultSet rs = ps.executeQuery();

            //Search if there is a result from the DB execution
            if (rs.next()) {
                //Return true, if the is a DB execution result
                return true;

            } else {
                //Return false, if the isn't a DB execution result
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User login(String user_email) throws LoginSampleException {

        try (Connection conn = database.getConnection()){
            String SQL = "SELECT user_id, user_role, user_credit, salt, secret FROM users "
                    + "WHERE user_email = ?";
            PreparedStatement ps = conn.prepareStatement( SQL );
            ps.setString( 1, user_email );
            ResultSet rs = ps.executeQuery();
            if ( rs.next() ) {
                int user_id = rs.getInt("user_id");
                String user_role = rs.getString( "user_role" );
                double user_credit = rs.getDouble("user_credit");
                byte[] salt = rs.getBytes("salt");
                byte[] secret = rs.getBytes("secret");

                User user = new User(user_id, user_email, user_role, user_credit, salt, secret);

                return user;

            } else {
                throw new LoginSampleException("User not found");
            }
        } catch ( SQLException ex ) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

    @Override
    public void updateUserCreditBalance(int user_id, double newCreditBalance) throws LoginSampleException {
        try (Connection conn = database.getConnection()){

            //Prepare a SQL statement from the DB connection
            String SQL = "UPDATE users SET user_credit = ? "
                    + " WHERE user_id = ? ";
            PreparedStatement ps = conn.prepareStatement( SQL );

            //Link variables to the SQL statement
            ps.setDouble(1, newCreditBalance);
            ps.setInt(2, user_id);

            //Execute the SQL statement to update the DB
            ps.executeUpdate();

        } catch ( SQLException ex ) {
            throw new LoginSampleException( ex.getMessage() );
        }
    }
}
