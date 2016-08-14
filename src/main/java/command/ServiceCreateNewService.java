package command;

import dao.DAOException;
import ent.Service;
import org.apache.log4j.Logger;
import services.ServService;
import views.View;
import views.ViewURL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Created by Potaychuck Sviatoslav on 03.08.2016.
 */
public class ServiceCreateNewService implements Command {

    /**
     * Logger
     */
    private static Logger log =  Logger.getLogger(ServiceCreateNewService.class);

    /**
     * Service's service
     */
    private ServService servService = ServService.getInstance();

    /**
     * This method crates new service with edit status and default name and price
     * @param request is request which will be processing
     * @param response is response after processing
     * @return result of execution of ServiceServicesList command
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.trace(View.COMMAND_EXECUTE + this.getClass().getName());
        ResourceBundle bundle = (ResourceBundle)request.getSession().getAttribute(View.BUNDLE);
        Service service = new Service();
        service.setEdit(true);
        service.setName(View.JUST_CREATED_SERVICE_NAME);
        try {
            servService.create(service);
        } catch (DAOException e) {
            request.setAttribute(View.ERROR_CAUSE, bundle.getString(View.CANT_DO_REQUEST));
            return ViewURL.ERROR_PAGE;
        }
        Command command = CommandList.valueOf(View.SERVICES_LIST_EDIT).getCommand();
        return command.execute(request,response);
    }

    //getters & setters
    public ServService getServService() {
        return servService;
    }

    public void setServService(ServService servService) {
        this.servService = servService;
    }
}
