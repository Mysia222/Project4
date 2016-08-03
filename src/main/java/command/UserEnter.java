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
public class UserEnter implements Command {



    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //successful enter
        if (subService.existByLogPas(request.getParameter(View.LOGIN_PAGE), request.getParameter(View.PASSWORD_PAGE))) {
            Subscriber sub = subService.subByLog(request.getParameter(View.LOGIN_PAGE));
            request.getSession().setAttribute(View.SUBSCRIBER_SESSION,sub);
            Command command= sub.isAdmin() ? CommandList.valueOf(View.ADMIN_CABINET).getCommand():
                                                CommandList.valueOf(View.USER_CABINET).getCommand();
            return command.execute(request, response);
        }else {
            //inform about wrong login
            if (subService.subByLog(request.getParameter(View.LOGIN_PAGE))!=null){
                request.setAttribute(View.WRONG_LOGIN_PAGE,View.WRONG_PASSWORD);
                request.setAttribute(View.PREPARED_LOGIN,request.getParameter(View.LOGIN_PAGE));
             //inform about wrong password
            }else {
                request.setAttribute(View.WRONG_LOGIN_PAGE,View.WRONG_LOGIN);
                request.setAttribute(View.PREPARED_LOGIN,"");
            }
            //draw page
            request.setAttribute(View.PASSWORD_PAGE, View.PASSWORD);
            request.setAttribute(View.LOGIN_PAGE, View.LOGIN);
            request.setAttribute(View.CREATE_ACCOUNT_PAGE, View.CREATE_ACCOUNT);
            request.setAttribute(View.ENTER_PAGE, View.ENTER);
            return ViewURL.INDEX_JSP;
        }



    }
}
