package infrastructure.local_cart;

import core.Cart;
import core.Cart_item;
import domain.cart.CartServices;

import java.util.ArrayList;

public class LocalCartServices implements CartServices {

    private final Cart cart;

    public LocalCartServices(Cart cart) {
        this.cart = cart;
    }

    @Override
    public double getCartTotalAmount() {
        ArrayList<Cart_item> shoppingCart = cart.getListOfCartItems();
        double cartTotal = 0;
        for(Cart_item currentElement: shoppingCart){
            cartTotal = cartTotal + currentElement.getSubTotal();
        }
        return cartTotal;
    }

    @Override
    public void resetCart(ArrayList<Cart_item> shoppingCart) {
        cart.setListOfCartItems(shoppingCart);
    }
}
