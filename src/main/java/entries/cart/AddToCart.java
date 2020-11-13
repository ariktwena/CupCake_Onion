package entries.cart;


import core.Bottom;
import core.Cart_item;
import core.Item;
import core.Topping;
import domain.bottom.NoBottomListInDB;
import domain.bottom.NoBottomWithThatId;
import domain.item.NoItemWithThatId;
import domain.topping.NoToppingListInDB;
import domain.topping.NoToppingWithThatId;
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

@WebServlet(name = "AddToCart", urlPatterns = { "/AddToCart" } )
public class AddToCart extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Error message
        String errorMessage;

        int item_id = Integer.parseInt(request.getParameter("item_id"));
        int topping_id = Integer.parseInt(request.getParameter("topping_id"));
        int bottom_id = Integer.parseInt(request.getParameter("bottom_id"));
        int qty = Integer.parseInt(request.getParameter("qty"));

        try{
            //Get ID's
            Item item = api.getItemById(item_id);
            Topping topping = api.getToppingById(topping_id);
            Bottom bottom = api.getBottomById(bottom_id);

            //Get the sopping cart session
            HttpSession session = request.getSession();
            ArrayList<Cart_item> shoppingCart = (ArrayList<Cart_item>) session.getAttribute("shoppingCart");

            //Add to shopping cart
            Cart_item cart_item = new Cart_item(item, topping, bottom, qty);
            shoppingCart = api.addToShoppingCart(cart_item, shoppingCart);

            //Set shopping cart session
//            HttpSession session = request.getSession();
//            ArrayList<Cart_item> shoppingCart = api.getShoppingCart();
            session.setAttribute("shoppingCart", shoppingCart);

            response.sendRedirect(request.getContextPath() + "/ItemPage?item_id=" + item_id);

        } catch (NoItemWithThatId noItemWithThatId) {
            errorMessage = "Unknown Item_id: " + Arrays.toString(noItemWithThatId.getStackTrace());
            response.sendRedirect(request.getContextPath() + "/ItemPage?item_id=" + item_id + "&errorMessage=" +errorMessage);

        } catch (NoToppingWithThatId noToppingWithThatId) {
            errorMessage = "Unknown Topping_id: " + Arrays.toString(noToppingWithThatId.getStackTrace());
            response.sendRedirect(request.getContextPath() + "/ItemPage?item_id=" + item_id + "&errorMessage=" +errorMessage);

        } catch (DBexception dBexception) {
            errorMessage = "DB error: " + Arrays.toString(dBexception.getStackTrace());
            response.sendRedirect(request.getContextPath() + "/ItemPage?item_id=" + item_id + "&errorMessage=" +errorMessage);

        } catch (NoBottomWithThatId noBottomWithThatId) {
            errorMessage = "Unknown Bottom_id: " + Arrays.toString(noBottomWithThatId.getStackTrace());
            response.sendRedirect(request.getContextPath() + "/ItemPage?item_id=" + item_id + "&errorMessage=" +errorMessage);
        }
    }
}
