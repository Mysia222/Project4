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
 * Created by potaychuk on 03.08.2016.
 */
public class UserCabinet implements Command {

    /**
     * Logger
     */
    private static Logger log =  Logger.getLogger(UserCabinet.class);

    /**
     * Subscriber's service
     */
    private SubService subService = SubService.getInstance();

    /**
     * This method finds updates session's subscriber from DB and sets attributes for Home.jsp
     * @param request is request which will be processing
     * @param response is response after processing
     * @return String url of Home.jsp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.trace(View.COMMAND_EXECUTE + this.getClass().getName());
        ResourceBundle bundle = (ResourceBundle) request.getSession().getAttribute(View.BUNDLE);
        Subscriber sub = (Subscriber) request.getSession().getAttribute(View.SUBSCRIBER_SESSION);
        try {
            sub=subService.find(sub.getContract());
            request.setAttribute(View.BLOCKED_PAGE, sub.isBlocked()?bundle.getString(View.BLOCKED_TRUE):bundle.getString(View.BLOCKED_FALSE));
            request.setAttribute(View.FIRST_NAME_PAGE, sub.getInfo().getFirstName());
            request.setAttribute(View.SECOND_NAME_PAGE, sub.getInfo().getSecondName());
            request.setAttribute(View.LOGIN_PAGE, sub.getInfo().getLogin());
            request.setAttribute(View.BALANCE_PAGE, sub.getBalance());
            request.setAttribute(View.CONTRACT_PAGE, sub.getContract());
            request.setAttribute(View.CURRENT_SERVICE_PAGE, sub.getCurrentService());
            request.setAttribute(View.FIRST_NAME_PAGE_H, bundle.getString(View.FIRST_NAME));
            request.setAttribute(View.SECOND_NAME_PAGE_H, bundle.getString(View.SECOND_NAME));
            request.setAttribute(View.LOGIN_PAGE_H, bundle.getString(View.LOGIN));
            request.setAttribute(View.BALANCE_PAGE_H, bundle.getString(View.BALANCE));
            request.setAttribute(View.CONTRACT_PAGE_H, bundle.getString(View.CONTRACT));
            request.setAttribute(View.CURRENT_SERVICE_PAGE_H, bundle.getString(View.CURRENT_SERVICE));
            request.setAttribute(View.DIRECT_SERVICE_BUTTON, bundle.getString(View.DIRECT_SERVICE));
            request.setAttribute(View.FOOT_THE_BILL_PAGE, bundle.getString(View.FOOT_THE_BILL));
            request.setAttribute(View.LOGOUT_PAGE, bundle.getString(View.LOGOUT));
            request.setAttribute(View.DIRECT_INFO_BUTTON, bundle.getString(View.DIRECT_INFO));
            request.getSession().setAttribute(View.SUBSCRIBER_SESSION,sub);
            return ViewURL.HOME_JSP;
        } catch (DAOException e) {
            log.error(View.LOG_BY_USER + request.getSession().getAttribute(View.SUBSCRIBER_SESSION));
            request.setAttribute(View.ERROR_CAUSE, View.CANT_DO_REQUEST);
            return ViewURL.ERROR_PAGE;
        }
    }

    //getters & setters
    public SubService getSubService() {
        return subService;
    }

    public void setSubService(SubService subService) {
        this.subService = subService;
    }
}
