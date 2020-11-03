package entries;

import api.Api;
import core.Cart;
import infrastructure.Database;
import infrastructure.dbbottoms.DBBottomRepository;
import infrastructure.dbitem.DBItemRepository;
import infrastructure.dborder.DBOrderFactory;
import infrastructure.dborder.DBOrderRepository;
import infrastructure.dborder.DBOrderServices;
import infrastructure.dbshipping.DBShippingFactory;
import infrastructure.dbtoppings.DBToppingRepository;
import infrastructure.dbuser.DBUserFactory;
import infrastructure.dbuser.DBUserRepository;
import infrastructure.dbuser.DBUserServices;
import infrastructure.local_cart.LocalCartFactory;
import infrastructure.local_cart.LocalCartRepository;
import infrastructure.local_cart.LocalCartServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BaseServlet extends HttpServlet {

    protected static final Api api;

    static {
        api = createCupCakeApi();
    }

    private static Api createCupCakeApi(){

        Database database = new Database();
        Cart cart = new Cart();

        return new Api(new DBUserFactory(database), new DBUserRepository(database), new DBUserServices(database),
                new DBItemRepository(database), new DBBottomRepository(database), new DBToppingRepository(database),
                new LocalCartFactory(cart), new LocalCartRepository(cart), new LocalCartServices(cart),
                new DBShippingFactory(database), new DBOrderFactory(database), new DBOrderRepository(database),
                new DBOrderServices(database));
    }

    protected void render(String title, String content, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("title", title);
        req.setAttribute("content", content);
        req.getRequestDispatcher("/WEB-INF/pages/base.jsp").forward(req, resp);

    }

}
