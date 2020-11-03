package core;

public class Item {
    private final int item_id;
    private final String item_name;
    private final String item_des;
    private final double item_price;
    private final String item_image;

    public Item(int item_id, String item_name, String item_des, double item_price, String item_image) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_des = item_des;
        this.item_price = item_price;
        this.item_image = item_image;
    }

    //Is used when we get orders from the DB where we don't need all the item info's
    public Item(String item_name, double item_price, String item_image) {
        this.item_id = -1;
        this.item_name = item_name;
        this.item_des = "";
        this.item_price = item_price;
        this.item_image = item_image;
    }

    public int getItem_id() {
        return item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getItem_des() {
        return item_des;
    }

    public double getItem_price() {
        return item_price;
    }

    public String getItem_image() {
        return item_image;
    }

    @Override
    public String toString() {
        return "Item{" +
                "item_id=" + item_id +
                ", item_name='" + item_name + '\'' +
                ", item_des='" + item_des + '\'' +
                ", item_price=" + item_price +
                ", item_image='" + item_image + '\'' +
                '}';
    }
}
