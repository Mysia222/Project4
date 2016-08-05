package command;

import dao.DAOException;
import ent.Subscriber;
import views.View;
import views.ViewURL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by potaychuk on 03.08.2016.
 */
public class UserCabinet implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResourceBundle bundle = (ResourceBundle) request.getSession().getAttribute(View.BUNDLE);
        Subscriber sub = (Subscriber) request.getSession().getAttribute(View.SUBSCRIBER_SESSION);
        try {
            sub=subService.subByContract(sub.getContract());
        } catch (DAOException e) {
            request.setAttribute(View.ERROR_CAUSE, View.CANT_DO_REQUEST);
            return ViewURL.ERROR_PAGE;
        }

        request.setAttribute(View.BLOCKED_PAGE, sub.isBlocked()?bundle.getString(View.BLOCKED_TRUE):bundle.getString(View.BLOCKED_FALSE));
        request.setAttribute(View.FIRST_NAME_PAGE, sub.getInfo().getFirstName());
        request.setAttribute(View.SECOND_NAME_PAGE, sub.getInfo().getSecondName());
        request.setAttribute(View.LOGIN_PAGE, sub.getInfo().getLogin());
        request.setAttribute(View.PASSWORD_PAGE, sub.getInfo().getPassword());
        request.setAttribute(View.BALANCE_PAGE, sub.getBalance());
        request.setAttribute(View.CONTRACT_PAGE, sub.getContract());
        request.setAttribute(View.CURRENT_SERVICE_PAGE, sub.getCurrentService());
        request.setAttribute(View.FIRST_NAME_PAGE_H, bundle.getString(View.FIRST_NAME));
        request.setAttribute(View.SECOND_NAME_PAGE_H, bundle.getString(View.SECOND_NAME));
        request.setAttribute(View.LOGIN_PAGE_H, bundle.getString(View.LOGIN));
        request.setAttribute(View.PASSWORD_PAGE_H, bundle.getString(View.PASSWORD));
        request.setAttribute(View.BALANCE_PAGE_H, bundle.getString(View.BALANCE));
        request.setAttribute(View.CONTRACT_PAGE_H, bundle.getString(View.CONTRACT));
        request.setAttribute(View.CURRENT_SERVICE_PAGE_H, bundle.getString(View.CURRENT_SERVICE));
        request.setAttribute(View.DIRECT_SERVICE_BUTTON, bundle.getString(View.DIRECT_SERVICE));
        request.setAttribute(View.FOOT_THE_BILL_PAGE, bundle.getString(View.FOOT_THE_BILL));
        request.setAttribute(View.LOGOUT_PAGE, bundle.getString(View.LOGOUT));
        request.setAttribute(View.DIRECT_INFO_BUTTON, bundle.getString(View.DIRECT_INFO));
        return ViewURL.HOME_JSP;
    }
}
