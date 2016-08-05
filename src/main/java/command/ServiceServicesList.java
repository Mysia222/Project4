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
import java.util.ResourceBundle;

/**
 * Created by Славик on 03.08.2016.
 */
public class ServiceServicesList implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResourceBundle bundle = (ResourceBundle)request.getSession().getAttribute(View.BUNDLE);
        List<Service> list = null;
        try {
            list = ServService.getInstance().getServiceList();
        } catch (DAOException e) {
            request.setAttribute(View.ERROR_CAUSE, bundle.getString(View.CANT_DO_REQUEST));
            return ViewURL.ERROR_PAGE;
        }
        request.setAttribute(View.SERVICES_LIST_PAGE, list);
        request.setAttribute(View.NAME_PAGE, bundle.getString(View.NAME));
        request.setAttribute(View.PRICE_PAGE, bundle.getString(View.PRICE));
        request.setAttribute(View.CHANGE_PAGE, bundle.getString(View.CHANGE));
        request.setAttribute(View.DELETE_PAGE, bundle.getString(View.DELETE));
        request.setAttribute(View.RETURN_CABINET_PAGE, bundle.getString(View.RETURN_CABINET));
        request.setAttribute(View.SAVE_PAGE, bundle.getString(View.SAVE));
        request.setAttribute(View.CREATE_SERVICE_BUTTON, bundle.getString(View.CREATE_SERVICE));
        return ViewURL.SERVICE_LIST_EDIT_JSP;
    }
}
