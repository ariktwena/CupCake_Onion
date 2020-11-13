package entries.shipping_payment_order;

import core.Cart_item;
import entries.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "CheckoutPage", urlPatterns = { "/CheckoutPage" } )
public class CheckoutPage extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Parameter from the SubmitOrder Servlet
        if(request.getParameter("errorMessage") != null){
            String errorMessage = request.getParameter("errorMessage");
            request.setAttribute("errorMessage", errorMessage);
        }

        HttpSession session = request.getSession();
        ArrayList<Cart_item> shoppingCart = (ArrayList<Cart_item>) session.getAttribute("shoppingCart");
        double cartTotal = api.getCartTotalAmount(shoppingCart);
        request.setAttribute("cartTotal", cartTotal);

        render("Checkout Page", "/WEB-INF/pages/checkout.jsp", request, response);
    }


}
