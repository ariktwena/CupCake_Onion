package entries.admin;

import entries.BaseServlet;
import infrastructure.LoginSampleException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BulkHandlerOrders", urlPatterns = { "/BulkHandlerOrders" } )
public class BulkHandlerOrders extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int order_id;
        String errorMessage, successMessage;

        String selector_option = request.getParameter("selector_option");
        String[] bulkArrayOrderId = request.getParameterValues("bulkArrayOrderId");

        if (bulkArrayOrderId != null && bulkArrayOrderId.length > 0) {
            switch (selector_option) {
                case "pending":
                    try {
                        //we loop through the array with the user id's we want to change
                        for (String currentElement : bulkArrayOrderId) {
                            order_id = Integer.parseInt(currentElement);
                            api.updatedOrderToPending(order_id);
                        }

                        successMessage = "Selected were successfully converted to 'pending'";
                        response.sendRedirect(request.getContextPath() + "/AdminPage?target=allOrders&successMessage=" + successMessage);

                    } catch (LoginSampleException e) {
                        errorMessage = "DB error " + e;
                        //We create a incomplete user to hold the variables to we can reset the edit page
                        response.sendRedirect(request.getContextPath() + "/AdminPage?target=allOrders&errorMessage=" + errorMessage);
                    }
                    break;
                case "delivered":
                    try {
                        //we loop through the array with the user id's we want to change
                        for (String currentElement : bulkArrayOrderId) {
                            order_id = Integer.parseInt(currentElement);
                            api.updatedOrderToDelivered(order_id);
                        }

                        successMessage = "Selected were successfully converted to 'delivered'";
                        response.sendRedirect(request.getContextPath() + "/AdminPage?target=allOrders&successMessage=" + successMessage);

                    } catch (LoginSampleException e) {
                        errorMessage = "DB error " + e;
                        response.sendRedirect(request.getContextPath() + "/AdminPage?target=allOrders&errorMessage=" + errorMessage);
                    }
                    break;
                case "delete":
                    try {
                        //we loop through the array with the user id's we want to change
                        for (String currentElement : bulkArrayOrderId) {
                            order_id = Integer.parseInt(currentElement);
                            api.deleteOrderById(order_id);
                        }

                        successMessage = "Selected were successfully deleted.";
                        response.sendRedirect(request.getContextPath() + "/AdminPage?target=allOrders&successMessage=" + successMessage);

                    } catch (LoginSampleException e) {
                        errorMessage = "DB error " + e;
                        //We create a incomplete user to hold the variables to we can reset the edit page
                        response.sendRedirect(request.getContextPath() + "/AdminPage?target=allOrders&errorMessage=" + errorMessage);
                    }
                    break;
                default:
                    errorMessage = "Failed to execute bulk command";
                    response.sendRedirect(request.getContextPath() + "/AdminPage?target=allOrders&errorMessage=" + errorMessage);
            }
        } else {
            errorMessage = "Nothing was selected, please try again...";
            response.sendRedirect(request.getContextPath() + "/AdminPage?target=allOrders&errorMessage=" + errorMessage);
        }
    }
}
