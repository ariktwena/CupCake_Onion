package entries.order;

import core.Order;
import entries.BaseServlet;
import infrastructure.DBexception;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ViewOrder", urlPatterns = { "/ViewOrder" } )
public class ViewOrder extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int order_id = Integer.parseInt(req.getParameter("order_id"));

        if(req.getParameter("role") != null && req.getParameter("role").equals("admin")){
            try{
                Order order = api.getOrderById(order_id);
                req.setAttribute("order", order);

                String adminMenu = "viewOrder";
                req.setAttribute("adminMenu", adminMenu);
                render("Admin Page", "/WEB-INF/pages/adminpage.jsp", req, resp);
            } catch (DBexception dBexception) {
                dBexception.printStackTrace();
            }

        } else if (req.getParameter("role") != null && req.getParameter("role").equals("customer")){
            try{
                Order order = api.getOrderById(order_id);
                req.setAttribute("order", order);

                String profileMenu = "viewOrder";
                req.setAttribute("profileMenu", profileMenu);
                render("Admin Page", "/WEB-INF/pages/profilepage.jsp", req, resp);
            } catch (DBexception dBexception) {
                dBexception.printStackTrace();
            }
        }
    }
}
