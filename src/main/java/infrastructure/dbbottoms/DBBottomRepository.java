package infrastructure.dbbottoms;

import core.Bottom;
import domain.bottom.BottomRepository;
import infrastructure.DBexception;
import infrastructure.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBBottomRepository implements BottomRepository {

    private final Database database;

    public DBBottomRepository(Database database) {
        this.database = database;
    }

    @Override
    public ArrayList<Bottom> getAllBottomsFromDB() throws DBexception {
        ArrayList<Bottom> allBottomsFromDB = new ArrayList<>();

        try (Connection conn = database.getConnection()){

            String SQL = "SELECT bottom_id, bottom_name, bottom_price FROM bottom ";

            PreparedStatement ps = conn.prepareStatement( SQL );

            ResultSet rs = ps.executeQuery();

            while ( rs.next() ) {
                int bottom_id = rs.getInt("bottom_id");
                String bottom_name = rs.getString( "bottom_name" );
                double bottom_price = rs.getDouble("bottom_price");

                Bottom bottom = new Bottom(bottom_id, bottom_name, bottom_price);

                allBottomsFromDB.add(bottom);
            }
        } catch ( SQLException ex ) {
            throw new DBexception();
        }

        return allBottomsFromDB;
    }

    @Override
    public Bottom getBottomById(int bottom_id) throws DBexception {
        Bottom bottom = null;

        try (Connection conn = database.getConnection()){

            String SQL = "SELECT bottom_name, bottom_price FROM bottom "
                    + "WHERE bottom_id = ?";

            PreparedStatement ps = conn.prepareStatement( SQL );
            ps.setInt( 1, bottom_id );
            ResultSet rs = ps.executeQuery();

            if ( rs.next() ) {
                String bottom_name = rs.getString( "bottom_name" );
                double bottom_price = rs.getDouble( "bottom_price" );

                bottom = new Bottom(bottom_id, bottom_name, bottom_price);

                return bottom;
            }
        } catch ( SQLException ex ) {
            throw new DBexception();
        }

        return bottom;
    }
}
