package core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Cart_itemTest {

    @org.junit.jupiter.api.Test
    void getSubTotalTest1() {
        Item item = new Item(1, "test_item", "", 20.00, "");
        Topping topping = new Topping(1, "", 10.00);
        Bottom bottom = new Bottom(1, "", 10.00);
        Cart_item cart_item = new Cart_item(item, topping, bottom, 1);

        assertEquals(40.00, cart_item.getSubTotal());
    }

    @org.junit.jupiter.api.Test
    void getSubTotalTest2() {
        Item item = new Item(1, "test_item", "", 20.00, "");
        Topping topping = new Topping(1, "", 10.00);
        Bottom bottom = new Bottom(1, "", 10.00);
        Cart_item cart_item = new Cart_item(item, topping, bottom, 2);

        assertEquals(80.00, cart_item.getSubTotal());
    }

    @Test
    void twoCartItemsAreTheSame(){
        Item item = new Item(1, "test_item", "", 20.00, "");
        Topping topping = new Topping(1, "", 10.00);
        Bottom bottom = new Bottom(1, "", 10.00);
        Cart_item cart_item = new Cart_item(item, topping, bottom, 2);

        assertEquals(cart_item.equals(cart_item), true);

        Item item1 = new Item(1, "test_item", "", 20.00, "");
        Topping topping1 = new Topping(1, "", 10.00);
        Bottom bottom1 = new Bottom(1, "", 10.00);
        Cart_item cart_item1 = new Cart_item(item, topping, bottom, 2);

        assertEquals(cart_item.equals(cart_item1), true);
    }
}