package command;

import ent.Subscriber;
import views.View;
import views.ViewURL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by potaychuk on 03.08.2016.
 */
public class UserDirectInfo implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Subscriber sub = (Subscriber)request.getSession().getAttribute(View.SUBSCRIBER_SESSION);
        if(request.getParameter(View.DIRECT_INFO_BUTTON).equals(View.DIRECT_INFO)){
            request.setAttribute(View.USER_INFO_PAGE, View.USER_INFO);
            request.setAttribute(View.FIRST_NAME_PAGE_H, View.FIRST_NAME);
            request.setAttribute(View.SECOND_NAME_PAGE_H, View.SECOND_NAME);
            request.setAttribute(View.LOGIN_PAGE_H, View.LOGIN);
            request.setAttribute(View.PASSWORD_PAGE_H, View.PASSWORD);
            request.setAttribute(View.PASSWORD_PAGE_H, View.PASSWORD);
            request.setAttribute(View.CREATE_PAGE, View.CREATE);
            request.setAttribute(View.SAVE_PAGE, View.SAVE);
            return ViewURL.DIRECT_INFO_JSP;
        }else {
            String fName = request.getParameter(View.FIRST_NAME_PAGE);
            String sName = request.getParameter(View.SECOND_NAME_PAGE);
            String pass = request.getParameter(View.PASSWORD_PAGE);
            String log = request.getParameter(View.LOGIN_PAGE);
            sub.getInfo().setLogin(log);
            sub.getInfo().setPassword(pass);
            sub.getInfo().setFirstName(fName);
            sub.getInfo().setSecondName(sName);
            request.getSession().setAttribute(View.SUBSCRIBER_SESSION, sub);
            subService.setSub(sub);
            Command command = CommandList.valueOf(View.USER_CABINET).getCommand();
            return command.execute(request, response);
        }
    }
}
