package core;

public class Topping {
    private final int topping_id;
    private final String topping_name;
    private final double topping_price;

    public Topping(int topping_id, String topping_name, double topping_price) {
        this.topping_id = topping_id;
        this.topping_name = topping_name;
        this.topping_price = topping_price;
    }

    //Is used when we get orders from the DB where we don't need all the topping info's
    public Topping(String topping_name, double topping_price) {
        this.topping_id = -1;
        this.topping_name = topping_name;
        this.topping_price = topping_price;
    }

    public int getTopping_id() {
        return topping_id;
    }

    public String getTopping_name() {
        return topping_name;
    }

    public double getTopping_price() {
        return topping_price;
    }
}
