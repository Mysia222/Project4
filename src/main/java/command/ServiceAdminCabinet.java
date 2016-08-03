package command;

import views.View;
import views.ViewURL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Славик on 03.08.2016.
 */
public class ServiceAdminCabinet implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(View.LOGOUT_BUTTON, View.LOGOUT);
        request.setAttribute(View.ADMIN_VIEW_SUBS_BUTTON, View.ADMIN_VIEW_SUBS);
        request.setAttribute(View.ADMIN_VIEW_SERVICES_BUTTON, View.ADMIN_VIEW_SERVICES);
        request.setAttribute(View.ADMIN_CREATE_SERVICE_BUTTON, View.ADMIN_CREATE_SERVICE);

        return ViewURL.ADMIN_HOME_JSP;
    }
}
