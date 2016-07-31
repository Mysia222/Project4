package main.command;

import main.ent.Subscriber;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Славик on 23.07.2016.
 */
public class SaveInfo implements Command {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String fName = request.getParameter("first_name");
        String sName = request.getParameter("second_name");
        String pass = request.getParameter("password");
        String log = request.getParameter("login");
        Subscriber sub = (Subscriber) request.getSession().getAttribute("sub");
        sub.getInfo().setLogin(log);
        sub.getInfo().setPassword(pass);
        sub.getInfo().setFirstName(fName);
        sub.getInfo().setSecondName(sName);
        request.getSession().setAttribute("sub", sub);
        subService.setSub(sub);
        return "/cabinet";
    }
}
