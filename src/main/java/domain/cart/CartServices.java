package domain.cart;

import core.Cart_item;

import java.util.ArrayList;

public interface CartServices {
    double getCartTotalAmount(ArrayList<Cart_item> shoppingCart);

    void resetCart(ArrayList<Cart_item> shoppingCart);
}
