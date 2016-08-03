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
public class UserCabinet implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Subscriber sub = (Subscriber) request.getSession().getAttribute(View.SUBSCRIBER_SESSION);
        request.setAttribute(View.BLOCKED_PAGE, sub.isBlocked()?View.BLOCKED_TRUE:View.BLOCKED_FALSE);
        request.setAttribute(View.FIRST_NAME_PAGE, sub.getInfo().getFirstName());
        request.setAttribute(View.SECOND_NAME_PAGE, sub.getInfo().getSecondName());
        request.setAttribute(View.LOGIN_PAGE, sub.getInfo().getLogin());
        request.setAttribute(View.PASSWORD_PAGE, sub.getInfo().getPassword());
        request.setAttribute(View.BALANCE_PAGE, sub.getBalance());
        request.setAttribute(View.CONTRACT_PAGE, sub.getContract());
        request.setAttribute(View.CURRENT_SERVICE_PAGE, sub.getCurrentService());
        request.setAttribute(View.FIRST_NAME_PAGE_H, View.FIRST_NAME);
        request.setAttribute(View.SECOND_NAME_PAGE_H, View.SECOND_NAME);
        request.setAttribute(View.LOGIN_PAGE_H, View.LOGIN);
        request.setAttribute(View.PASSWORD_PAGE_H, View.PASSWORD);
        request.setAttribute(View.BALANCE_PAGE_H, View.BALANCE);
        request.setAttribute(View.CONTRACT_PAGE_H, View.CONTRACT);
        request.setAttribute(View.CURRENT_SERVICE_PAGE_H, View.CURRENT_SERVICE);
        request.setAttribute(View.DIRECT_SERVICE_BUTTON, View.DIRECT_SERVICE);
        request.setAttribute(View.FOOT_THE_BILL_PAGE, View.FOOT_THE_BILL);
        request.setAttribute(View.LOGOUT_PAGE, View.LOGOUT);
        request.setAttribute(View.DIRECT_INFO_BUTTON, View.DIRECT_INFO);
        return ViewURL.HOME_JSP;
    }
}
