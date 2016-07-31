package main.command;

import main.ent.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Славик on 31.07.2016.
 */
public class ServiceChange implements Command {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("id", Integer.valueOf(request.getParameter("id")));
        return "/view/ServiceChange.jsp";
    }
}
