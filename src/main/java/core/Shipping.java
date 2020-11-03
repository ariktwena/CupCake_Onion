package core;

public class Shipping {

    private final int shipping_id;
    private final String shipping_address;
    private final int shipping_zip;
    private final String shipping_city;
    private final User user;

    public Shipping(int shipping_id, String shipping_address, int shipping_zip, String shipping_city, User user) {
        this.shipping_id = shipping_id;
        this.shipping_address = shipping_address;
        this.shipping_zip = shipping_zip;
        this.shipping_city = shipping_city;
        this.user = user;
    }

    public Shipping(String shipping_address, int shipping_zip, String shipping_city, User user) {
        this.shipping_id = -1;
        this.shipping_address = shipping_address;
        this.shipping_zip = shipping_zip;
        this.shipping_city = shipping_city;
        this.user = user;
    }

    //Is used when we get orders from the DB where we don't need all the topping info's
    public Shipping(String shipping_address, int shipping_zip, String shipping_city) {
        this.shipping_id = -1;
        this.shipping_address = shipping_address;
        this.shipping_zip = shipping_zip;
        this.shipping_city = shipping_city;
        this.user = null;
    }

    public Shipping shippingWhitId(int shipping_id){
        return new Shipping(shipping_id, this.shipping_address, this.shipping_zip, this.shipping_city, this.user);
    }

    public int getShipping_id() {
        return shipping_id;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    public int getShipping_zip() {
        return shipping_zip;
    }

    public String getShipping_city() {
        return shipping_city;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Shipping{" +
                "shipping_id=" + shipping_id +
                ", shipping_adress='" + shipping_address + '\'' +
                ", shipping_zip=" + shipping_zip +
                ", shipping_city='" + shipping_city + '\'' +
                ", user=" + user +
                '}';
    }
}
