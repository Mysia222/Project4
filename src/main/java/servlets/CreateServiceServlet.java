package servlets;

import main.ent.Service;
import model.ServService;
import model.SubService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Славик on 31.07.2016.
 */
@WebServlet("/secure/CreateService")
public class CreateServiceServlet extends HttpServlet {
    /**
     * This method creates new Service and redirects to Admin.jsp
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServService.getInstance().create(new Service(request.getParameter("name"), Double.valueOf(request.getParameter("price")),0));
        request.getRequestDispatcher("/view/Admin.jsp").forward(request, response);
    }

    /**
     * This method redirects to crating form ServiceCreate.jsp
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/ServiceCreate.jsp").forward(request, response);
    }
}
