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
import java.util.ArrayList;

@WebServlet(name = "DeleteCustomer", urlPatterns = { "/DeleteCustomer" } )
public class DeleteCustomer extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String errorMessage = "";
        String successMessage = "";

        int user_id = Integer.parseInt(request.getParameter("user_id"));


        if(user_id == 0){
            errorMessage = "User was not deleted..";
            //We create a incomplete user to hold the variables to we can reset the edit page
            response.sendRedirect(request.getContextPath() + "/AdminPage?target=allCustomers&errorMessage=" + errorMessage);

        }else {
            try{
                //We don't return a user, because we don't need the data
                api.deleteUserById(user_id);

                successMessage = "User with ID: " + user_id + " was deleted.";
                request.setAttribute("successMessage", successMessage);

                response.sendRedirect(request.getContextPath() + "/AdminPage?target=allCustomers&successMessage=" + successMessage);

            }  catch (LoginSampleException e) {
                errorMessage = "DB error " + e;
                response.sendRedirect(request.getContextPath() + "/AdminPage?target=allCustomers&errorMessage=" + errorMessage);
            }

        }
    }

}
