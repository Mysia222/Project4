package controller;

import command.Command;
import command.CommandList;
import views.View;
import views.ViewURL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

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
        String commandName = request.getParameter("command");
        Command command = CommandList.valueOf(commandName).getCommand();
        String goTo = command.execute(request, response);
        request.getRequestDispatcher(goTo).forward(request, response);
    }
}
