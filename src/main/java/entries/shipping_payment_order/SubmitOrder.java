package entries.shipping_payment_order;

import api.Utils;
import core.*;
import entries.BaseServlet;
import infrastructure.DBexception;
import infrastructure.LoginSampleException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@WebServlet(name = "SubmitOrder", urlPatterns = { "/SubmitOrder" } )
public class SubmitOrder extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int zip;
        String address, city, order_ship;

        String shippingOption = req.getParameter("shippingOption");

        if(shippingOption.equals("store")){
            address = "Olsker road 45b";
            zip = 23445;
            city = "Olsker";
            order_ship = "shop";
            submitOrder(req, resp, address, zip, city, order_ship);

        } else {
            if(!req.getParameter("address").equals("") && Integer.parseInt(req.getParameter("zip")) > 999 && !req.getParameter("city").equals("")){
                //We excape the string with our Util method
                address = Utils.encodeHtml(req.getParameter("address"));
                zip = Integer.parseInt(req.getParameter("zip"));
                city = Utils.encodeHtml(req.getParameter("city"));
                order_ship = "delivery";
                submitOrder(req, resp, address, zip, city, order_ship);

            } else {
                String errorMessage = "Address fields can't be empty, and zip has to be greater then 999";
                resp.sendRedirect(req.getContextPath() + "/CheckoutPage?errorMessage=" + errorMessage);
            }
        }
    }

    private void submitOrder(HttpServletRequest req, HttpServletResponse resp, String address, int zip, String city, String order_ship){
        String paymentOption, extra_info;
        int user_id = Integer.parseInt(req.getParameter("user_id"));
        paymentOption = req.getParameter("paymentOption");
        //We excape the string with our Util method
        extra_info = Utils.encodeHtml(req.getParameter("extra_info"));
        String order_status = "pending";
        int order_active = 1;
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String order_time = localDateTime.format(format);
        HttpSession session = req.getSession();
        ArrayList<Cart_item> shoppingCart = (ArrayList<Cart_item>) session.getAttribute("shoppingCart");
        double order_total = api.getCartTotalAmount(shoppingCart);
        Cart cart = new Cart(shoppingCart);

        try {
            User user = api.getUserById(user_id);

            Shipping shipping = new Shipping(address, zip, city, user);

            shipping = api.createShipping(shipping);

            Order order = new Order(paymentOption, extra_info, order_time, order_total, order_ship, order_status, order_active, user, shipping, cart);

            //We create the order in the DB and get the correct order with ID back
            order = api.createOrder(order);

            //We insert the order details in to the DB with the correct order ID
            api.creatOrderDetails(order);

            //Subtract payment from user if payment is "credit"
            if(paymentOption.equals("credit")){
                double newCreditBalance = user.getUser_credit() - order.getOrder_total();
                user.setUser_credit(newCreditBalance);
                api.updateUserCreditBalance(user.getId(), user.getUser_credit());
            }

            //Reset shopping cart
            shoppingCart = new ArrayList<>();
//            api.resetCart(shoppingCart);
//            shoppingCart = api.getShoppingCart();

//            HttpSession session = req.getSession();
            session.setAttribute("order", order);
            session.setAttribute("shoppingCart", shoppingCart);
            session.setAttribute("user", user);

            resp.sendRedirect(req.getContextPath() + "/ThankYouForYourOrder");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (LoginSampleException e) {
            e.printStackTrace();
        } catch (DBexception dBexception) {
            dBexception.printStackTrace();
        }


    }
}
