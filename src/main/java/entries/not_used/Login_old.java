//package entries.not_used;
//
//import api.Api;
//import core.User;
//import domain.user.InvalidPassword;
//import infrastructure.Database;
//import infrastructure.LoginSampleException;
//import infrastructure.dbuser.DBUserFactory;
//import infrastructure.dbuser.DBUserRepository;
//import infrastructure.dbuser.DBUserServices;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@WebServlet(name = "Login_old", urlPatterns = { "/Login_old" } )
//public class Login_old extends HttpServlet {
//
//    protected static final Api api;
//
//    static {
//        api = createCupCakeApi();
//    }
//
//    private static Api createCupCakeApi(){
//        Database database = new Database();
//        return new Api(new DBUserFactory(database), new DBUserRepository(database), new DBUserServices(database), itemRepository);
//    }
//
////    @Override
////    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
////            throws ServletException, IOException {
////        render("The CupCake Shop", "/WEB-INF/index.jsp", req, resp);
////
////    }
//
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
//    }
//
////    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //Error message and destination page
//        String messageSignIn = "";
//        String errorMessage = "";
//
//        String user_email = request.getParameter( "email" );
//        String password = request.getParameter( "password" );
//        String checkoutProcess = request.getParameter("checkoutProcess");
//
//        //Check the username and password that is provided, and the user from DB
//        try {
//
//            User user = api.login(user_email, password);
//
//            HttpSession session = request.getSession();
//
//            //Set the logged in user in a session
//            session.setAttribute("user", user);
////            String loggedIn = "true";
////            session.setAttribute("loggedIn", loggedIn);
//
//            //Redirect by user_role and get session data
//            if(user.getUserRole().equals("admin")){
//                if(checkoutProcess.equals("true")){
//
//                    //PAYMENT PAGE
//
//                } else {
//                    response.sendRedirect(request.getContextPath() + "/AdminPage");
////                    request.getRequestDispatcher("/WEB-INF/adminpage.jsp").forward(request, response);
//                }
//
//            } else if (user.getUserRole().equals("customer")){
//                if(checkoutProcess.equals("true")){
//
//                    //PAYMENT PAGE
//
//                } else {
//                    request.getRequestDispatcher("/WEB-INF/profilepage.jsp").forward(request, response);
//                }
//
//            } else {
//                messageSignIn = "Unknown username or password";
//                redirectWithMessage(checkoutProcess, messageSignIn, request, response);
//            }
//
//
//        } catch(NullPointerException | InvalidPassword e){
//            //Unknown username or password
//            messageSignIn = "Unknown username or password";
//            redirectWithMessage(checkoutProcess, messageSignIn, request, response);
//        } catch (LoginSampleException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void redirectWithMessage(String checkoutProcess, String messageSignIn, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        if(checkoutProcess.equals("true")){
//            request.setAttribute("messageSignIn", messageSignIn);
//            request.getRequestDispatcher("/WEB-INF/login_register.jsp").forward(request, response);
//        } else {
//            request.setAttribute("messageSignUp", messageSignIn);
//            request.getRequestDispatcher("Register").forward(request, response);
//        }
//    }
//
//
//}
