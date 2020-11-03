package entries.shop;

import core.Bottom;
import core.Cart_item;
import core.Item;
import core.Topping;
import entries.BaseServlet;
import infrastructure.DBexception;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Shop", urlPatterns = { "/Shop" } )
public class Shop extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            ArrayList<Item> getAllItemsFromDB = api.getAllItemsFromDB();
            req.setAttribute("getAllItemsFromDB", getAllItemsFromDB);
        } catch (DBexception dBexception) {
            dBexception.printStackTrace();
        }

        render("The CupCake Shop", "/WEB-INF/pages/index.jsp", req, resp);
    }

}
