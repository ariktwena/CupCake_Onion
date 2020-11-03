package entries.shipping_payment_order;

import entries.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CheckoutPage", urlPatterns = { "/CheckoutPage" } )
public class CheckoutPage extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Parameter from the SubmitOrder Servlet
        if(request.getParameter("errorMessage") != null){
            String errorMessage = request.getParameter("errorMessage");
            request.setAttribute("errorMessage", errorMessage);
        }

        double cartTotal = api.getCartTotalAmount();
        request.setAttribute("cartTotal", cartTotal);

        render("Checkout Page", "/WEB-INF/pages/checkout.jsp", request, response);
    }


}
