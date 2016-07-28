package servlets;

import main.ent.Subscriber;
import model.TelService;

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

import static model.TelService.*;

/**
 * Created by Славик on 17.07.2016.
 */
@WebServlet("/cabinet")
public class CabinetServlet extends HttpServlet {
    private static Subscriber sub;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setAttribute("balance", "2nd_servlet ");
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
//        dispatcher.forward(request,response);
    }

    public static Subscriber getSub() {
        return sub;
    }

    public static void setSub(Subscriber sub) {
        CabinetServlet.sub = sub;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Subscriber subscriber = new Subscriber();

        if (request.getSession().getAttribute("sub")==null) {

            try {
                String log = request.getParameter("login");
                subscriber = TelService.getInstance().subByLog(log);
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
        }
            request.setAttribute("balance", subscriber.getBalance());
            request.setAttribute("contract", subscriber.getContract());
            request.setAttribute("fName", subscriber.getInfo().getFirstName());
            request.setAttribute("sName", subscriber.getInfo().getSecondName());
            request.setAttribute("pass", subscriber.getInfo().getPassword());
            request.setAttribute("log", subscriber.getInfo().getLogin());
            request.setAttribute("service", subscriber.getCurrentService());
            if (subscriber.isBlocked()) {
                request.setAttribute("blocked", "STATUS BLOCKED!");
            }
            HttpSession session = request.getSession();
            session.setAttribute("sub", subscriber);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
        dispatcher.forward(request,response);
    }
}
