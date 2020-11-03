package entries.shipping_payment_order;

import entries.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ThankYouForYourOrder", urlPatterns = { "/ThankYouForYourOrder" } )
public class ThankYouForYourOrder extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        render("Thank you!", "/WEB-INF/pages/thank_you_for_your_order.jsp", request, response);
    }
}
