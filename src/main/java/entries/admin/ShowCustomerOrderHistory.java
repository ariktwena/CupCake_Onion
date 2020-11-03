package entries.admin;

import core.Order;
import core.User;
import entries.BaseServlet;
import infrastructure.DBexception;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@WebServlet(name = "ShowCustomerOrderHistory", urlPatterns = { "/ShowCustomerOrderHistory" } )
public class ShowCustomerOrderHistory extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int user_id = Integer.parseInt(req.getParameter("user_id"));

        try{
            ArrayList<Order> allOrders = api.getAllOrdersByUserId(user_id);
            Collections.reverse(allOrders);
            req.setAttribute("allOrders", allOrders);
            String adminMenu = "orderHistory";
            req.setAttribute("adminMenu", adminMenu);
            render("Admin Page", "/WEB-INF/pages/adminpage.jsp", req, resp);
        } catch (DBexception dBexception) {
            dBexception.printStackTrace();
        }
    }
}
