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

@WebServlet(name = "EditCustomer", urlPatterns = { "/EditCustomer" } )
public class EditCustomer extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String adminMenu, errorMessage;

        int user_id = Integer.parseInt(request.getParameter("user_id"));

        try {
            User userToEdit = api.getUserById(user_id);
            request.setAttribute("userToEdit", userToEdit);
            adminMenu = "editCustomer";
            request.setAttribute("adminMenu", adminMenu);
            render("Shopping Cart", "/WEB-INF/pages/adminpage.jsp", request, response);

        } catch (DBexception dBexception) {
            errorMessage = "DB error";
            request.setAttribute("errorMessage", errorMessage);
            adminMenu = "editCustomer";
            request.setAttribute("adminMenu", adminMenu);
            render("Shopping Cart", "/WEB-INF/pages/adminpage.jsp", request, response);
        }
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int user_id = Integer.parseInt(req.getParameter("user_id"));
        String user_email = req.getParameter( "user_email" );
        String user_role = req.getParameter( "user_role" );
        double user_credit = Double.parseDouble(req.getParameter( "user_credit" ));


        if(user_email.length() == 0  | user_role.length() == 0 ){
            //We redirect back to the Servlet "AdminPage" with a get request
            resp.sendRedirect(req.getContextPath() + "/AdminPage?target=editCustomer&update=emptyFields&user_id=" + user_id);

        } else {
            try{
                //We dont return a user, because we don't need the data
                api.updateUserById(user_id, user_role, user_credit);
                //We redirect back to the Servlet "AdminPage" with a get request
                resp.sendRedirect(req.getContextPath() + "/AdminPage?target=editCustomer&update=true&user_id=" + user_id);

            }  catch (LoginSampleException e) {
                //We redirect back to the Servlet "AdminPage" with a get request
                resp.sendRedirect(req.getContextPath() + "/AdminPage?target=editCustomer&update=false&user_id=" + user_id);
            }
        }
    }


}
