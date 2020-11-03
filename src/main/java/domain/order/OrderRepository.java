package domain.order;

import core.Order;
import infrastructure.DBexception;

import java.util.ArrayList;

public interface OrderRepository {

    Order getOrderById(int order_id) throws DBexception;

    ArrayList<Order> getAllOrders() throws DBexception;

    ArrayList<Order> getAllOrdersByUserId(int user_id) throws DBexception;
}
