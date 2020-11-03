package infrastructure.dbtoppings;

import core.Topping;
import domain.topping.ToppingRepository;
import infrastructure.DBexception;
import infrastructure.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBToppingRepository implements ToppingRepository {

    private final Database database;

    public DBToppingRepository(Database database) {
        this.database = database;
    }

    @Override
    public ArrayList<Topping> getAllToppingsFromDb() throws DBexception {
        ArrayList<Topping> allToppingsFromDB = new ArrayList<>();

        try (Connection conn = database.getConnection()){

            String SQL = "SELECT topping_id, topping_name, topping_price FROM topping ";

            PreparedStatement ps = conn.prepareStatement( SQL );

            ResultSet rs = ps.executeQuery();

            while ( rs.next() ) {
                int topping_id = rs.getInt("topping_id");
                String topping_name = rs.getString( "topping_name" );
                double topping_price = rs.getDouble("topping_price");

                Topping topping = new Topping(topping_id, topping_name, topping_price);

                allToppingsFromDB.add(topping);
            }
        } catch ( SQLException ex ) {
            throw new DBexception();
        }

        return allToppingsFromDB;
    }

    @Override
    public Topping getToppingById(int topping_id) throws DBexception {
        Topping topping = null;

        try (Connection conn = database.getConnection()){

            String SQL = "SELECT topping_name, topping_price FROM topping "
                    + "WHERE topping_id = ?";

            PreparedStatement ps = conn.prepareStatement( SQL );
            ps.setInt( 1, topping_id );
            ResultSet rs = ps.executeQuery();

            if ( rs.next() ) {
                String topping_name = rs.getString( "topping_name" );
                double topping_price = rs.getDouble( "topping_price" );

                topping = new Topping(topping_id, topping_name, topping_price);

                return topping;
            }
        } catch ( SQLException ex ) {
            throw new DBexception();
        }

        return topping;
    }

}
