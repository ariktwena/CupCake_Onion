//package entries.not_used;
//
//import api.Api;
//import core.User;
//import domain.user.InvalidPassword;
//import domain.user.UserExists;
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
//@WebServlet(name = "Register_old", urlPatterns = { "/Register_old" } )
//public class Register_old extends HttpServlet {
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
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        //Error message and destination page
//        String messageSignUp = "";
//        String destinationPage = "";
//
//        String user_email = request.getParameter( "email" );
//        String password1 = request.getParameter( "password1" );
//        String password2 = request.getParameter( "password2" );
//        String checkoutProcess = request.getParameter("checkoutProcess");
//
//        try{
//            User user = api.createUser(user_email, password1, password2);
//            HttpSession session = request.getSession();
//
//            //Set the created user in a session
//            session.setAttribute("user", user);
//
//            //Redirect by user_role and get session data
//            if(user.getUserRole().equals("admin")){
//                if(checkoutProcess.equals("true")){
//
//                    //PAYMENT PAGE
//
//                } else {
//                    destinationPage = "adminpage";
//                }
//
//            } else if (user.getUserRole().equals("customer")){
//                if(checkoutProcess.equals("true")){
//
//                    //PAYMENT PAGE
//
//                } else {
//                    destinationPage = "profilepage";
//                }
//
//            } else {
//                messageSignUp = "Something when wrong, please try again...";
//                redirectWithMessage(checkoutProcess, messageSignUp, request, response);
//            }
//
//        } catch (InvalidPassword e){
//            messageSignUp = "Passwords do not match";
//            redirectWithMessage(checkoutProcess, messageSignUp, request, response);
//
//        } catch (UserExists e) {
//            messageSignUp = "User already exist in our system";
//            redirectWithMessage(checkoutProcess, messageSignUp, request, response);
//
//        } catch (LoginSampleException e) {
//            messageSignUp = "DB error";
//            redirectWithMessage(checkoutProcess, messageSignUp, request, response);
//        }
//    }
//
//    private void redirectWithMessage(String checkoutProcess, String messageSignUp, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        if(checkoutProcess.equals("true")){
//            request.setAttribute("messageSignUp", messageSignUp);
//            request.getRequestDispatcher("/WEB-INF/login_register.jsp").forward(request, response);
//        } else {
//            request.setAttribute("messageSignUp", messageSignUp);
//            request.getRequestDispatcher("Register").forward(request, response);
//        }
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
//    }
//}
