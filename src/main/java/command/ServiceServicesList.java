package command;

import dao.DAOException;
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
        List<Service> list = null;
        try {
            list = ServService.getInstance().getServiceList();
        } catch (DAOException e) {
            request.setAttribute(View.ERROR_CAUSE, View.CANT_DO_REQUEST);
            return ViewURL.ERROR_PAGE;
        }
        request.setAttribute(View.SERVICES_LIST_PAGE, list);
        request.setAttribute(View.NAME_PAGE, View.NAME);
        request.setAttribute(View.PRICE_PAGE, View.PRICE);
        request.setAttribute(View.CHANGE_PAGE, View.CHANGE);
        request.setAttribute(View.DELETE_PAGE, View.DELETE);
        request.setAttribute(View.RETURN_CABINET_PAGE, View.RETURN_CABINET);
        request.setAttribute(View.SAVE_PAGE, View.SAVE);
        request.setAttribute(View.CREATE_SERVICE_BUTTON, View.CREATE_SERVICE);
        return ViewURL.SERVICE_LIST_EDIT_JSP;
    }
}
