package command;

import ent.Service;
import services.ServService;
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
public class ServiceServicesList implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Service> list = ServService.getInstance().getServiceList();
        request.setAttribute(View.SERVICES_LIST_PAGE, list);
        request.setAttribute(View.NAME_PAGE, View.NAME);
        request.setAttribute(View.PRICE_PAGE, View.PRICE);
        request.setAttribute(View.CHANGE_PAGE, View.CHANGE);
        request.setAttribute(View.DELETE_PAGE, View.DELETE);
        request.setAttribute(View.SAVE_PAGE, View.SAVE);
        request.setAttribute(View.CREATE_NEW_FLAG, View.CREATE_NEW_FLAG_FALSE);
        request.setAttribute(View.CREATE_NEW_FLAG_BUTTON, false);


        request.setAttribute(View.CREATE_SERVICE_BUTTON, View.CREATE_SERVICE);
        return ViewURL.SERVICE_LIST_EDIT_JSP;
    }
}
