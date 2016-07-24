package servlets;

import main.command.Command;
import main.command.CommandList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Славик on 23.07.2016.
 */
@WebServlet("/T1Servlet")
public class T1Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String commandName = request.getParameter("command");
        Command command = CommandList.valueOf(commandName).getCommand();
        String goTo = command.execute(request, response);
        request.getRequestDispatcher(goTo).forward(request, response);
    }
}
