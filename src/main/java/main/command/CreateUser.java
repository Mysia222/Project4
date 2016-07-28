package main.command;

import main.ent.Subscriber;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Славик on 17.07.2016.
 */
public class CreateUser implements Command{
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Subscriber subscriber = new Subscriber();
        subscriber.getInfo().setFirstName(request.getParameter("fName"));
        subscriber.getInfo().setSecondName(request.getParameter("sName"));
        subscriber.getInfo().setLogin(request.getParameter("log"));
        subscriber.getInfo().setPassword(request.getParameter("pass"));
        telService.create(subscriber);




        return "/view/CreateSub.jsp";
    }
}
