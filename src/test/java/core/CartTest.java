package core;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    @Test
    void getListOfCartItems() {
        Item item = new Item(1, "test_item", "", 20.00, "");
        Topping topping = new Topping(1, "", 10.00);
        Bottom bottom = new Bottom(1, "", 10.00);
        Cart_item cart_item = new Cart_item(item, topping, bottom, 1);
        ArrayList<Cart_item> shoppingCart = new ArrayList<>();
        shoppingCart.add(cart_item);
        Cart cart = new Cart(shoppingCart);

        assertEquals(cart.getListOfCartItems().size(), 1);

    }

    @Test
    void setListOfCartItems() {
        ArrayList<Cart_item> shoppingCart = new ArrayList<>();
        Cart cart = new Cart(shoppingCart);

        assertEquals(cart.getListOfCartItems().isEmpty(), true);

        ArrayList<Cart_item> NewShoppingCart = new ArrayList<>();
        Item item = new Item(1, "test_item", "", 20.00, "");
        Topping topping = new Topping(1, "", 10.00);
        Bottom bottom = new Bottom(1, "", 10.00);
        Cart_item cart_item = new Cart_item(item, topping, bottom, 1);
        NewShoppingCart.add(cart_item);
        cart.setListOfCartItems(NewShoppingCart);

        assertEquals(cart.getListOfCartItems().isEmpty(), false);
        assertEquals(cart.getListOfCartItems().size(), 1);

    }
}