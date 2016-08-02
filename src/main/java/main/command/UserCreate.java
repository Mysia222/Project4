package main.command;

import main.ent.Subscriber;
import main.resources.View;
import main.resources.ViewINPUT;
import main.resources.ViewURL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by potaychuk on 02.08.2016.
 */
public class UserCreate implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       if(request.getParameter(ViewINPUT.CREATE).equals(ViewINPUT.CREATE_NEW_LOGIN)){
           request.setAttribute("testSetAttribute", "asdasd");
           request.setAttribute("testSetAttribute1", ViewINPUT.PASS_KEY);
           request.setAttribute("testSetAttribute2", ViewINPUT.PASS);
           return ViewURL.CREATE_SUB_JSP;
       }
       Subscriber sub = subService.subByLog(request.getParameter(View.QUERY_LOGIN));
        if(sub==null) {
            Subscriber subscriber = new Subscriber();
            subscriber.setInfo(subscriber.new SubInfo());
            subscriber.getInfo().setFirstName(request.getParameter(View.QUERY_F_NAME));
            subscriber.getInfo().setSecondName(request.getParameter(View.QUERY_S_NAME));
            subscriber.getInfo().setLogin(request.getParameter(View.QUERY_LOGIN));
            subscriber.getInfo().setPassword(request.getParameter(View.QUERY_PASSWORD));
            subService.create(subscriber);
            return ViewURL.HOME_JSP;
        }else {
            request.setAttribute("loginInUse", "Login is already in use");
            return ViewURL.CREATE_SUB_JSP;
        }

    }
}
