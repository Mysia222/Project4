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
        try {
            sub = TelService.getInstance().getSub(request);
            request.setAttribute("balance", sub.getBalance());
            request.setAttribute("contract", sub.getContract());
            request.setAttribute("fName", sub.getInfo().getFirstName());
            request.setAttribute("sName", sub.getInfo().getSecondName());
            request.setAttribute("pass", sub.getInfo().getPassword());
            request.setAttribute("log", sub.getInfo().getLogin());
//            request.setAttribute("contract", sub.getContract());
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
        dispatcher.forward(request,response);

//        String login = request.getParameter("login");
//        String password = request.getParameter("password");
//        try {
//            TelService.getInstance().getBalance(request);
//        } catch (NamingException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
