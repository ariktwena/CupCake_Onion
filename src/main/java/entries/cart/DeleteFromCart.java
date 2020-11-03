package entries.cart;

import core.Cart_item;
import entries.BaseServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "DeleteFromCart", urlPatterns = { "/DeleteFromCart" } )
public class DeleteFromCart extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String successMessage, errorMessage;
        String [] checkboxArray = req.getParameterValues("checkboxArray");
        ArrayList<Cart_item> shoppingCart = api.getShoppingCart();

        if(checkboxArray.length > 0){
            for(int i = checkboxArray.length - 1 ; i >= 0 ; i--){
                shoppingCart.remove(Integer.parseInt(checkboxArray[i]));
            }

            //Set shopping cart session
            HttpSession session = req.getSession();
            session.setAttribute("shoppingCart", shoppingCart);

            //Set total price in cart
            double cartTotal = 0;
            for(Cart_item currentElement: shoppingCart){
                cartTotal = cartTotal + currentElement.getSubTotal();
            }

            req.setAttribute("cartTotal", cartTotal);

            successMessage = "Selected cart items was deleted";
            req.setAttribute("successMessage", successMessage);
//            getServletContext().getRequestDispatcher("/Show_Cart").forward(req, resp);
            resp.sendRedirect(req.getContextPath() + "/Show_Cart?successMessage=" + successMessage);

        } else {
            errorMessage = "Nothing was selected, please try again...";
            req.setAttribute("errorMessage", errorMessage);
//            getServletContext().getRequestDispatcher("/Show_Cart").forward(req, resp);
            resp.sendRedirect(req.getContextPath() + "/Show_Cart?successMessage=" + errorMessage);
        }
    }
}
