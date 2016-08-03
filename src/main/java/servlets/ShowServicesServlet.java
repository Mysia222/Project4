package servlets;

import ent.Service;
import model.ServService;

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
 * Created by Славик on 31.07.2016.
 */
@WebServlet("/secure/Controller2")
public class ShowServicesServlet extends HttpServlet {

    /**
     * This method sets attributes on ServiceListEdit.jsp and redirect there
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            List<Service> list = ServService.getInstance().getServiceList();
            request.setAttribute("services", list);
        request.getRequestDispatcher("/view/ServiceListEdit.jsp").forward(request, response);
    }
}
