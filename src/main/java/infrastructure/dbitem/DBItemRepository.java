package infrastructure.dbitem;

import core.Item;
import domain.item.ItemRepository;
import infrastructure.DBexception;
import infrastructure.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBItemRepository implements ItemRepository {

    private final Database database;

    public DBItemRepository(Database database) {
        this.database = database;
    }

    @Override
    public ArrayList<Item> getAllItemsFromDB() throws DBexception {
        ArrayList<Item> allItemsFromDB = new ArrayList<>();

        try (Connection conn = database.getConnection()){

            String SQL = "SELECT item_id, item_name, item_des, item_price, item_image FROM item";

            PreparedStatement ps = conn.prepareStatement( SQL );

            ResultSet rs = ps.executeQuery();

            while ( rs.next() ) {
                int item_id = rs.getInt("item_id");
                String item_name = rs.getString( "item_name" );
                String item_des = rs.getString( "item_des" );
                double item_price = rs.getDouble( "item_price" );
                String item_image = rs.getString( "item_image" );

                Item item = new Item(item_id, item_name, item_des, item_price, item_image);

                allItemsFromDB.add(item);
            }
        } catch ( SQLException ex ) {
            throw new DBexception();
        }

        return allItemsFromDB;
    }

    @Override
    public Item getItemById(int item_id) throws DBexception {
        Item item = null;

        try (Connection conn = database.getConnection()){

            String SQL = "SELECT item_name, item_des, item_price, item_image FROM item "
                    + "WHERE item_id = ?";

            PreparedStatement ps = conn.prepareStatement( SQL );
            ps.setInt( 1, item_id );
            ResultSet rs = ps.executeQuery();

            if ( rs.next() ) {
                String item_name = rs.getString( "item_name" );
                String item_des = rs.getString( "item_des" );
                double item_price = rs.getDouble( "item_price" );
                String item_image = rs.getString( "item_image" );

                item = new Item(item_id, item_name, item_des, item_price, item_image);

                return item;
            }
        } catch ( SQLException ex ) {
            throw new DBexception();
        }

        return item;
    }
}
