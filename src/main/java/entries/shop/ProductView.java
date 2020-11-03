package entries.shop;

import core.Item;
import entries.BaseServlet;
import infrastructure.DBexception;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ProductView", urlPatterns = { "/ProductView" } )
public class ProductView extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            ArrayList<Item> getAllItemsFromDB = api.getAllItemsFromDB();
            req.setAttribute("getAllItemsFromDB", getAllItemsFromDB);
        } catch (DBexception dBexception) {
            dBexception.printStackTrace();
        }

        render("Our CupCakes", "/WEB-INF/pages/product_view.jsp", req, resp);
    }
}
