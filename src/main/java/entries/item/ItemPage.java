package entries.item;

import core.Bottom;
import core.Item;
import core.Topping;
import core.User;
import domain.bottom.NoBottomListInDB;
import domain.item.NoItemWithThatId;
import domain.topping.NoToppingListInDB;
import entries.BaseServlet;
import infrastructure.DBexception;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet(name = "ItemPage", urlPatterns = { "/ItemPage" } )
public class ItemPage extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //Parameters from the AddToCart servlet.
        String errorMessage;
        if(req.getParameter("errorMessage") != null){
            errorMessage = req.getParameter("errorMessage");
            req.setAttribute("errorMessage", errorMessage);
        }

        int item_id = Integer.parseInt(req.getParameter("item_id"));

        if(item_id > 0){
            //Get item, topping and bottom list from DB
            try{
                Item item = api.getItemById(item_id);
                ArrayList<Topping> listOfToppings = api.getAllToppingFromDB();
                ArrayList<Bottom> listOfBottoms = api.getAllBottomFromDB();

                req.setAttribute("item", item);
                req.setAttribute("listOfToppings", listOfToppings);
                req.setAttribute("listOfBottoms", listOfBottoms);

                redirect(req, resp);

            } catch (NoItemWithThatId noItemWithThatId) {
                noItemWithThatId.printStackTrace();
                errorMessage = "Error getting item: " + Arrays.toString(noItemWithThatId.getStackTrace());
                redirectWithError(errorMessage, req, resp);

            } catch (DBexception dBexception) {
                dBexception.printStackTrace();
                errorMessage = "DB exception: " + Arrays.toString(dBexception.getStackTrace());
                redirectWithError(errorMessage, req, resp);

            } catch (NoToppingListInDB noToppingListInDB) {
                noToppingListInDB.printStackTrace();
                errorMessage = "Error getting Toppings: " + Arrays.toString(noToppingListInDB.getStackTrace());
                redirectWithError(errorMessage, req, resp);

            } catch (NoBottomListInDB noBottomListInDB) {
                noBottomListInDB.printStackTrace();
                errorMessage = "Error getting Bottoms: " + Arrays.toString(noBottomListInDB.getStackTrace());
                redirectWithError(errorMessage, req, resp);
            }

        } else {
            errorMessage = "Error getting product id: " + item_id;
            redirectWithError(errorMessage, req, resp);
        }
    }

    private void redirect (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        render("Product Page", "/WEB-INF/pages/product_page.jsp", req, resp);
    }

    private void redirectWithError (String errorMessageProduct, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("errorMessageProduct", errorMessageProduct);
        render("Product Page", "/WEB-INF/pages/product_page.jsp", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


}
