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

        User user = (User) request.getAttribute("user");

        if(user == null){

            if(request.getParameter("messageSignIn") != null){
                String messageSignIn = request.getParameter("messageSignIn");
                request.setAttribute("messageSignIn", messageSignIn);
            }
            if(request.getParameter("messageSignUp") != null){
                String messageSignUp = request.getParameter("messageSignUp");
                request.setAttribute("messageSignUp", messageSignUp);
            }

            String checkoutProcess = "true";
            request.setAttribute("checkoutProcess", checkoutProcess);
            render("Login/Register Page", "/WEB-INF/pages/login_register.jsp", request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/CheckoutPage");
        }



    }
}
