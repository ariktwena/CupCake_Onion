package core;

public class Bottom {
    private final int bottom_id;
    private final String bottom_name;
    private final double bottom_price;

    public Bottom(int bottom_id, String bottom_name, double bottom_price) {
        this.bottom_id = bottom_id;
        this.bottom_name = bottom_name;
        this.bottom_price = bottom_price;
    }

    //Is used when we get orders from the DB where we don't need all the topping info's
    public Bottom(String bottom_name, double bottom_price) {
        this.bottom_id = -1;
        this.bottom_name = bottom_name;
        this.bottom_price = bottom_price;
    }

    public int getBottom_id() {
        return bottom_id;
    }

    public String getBottom_name() {
        return bottom_name;
    }

    public double getBottom_price() {
        return bottom_price;
    }
}

