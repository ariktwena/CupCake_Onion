package domain.order;

import infrastructure.LoginSampleException;

public interface OrderServices {

    void deleteOrderById(int order_id) throws LoginSampleException;

    void updatedOrderToPending(int order_id) throws LoginSampleException;

    void updatedOrderToDelivered(int order_id) throws LoginSampleException;
}
