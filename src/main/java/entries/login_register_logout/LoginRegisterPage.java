package entries.login_register_logout;

import core.User;
import entries.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginRegisterPage", urlPatterns = { "/LoginRegisterPage" } )
public class LoginRegisterPage extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if(request.getParameter("user_id").equals("")){
            String checkoutProcess = "true";
            request.setAttribute("checkoutProcess", checkoutProcess);
            render("Login/Register Page", "/WEB-INF/pages/login_register.jsp", request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/CheckoutPage");
        }



    }
}
