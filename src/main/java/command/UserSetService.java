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
public class UserSetService implements Command {

    /**
     * Subscriber's service
     */
    private SubService subService = SubService.getInstance();

    /**
     * Service's service
     */
    private ServService servService = ServService.getInstance();

    /**
     * This method sets chosen service to subscriber's service list in case:
     * 1) subscriber isn't blocked
     * 2) service isn't in edit
     * than updates user's balance and services
     * @param request is request which will be processing
     * @param response is response after processing
     * @return result of UserCabinet command execution
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResourceBundle bundle = (ResourceBundle)request.getSession().getAttribute(View.BUNDLE);
        Subscriber sub = (Subscriber) request.getSession().getAttribute(View.SUBSCRIBER_SESSION);
        int id = Integer.parseInt(request.getParameter(View.ID_PAGE));
        try {
            //is account blocked?  --> disable access to DirectService.jsp
            if(subService.find(sub.getInfo().getLogin()).isBlocked()){
                Command command = CommandList.valueOf(View.USER_CABINET).getCommand();
                return command.execute(request, response);
            }
            Service service = servService.getService(id);
            //Is service in edit --> hold on DirectService.jsp
            if(service.isEdit()){
                request.setAttribute(View.USER_SERVICE_IN_USE_PAGE, bundle.getString(View.USER_SERVICE_IN_USE));
                Command command = CommandList.valueOf(View.USER_DIRECT_SERVICE).getCommand();
                return command.execute(request, response);
            }
            //All is ok! Set, update
            sub.getCurrentService().add(service);
            sub.setBalance(sub.getBalance() - service.getPrice());
            subService.setBalance(sub);
            subService.setService(sub);
        }catch (DAOException e){
            request.setAttribute(View.ERROR_CAUSE, bundle.getString(View.CANT_DO_REQUEST));
            return ViewURL.ERROR_PAGE;
        }

        request.getSession().setAttribute(View.SUBSCRIBER_SESSION,sub);
        Command command = CommandList.valueOf(View.USER_CABINET).getCommand();
        return command.execute(request, response);
    }

    //setters & getters
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
