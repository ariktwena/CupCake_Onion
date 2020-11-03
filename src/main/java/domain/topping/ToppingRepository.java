package domain.topping;

import core.Item;
import core.Topping;
import infrastructure.DBexception;

import java.util.ArrayList;

public interface ToppingRepository {

    ArrayList<Topping> getAllToppingsFromDb() throws DBexception;

    Topping getToppingById(int topping_id) throws DBexception;

}
