package command;

import views.View;
import views.ViewURL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Potaychuk Sviatoslav on 01.08.2016.
 */
public class UserLogout implements Command {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute(View.SUBSCRIBER_SESSION);
        request.setAttribute(View.PASSWORD_PAGE, View.PASSWORD);
        request.setAttribute(View.LOGIN_PAGE, View.LOGIN);
        request.setAttribute(View.CREATE_ACCOUNT_PAGE, View.CREATE_ACCOUNT);
        request.setAttribute(View.ENTER_PAGE, View.ENTER);
        return ViewURL.INDEX_JSP;
    }
}
