package domain.cart;

import core.Cart;
import core.Cart_item;

import java.util.ArrayList;

public interface CartRepository {

    ArrayList<Cart_item> getShoppingCart();

}
