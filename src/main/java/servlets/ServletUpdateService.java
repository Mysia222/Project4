package servlets;

import main.ent.Service;
import model.ServService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Славик on 31.07.2016.
 */
@WebServlet("/secure/UpdateService")
public class ServletUpdateService extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Service service = new Service(request.getParameter("name"), Double.valueOf(request.getParameter("price")),Integer.valueOf(request.getParameter("id")));
        ServService.getInstance().update(service);
        request.getRequestDispatcher("/secure/Controller2").forward(request, response);

    }
}
