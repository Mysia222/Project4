package servlets;

import main.ent.Service;
import main.ent.Subscriber;
import model.ServService;
import model.SubService;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Славик on 30.07.2016.
 */
@WebServlet("/secure/Controller")
public class ShowSubsServlet extends HttpServlet {

    /**
     * This method sets attributes on UserList.jsp and redirect there
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Subscriber> list = SubService.getInstance().getSubsList();
            request.setAttribute("users", list);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
       request.getRequestDispatcher("/view/UserList.jsp").forward(request, response);
    }
}
