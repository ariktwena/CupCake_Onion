package core;

import core.Cart_item;

import java.util.ArrayList;

public class Cart {

    private volatile ArrayList<Cart_item> listOfCartItems;

    public Cart() {
        this.listOfCartItems = new ArrayList<>();
    }

    //Is used when we get orders from the DB
    public Cart(ArrayList<Cart_item> listOfCartItems) {
        this.listOfCartItems = listOfCartItems;
    }

    public ArrayList<Cart_item> getListOfCartItems() {
        return listOfCartItems;
    }

    public void setListOfCartItems(ArrayList<Cart_item> listOfCartItems) {
        this.listOfCartItems = listOfCartItems;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "listOfItems=" + listOfCartItems +
                '}';
    }
}
