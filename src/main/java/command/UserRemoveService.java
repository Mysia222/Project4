package command;

import dao.DAOException;
import ent.Service;
import ent.Subscriber;
import services.ServService;
import services.SubService;
import views.View;
import views.ViewURL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Created by potaychuk on 03.08.2016.
 */
public class UserRemoveService implements Command {

    /**
     * Subscriber's service
     */
    private SubService subService = SubService.getInstance();

    /**
     * Service's service
     */
    private ServService servService = ServService.getInstance();

    /**
     * This method removes service from subscriber's service list and updates this sub
     * @param request is request which will be processing
     * @param response is response after processing
     * @return result of UserCabinet command execution
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResourceBundle bundle = (ResourceBundle)request.getSession().getAttribute(View.BUNDLE);
        int id = Integer.parseInt(request.getParameter(View.ID_PAGE));
        try {
            Service service = servService.getService(id);
            Subscriber sub = (Subscriber) request.getSession().getAttribute(View.SUBSCRIBER_SESSION);
            sub.getCurrentService().remove(service);
            subService.setService(sub);
            request.getSession().setAttribute(View.SUBSCRIBER_SESSION,sub);
        } catch (DAOException e) {
            request.setAttribute(View.ERROR_CAUSE, bundle.getString(View.CANT_DO_REQUEST));
            return ViewURL.ERROR_PAGE;
        }
        Command command = CommandList.valueOf(View.USER_CABINET).getCommand();
        return command.execute(request, response);
    }

    //getters & setters
    public SubService getSubService() {
        return subService;
    }

    public void setSubService(SubService subService) {
        this.subService = subService;
    }

    public ServService getServService() {
        return servService;
    }

    public void setServService(ServService servService) {
        this.servService = servService;
    }
}
