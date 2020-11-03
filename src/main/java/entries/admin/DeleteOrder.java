package entries.admin;

import entries.BaseServlet;
import infrastructure.LoginSampleException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteOrder", urlPatterns = { "/DeleteOrder" } )
public class DeleteOrder extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String errorMessage = "";
        String successMessage = "";

        int order_id = Integer.parseInt(request.getParameter("order_id"));


        if(order_id == 0){
            errorMessage = "Order was not deleted..";
            //We create a incomplete user to hold the variables to we can reset the edit page
            response.sendRedirect(request.getContextPath() + "/AdminPage?target=allOrders&errorMessage=" + errorMessage);

        }else {
            try{
                //We don't return a order, because we don't need the data
                api.deleteOrderById(order_id);

                successMessage = "Order with ID: " + order_id + " was deleted.";
                request.setAttribute("successMessage", successMessage);

                response.sendRedirect(request.getContextPath() + "/AdminPage?target=allOrders&successMessage=" + successMessage);

            }  catch (LoginSampleException e) {
                errorMessage = "DB error " + e;
                response.sendRedirect(request.getContextPath() + "/AdminPage?target=allOrders&errorMessage=" + errorMessage);
            }

        }
    }

}
