package command;

import dao.DAOException;
import views.View;
import views.ViewURL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Created by Славик on 03.08.2016.
 */
public class ServiceDeleteService implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResourceBundle bundle = (ResourceBundle)request.getSession().getAttribute(View.BUNDLE);
        try {
            servService.delete(Integer.valueOf(request.getParameter(View.ID_PAGE)));
        } catch (DAOException e) {
            request.setAttribute(View.ERROR_CAUSE, bundle.getString(View.CANT_DO_REQUEST));
            return ViewURL.ERROR_PAGE;
        }
        Command command = CommandList.valueOf(View.SERVICES_LIST_EDIT).getCommand();
        return command.execute(request,response);
    }
}
