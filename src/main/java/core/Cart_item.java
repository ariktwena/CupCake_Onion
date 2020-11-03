package core;

import java.util.Objects;

public class Cart_item {

    private final Item item;
    private final Topping topping;
    private final Bottom bottom;
    private int qty;
    private double subTotal;

    public Cart_item(Item item, Topping topping, Bottom bottom, int qty) {
        this.item = item;
        this.topping = topping;
        this.bottom = bottom;
        this.qty = qty;
        this.subTotal = qty * (item.getItem_price() + topping.getTopping_price() + bottom.getBottom_price());
    }

    public Item getItem() {
        return item;
    }

    public Topping getTopping() {
        return topping;
    }

    public Bottom getBottom() {
        return bottom;
    }

    public int getQty() {
        return qty;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart_item cart_item = (Cart_item) o;
        return item.getItem_name().equals(cart_item.item.getItem_name()) &&
                topping.getTopping_name().equals(cart_item.topping.getTopping_name()) &&
                bottom.getBottom_name().equals(cart_item.bottom.getBottom_name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, topping, bottom);
    }

    @Override
    public String toString() {
        return "Cart_item{" +
                "item=" + item +
                ", topping=" + topping +
                ", bottom=" + bottom +
                ", qty=" + qty +
                ", subTotal=" + subTotal +
                '}';
    }
}
