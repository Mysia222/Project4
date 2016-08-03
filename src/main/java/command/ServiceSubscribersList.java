package command;

import ent.Subscriber;
import services.SubService;
import views.View;
import views.ViewURL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Славик on 03.08.2016.
 */
public class ServiceSubscribersList implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Subscriber> list = SubService.getInstance().getSubsList();
        request.setAttribute(View.USER_LIST_PAGE, list);
        request.setAttribute(View.FIRST_NAME_PAGE_H, View.FIRST_NAME);
        request.setAttribute(View.SECOND_NAME_PAGE_H, View.SECOND_NAME);
        request.setAttribute(View.LOGIN_PAGE_H, View.LOGIN);
        request.setAttribute(View.BALANCE_PAGE_H, View.BALANCE);
        request.setAttribute(View.CONTRACT_PAGE_H, View.CONTRACT);
        request.setAttribute(View.CURRENT_SERVICE_PAGE_H, View.CURRENT_SERVICE);
        request.setAttribute(View.BLOCK_SUBSCRIBER_BUTTON, View.BLOCK_SUBSCRIBER);
        request.setAttribute(View.UNLOCK_SUBSCRIBER_BUTTON, View.UNLOCK_SUBSCRIBER);
        request.setAttribute(View.ENABLE_ACTIONS_PAGE, View.ENABLE_ACTIONS);
        request.setAttribute(View.REFRESH_BUTTON, View.REFRESH);
        request.setAttribute(View.RETURN_CABINET_PAGE, View.RETURN_CABINET);
        return ViewURL.USER_LIST_JSP;
    }
}
