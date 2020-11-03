package core;

import java.time.LocalDateTime;

public class Order {
    private final int order_id;
    private final String order_pay;
    private final String order_info;
    private final String order_time;
    private double order_total;
    private final String order_ship;
    private String order_status;
    private int order_ative;
    private final User user;
    private final Shipping shipping;
    private final Cart cart;

    public Order(int order_id, String order_pay, String order_info, String order_time, double order_total,
                 String order_ship, String order_status, int order_active, User user, Shipping shipping, Cart cart) {
        this.order_id = order_id;
        this.order_pay = order_pay;
        this.order_info = order_info;
        this.order_time = order_time;
        this.order_total = order_total;
        this.order_ship = order_ship;
        this.order_status = order_status;
        this.order_ative = order_active;
        this.user = user;
        this.shipping = shipping;
        this.cart = cart;
    }

    public Order(String order_pay, String order_info, String order_time, double order_total, String order_ship,
                 String order_status, int order_active, User user, Shipping shipping, Cart cart) {
        this.order_id = -1;
        this.order_pay = order_pay;
        this.order_info = order_info;
        this.order_time = order_time;
        this.order_total = order_total;
        this.order_ship = order_ship;
        this.order_status = order_status;
        this.order_ative = order_active;
        this.user = user;
        this.shipping = shipping;
        this.cart = cart;
    }

    //Is used when we get orders from the DB
    public Order(int order_id, String order_pay, String order_info, String order_time, double order_total, String order_ship,
                 User user, Shipping shipping, Cart cart) {
        this.order_id = order_id;
        this.order_pay = order_pay;
        this.order_info = order_info;
        this.order_time = order_time;
        this.order_total = order_total;
        this.order_ship = order_ship;
        this.order_status = "";
        this.order_ative = 1;
        this.user = user;
        this.shipping = shipping;
        this.cart = cart;
    }
    //Is used when we get orders from the DB
    public Order(int order_id, String order_pay, String order_time, double order_total, String order_ship,
                 String order_status, User user) {
        this.order_id = order_id;
        this.order_pay = order_pay;
        this.order_info = "";
        this.order_time = order_time;
        this.order_total = order_total;
        this.order_ship = order_ship;
        this.order_status = order_status;
        this.order_ative = 1;
        this.user = user;
        this.shipping = null;
        this.cart = null;
    }

    public Order orderWhitId(int order_id){
        return new Order(order_id, this.order_pay, this.order_info, this.order_time, this.order_total, this.order_ship,
                this.order_status, this.order_ative, this.user, this.shipping, this.cart);
    }

    public int getOrder_id() {
        return order_id;
    }

    public String getOrder_pay() {
        return order_pay;
    }

    public String getOrder_info() {
        return order_info;
    }

    public String getOrder_time() {
        return order_time;
    }

    public double getOrder_total() {
        return order_total;
    }

    public String getOrder_ship() {
        return order_ship;
    }

    public String getOrder_status() {
        return order_status;
    }

    public int getOrder_ative() {
        return order_ative;
    }

    public void setOrder_total(double order_total) {
        this.order_total = order_total;
    }

    public User getUser() {
        return user;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public Cart getCart() {
        return cart;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public void setOrder_ative(int order_ative) {
        this.order_ative = order_ative;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", order_pay='" + order_pay + '\'' +
                ", order_info='" + order_info + '\'' +
                ", order_time=" + order_time +
                ", order_status='" + order_status + '\'' +
                ", order_ative=" + order_ative +
                ", user=" + user +
                ", shipping=" + shipping +
                ", cart=" + cart +
                '}';
    }
}
