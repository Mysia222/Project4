package command;

import dao.DAOException;
import ent.Subscriber;
import org.apache.log4j.Logger;
import services.SubService;
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
public class ServiceBlockSubscriber implements Command {

    /**
     * Logger
     */
    private static Logger log =  Logger.getLogger(ServiceBlockSubscriber.class);

    /**
     * Subscriber's service
     */
    private SubService subService = SubService.getInstance();

    /**
     * This method block subscriber
     * @param request is request which will be processing
     * @param response is response after processing
     * @return result of ServiceSubscribersList command execution
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.trace(View.COMMAND_EXECUTE + this.getClass().getName());
        ResourceBundle bundle = (ResourceBundle) request.getSession().getAttribute(View.BUNDLE);
        try {
            subService.blockId(Integer.valueOf(request.getParameter(View.ID_PAGE)));
        }catch (DAOException e){
            request.setAttribute(View.ERROR_CAUSE, bundle.getString(View.CANT_DO_REQUEST));
            return ViewURL.ERROR_PAGE;
        }
        Command command = CommandList.valueOf(View.SERVICE_SUBSCRIBERS).getCommand();
        return command.execute(request,response);
    }

    public SubService getSubService() {
        return subService;
    }

    public void setSubService(SubService subService) {
        this.subService = subService;
    }
}
