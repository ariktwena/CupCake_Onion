package domain.item;

import core.Item;
import infrastructure.DBexception;

import java.util.ArrayList;

public interface ItemRepository {

    ArrayList<Item> getAllItemsFromDB() throws DBexception;

    Item getItemById(int item_id) throws DBexception;
}
