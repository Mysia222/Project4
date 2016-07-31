package main.command;

import main.ent.Subscriber;
import main.resources.View;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Славик on 17.07.2016.
 */
public class CreateUser implements Command{
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Subscriber sub = subService.subByLog(request.getParameter(View.QUERY_LOGIN));

        if(sub==null) {
            Subscriber subscriber = new Subscriber();
            subscriber.setInfo(subscriber.new SubInfo());
            subscriber.getInfo().setFirstName(request.getParameter(View.QUERY_F_NAME));
            subscriber.getInfo().setSecondName(request.getParameter(View.QUERY_S_NAME));
            subscriber.getInfo().setLogin(request.getParameter(View.QUERY_LOGIN));
            subscriber.getInfo().setPassword(request.getParameter(View.QUERY_PASSWORD));
            subService.create(subscriber);
        }else {
            request.setAttribute("loginInUse", "Login is already in use");
            return "/view/CreateSub.jsp";
        }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }


        return "/cabinet";
    }
}
