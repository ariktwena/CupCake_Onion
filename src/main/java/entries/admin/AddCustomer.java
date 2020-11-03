package entries.admin;

import core.User;
import domain.user.UserExists;
import entries.BaseServlet;
import infrastructure.LoginSampleException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AddCustomer", urlPatterns = { "/AddCustomer" } )
public class AddCustomer extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Error message and destination page
        String errorMessage = "";
        String successMessage = "";

        String user_email = req.getParameter( "user_email" );
        String password = req.getParameter( "password" );
        String user_role = req.getParameter( "user_role" );
        double user_credit = Double.parseDouble(req.getParameter( "user_credit" ));


        if(user_email.length() == 0  | password.length() == 0  | user_role.length() == 0 ){
            errorMessage = "Fields can't be empty...";
            resp.sendRedirect(req.getContextPath() + "/AdminPage?target=addCustomer&errorMessage=" + errorMessage);

        } else {
            try{
                User createdUser = api.createUserFromAdminPage(user_email, password, user_role, user_credit);

                successMessage = "User: " + createdUser.getUserEmail() + " was successfully created!";
                req.setAttribute("successMessage", successMessage);

                resp.sendRedirect(req.getContextPath() + "/AdminPage?target=addCustomer&successMessage=" + successMessage);

            } catch (UserExists e) {
                errorMessage = "User already exist in our system";
                resp.sendRedirect(req.getContextPath() + "/AdminPage?target=addCustomer&errorMessage=" + errorMessage);

            } catch (LoginSampleException e) {
                errorMessage = "DB error";
                resp.sendRedirect(req.getContextPath() + "/AdminPage?target=addCustomer&errorMessage=" + errorMessage);
            }
        }
    }

}
