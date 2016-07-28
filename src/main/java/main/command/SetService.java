package main.command;

import main.ent.Service;
import main.ent.Subscriber;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Славик on 27.07.2016.
 */
public class SetService implements Command {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Subscriber sub = (Subscriber) request.getSession().getAttribute("sub");
//        if (sub.isBlocked()){
//            request.setAttribute("blocked", "STATUS: BLOCKED");
//            return "/cabinet";
//        }
        int id = Integer.parseInt(request.getParameter("id"));
        Service service=telService.getService(id);
        sub.setCurrentService(service.getName());
        sub.setBalance(sub.getBalance()-service.getPrice());
        telService.setSub(sub);
        request.getSession().setAttribute("sub",sub);
        return "/cabinet";
    }
}
