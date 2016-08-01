package servlets;

import main.resources.View;
import model.SubService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Potaychuk Sviatoslav on 11.07.2016.
 */
@WebServlet("/home2")
public class EnterServlet extends HttpServlet {

    /**
     * This method implements valid authentication
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String login = request.getParameter(View.PAGE_LOGIN);
        String password = request.getParameter(View.PAGE_PASSWORD);

        if(SubService.getInstance().existByLogPas(login,password)){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/cabinet");
            dispatcher.forward(request,response);
        }
        else {
            request.setAttribute("wrongLogin", View.WRONG_ENTER);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request,response);
        }
    }
}
