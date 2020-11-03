package domain.order;

import core.Cart_item;
import core.Order;
import infrastructure.LoginSampleException;

public interface OrderFactory {
    Order createOrder(Order order) throws LoginSampleException;

    void createOrderDetails(Cart_item cart_item, int order_id) throws LoginSampleException;
}
