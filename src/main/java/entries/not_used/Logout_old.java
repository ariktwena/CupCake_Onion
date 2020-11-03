//package entries.not_used;
//
//import core.User;
//import entries.BaseServlet;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.io.PrintStream;
//import java.util.logging.Handler;
//
//@WebServlet(name = "Logout", urlPatterns = { "/Logout" } )
//public class Logout extends BaseServlet {
//
//    //******* Doesn't work :( ******/
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//        User user = new User(0, "", "", 0);
//
//        HttpSession session = req.getSession();
//        session.setAttribute("user", user);
//        render("The CupCake Shop", "/WEB-INF/index.jsp", req, resp);
//    }
//
////    @Override
////    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
////            throws ServletException, IOException {
////        User user = new User(0, "", "", 0);
////
////        HttpSession session = req.getSession();
////        session.setAttribute("user", user);
////        render("The CupCake Shop", "/WEB-INF/index.jsp", req, resp);
////    }
//
//}
