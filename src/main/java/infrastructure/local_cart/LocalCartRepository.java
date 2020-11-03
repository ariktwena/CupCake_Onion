package infrastructure.local_cart;

import core.Cart;
import core.Cart_item;
import domain.cart.CartFactory;
import domain.cart.CartRepository;

import java.util.ArrayList;

public class LocalCartRepository implements CartRepository {

    private final Cart cart;

    public LocalCartRepository(Cart cart) {
        this.cart = cart;
    }


    @Override
    public ArrayList<Cart_item> getShoppingCart() {
        return cart.getListOfCartItems();
    }
}
