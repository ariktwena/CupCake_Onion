package domain.cart;

import core.Cart;
import core.Cart_item;
import core.Item;

import java.util.ArrayList;

public interface CartFactory {

//    void addToCart(Cart_item cart_item);

    void setCart(ArrayList<Cart_item> listOfCartItems);

}
