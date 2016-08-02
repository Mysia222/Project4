package servlets;

import main.command.Command;
import main.command.CommandList;
import main.resources.View;
import main.resources.ViewINPUT;
import main.resources.ViewURL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by potaychuk on 02.08.2016.
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType ("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String commandName = request.getParameter("command");
        Command command = CommandList.valueOf(commandName).getCommand();
        String goTo = command.execute(request, response);
        request.getRequestDispatcher(goTo).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType ("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        request.setAttribute(View.PASSWORD_PAGE, View.PASSWORD);
        request.setAttribute(View.LOGIN_PAGE, View.LOGIN);
        request.setAttribute(View.CREATE_ACCOUNT_PAGE, View.CREATE_ACCOUNT);
        request.setAttribute(View.ENTER_PAGE, View.ENTER);
        request.getRequestDispatcher(ViewURL.INDEX_JSP).forward(request, response);
    }
}
