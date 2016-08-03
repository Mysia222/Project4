package command;

import ent.Subscriber;
import views.View;
import views.ViewURL;

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
        response.setContentType ("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

       if(request.getParameter(View.CREATE_ACCOUNT_PAGE).equals(View.CREATE_ACCOUNT)){
           request.setAttribute(View.SAVED_FIRST_NAME, "");
           request.setAttribute(View.SAVED_SECOND_NAME, "");
           request.setAttribute(View.SAVED_PASSWORD,"");
           request.setAttribute(View.USER_INFO_PAGE, View.USER_INFO);
           request.setAttribute(View.FIRST_NAME_PAGE, View.FIRST_NAME);
           request.setAttribute(View.SECOND_NAME_PAGE, View.SECOND_NAME);
           request.setAttribute(View.LOGIN_PAGE, View.LOGIN);
           request.setAttribute(View.PASSWORD_PAGE, View.PASSWORD);
           request.setAttribute(View.PASSWORD_PAGE, View.PASSWORD);
           request.setAttribute(View.CREATE_PAGE, View.CREATE);
           return ViewURL.CREATE_SUB_JSP;
       }
       Subscriber sub = subService.subByLog(request.getParameter(View.QUERY_LOGIN));

        if(sub==null) {
            sub=new Subscriber();
            sub.setInfo(sub.new SubInfo());
            sub.getInfo().setFirstName(request.getParameter(View.FIRST_NAME_PAGE));
            sub.getInfo().setSecondName(request.getParameter(View.SECOND_NAME_PAGE));
            sub.getInfo().setLogin(request.getParameter(View.LOGIN_PAGE));
            sub.getInfo().setPassword(request.getParameter(View.PASSWORD_PAGE));
            subService.create(sub);
            sub = subService.subByLog(sub.getInfo().getLogin());
            request.getSession().setAttribute(View.SUBSCRIBER_SESSION, sub);
            Command command = CommandList.valueOf(View.USER_CABINET).getCommand();
            return command.execute(request, response);
//
        }else {
            request.setAttribute(View.SAVED_FIRST_NAME, request.getParameter(View.FIRST_NAME_PAGE));
            request.setAttribute(View.SAVED_SECOND_NAME, request.getParameter(View.SECOND_NAME_PAGE));
            request.setAttribute(View.SAVED_PASSWORD, request.getParameter(View.PASSWORD_PAGE));
            request.setAttribute(View.LOGIN_IN_USE_PAGE, View.LOGIN_IN_USE);
            request.setAttribute(View.USER_INFO_PAGE, View.USER_INFO);
            request.setAttribute(View.FIRST_NAME_PAGE, View.FIRST_NAME);
            request.setAttribute(View.SECOND_NAME_PAGE, View.SECOND_NAME);
            request.setAttribute(View.LOGIN_PAGE, View.LOGIN);
            request.setAttribute(View.PASSWORD_PAGE, View.PASSWORD);
            request.setAttribute(View.PASSWORD_PAGE, View.PASSWORD);
            request.setAttribute(View.CREATE_PAGE, View.CREATE);
            return ViewURL.CREATE_SUB_JSP;
        }

    }
}
