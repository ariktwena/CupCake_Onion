package entries.admin;

import core.User;
import domain.user.UserExists;
import entries.BaseServlet;
import infrastructure.DBexception;
import infrastructure.LoginSampleException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BulkHandlerUsers", urlPatterns = { "/BulkHandlerUsers" } )
public class BulkHandlerUsers extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int user_id;
        String errorMessage, successMessage;

        String selector_option = request.getParameter("selector_option");
        String[] bulkArrayUserId = request.getParameterValues("bulkArrayUserId");

        if (bulkArrayUserId != null && bulkArrayUserId.length > 0) {
            switch (selector_option) {
                case "customer":
                    try {
                        //we loop through the array with the user id's we want to change
                        for (String currentElement : bulkArrayUserId) {
                            user_id = Integer.parseInt(currentElement);
                            api.changeUserRoleToCustomer(user_id);
                        }

                        successMessage = "Selected were successfully converted to 'Customer'";
                        response.sendRedirect(request.getContextPath() + "/AdminPage?target=allCustomers&successMessage=" + successMessage);

                    } catch (LoginSampleException e) {
                        errorMessage = "DB error " + e;
                        //We create a incomplete user to hold the variables to we can reset the edit page
                        response.sendRedirect(request.getContextPath() + "/AdminPage?target=allCustomers&errorMessage=" + errorMessage);
                    }
                    break;
                case "admin":
                    try {
                        //we loop through the array with the user id's we want to change
                        for (String currentElement : bulkArrayUserId) {
                            user_id = Integer.parseInt(currentElement);
                            api.changeUserRoleToAdmin(user_id);
                        }

                        successMessage = "Selected were successfully converted to 'Admin'";
                        response.sendRedirect(request.getContextPath() + "/AdminPage?target=allCustomers&successMessage=" + successMessage);

                    } catch (LoginSampleException e) {
                        errorMessage = "DB error " + e;
                        response.sendRedirect(request.getContextPath() + "/AdminPage?target=allCustomers&errorMessage=" + errorMessage);
                    }
                    break;
                case "delete":
                    try {
                        //we loop through the array with the user id's we want to change
                        for (String currentElement : bulkArrayUserId) {
                            user_id = Integer.parseInt(currentElement);
                            api.deleteUserById(user_id);
                        }

                        successMessage = "Selected were successfully deleted.";
                        response.sendRedirect(request.getContextPath() + "/AdminPage?target=allCustomers&successMessage=" + successMessage);

                    } catch (LoginSampleException e) {
                        errorMessage = "DB error " + e;
                        //We create a incomplete user to hold the variables to we can reset the edit page
                        response.sendRedirect(request.getContextPath() + "/AdminPage?target=allCustomers&errorMessage=" + errorMessage);
                    }
                    break;
                default:
                    errorMessage = "Failed to execute bulk command";
                    response.sendRedirect(request.getContextPath() + "/AdminPage?target=allCustomers&errorMessage=" + errorMessage);
            }
        } else {
            errorMessage = "Nothing was selected, please try again...";
            response.sendRedirect(request.getContextPath() + "/AdminPage?target=allCustomers&errorMessage=" + errorMessage);
        }
    }
}
