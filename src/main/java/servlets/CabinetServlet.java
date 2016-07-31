package servlets;

import main.ent.Subscriber;
import main.resources.View;
import model.SubService;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Славик on 17.07.2016.
 */
@WebServlet("/cabinet")
public class CabinetServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Subscriber subscriber = new Subscriber();

        if (request.getSession().getAttribute("sub")==null) {
            try {
                String log = request.getParameter("login");
                subscriber = SubService.getInstance().subByLog(log);
                if (subscriber.isAdmin()){
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/view/Admin.jsp");
                    dispatcher.forward(request,response);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (NamingException e1) {
                e1.printStackTrace();
            }
        }else {
            subscriber = (Subscriber) request.getSession().getAttribute("sub");
            if (subscriber.isAdmin()){
                RequestDispatcher dispatcher = request.getRequestDispatcher("/view/Admin.jsp");
                dispatcher.forward(request,response);
            }
            subscriber= SubService.getInstance().subByContract(subscriber.getContract());
        }

            request.setAttribute(View.LOGIN_L, View.LOGIN_B);
            request.setAttribute(View.PASSWORD_L, View.PASSWORD_B);
            request.setAttribute(View.QUERY_BALANCE, subscriber.getBalance());
            request.setAttribute(View.QUERY_CONTRACT, subscriber.getContract());
            request.setAttribute(View.QUERY_F_NAME, subscriber.getInfo().getFirstName());
            request.setAttribute(View.QUERY_S_NAME, subscriber.getInfo().getSecondName());
            request.setAttribute(View.QUERY_PASSWORD, subscriber.getInfo().getPassword());
            request.setAttribute(View.QUERY_LOGIN, subscriber.getInfo().getLogin());
            request.setAttribute(View.QUERY_SERVICE, subscriber.getCurrentService());
            if (subscriber.isBlocked()) {
                request.setAttribute(View.QUERY_BLOCKED, "STATUS BLOCKED!");
            }else {
                request.setAttribute(View.QUERY_BLOCKED, "STATUS: active!");
            }
            HttpSession session = request.getSession();
            session.setAttribute("sub", subscriber);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
        dispatcher.forward(request,response);
    }
}
