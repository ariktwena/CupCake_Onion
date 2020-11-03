package infrastructure.local_cart;

import core.Cart;
import core.Cart_item;
import domain.cart.CartFactory;

import java.util.ArrayList;

public class LocalCartFactory implements CartFactory {

    private final Cart cart;

    public LocalCartFactory(Cart cart) {
        this.cart = cart;
    }

//    @Override
//    public void addToCart(Cart_item cart_item) {
//        cart.getListOfCartItems().add(cart_item);
//    }

    @Override
    public void setCart(ArrayList<Cart_item> listOfCartItems) {
        cart.setListOfCartItems(listOfCartItems);
    }
}
