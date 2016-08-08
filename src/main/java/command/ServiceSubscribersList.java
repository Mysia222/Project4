package command;

import dao.DAOException;
import ent.Subscriber;
import services.ServService;
import services.SubService;
import views.View;
import views.ViewURL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Potaychuck Sviatoslav on 03.08.2016.
 */
public class ServiceSubscribersList implements Command {

    /**
     * Subscriber's service
     */
    private SubService subService = SubService.getInstance();

    /**
     * This method gets list of all subscribers and sets attributes to UserList.jsp
     * @param request is request which will be processing
     * @param response is response after processing
     * @return String url of UserList.jsp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResourceBundle bundle = (ResourceBundle)request.getSession().getAttribute(View.BUNDLE);
        try {
            List<Subscriber>   list = subService.getSubsList();
            request.setAttribute(View.USER_LIST_PAGE, list);
            request.setAttribute(View.FIRST_NAME_PAGE_H,  bundle.getString(View.FIRST_NAME));
            request.setAttribute(View.SECOND_NAME_PAGE_H,  bundle.getString(View.SECOND_NAME));
            request.setAttribute(View.LOGIN_PAGE_H,  bundle.getString(View.LOGIN));
            request.setAttribute(View.BALANCE_PAGE_H,  bundle.getString(View.BALANCE));
            request.setAttribute(View.CONTRACT_PAGE_H,  bundle.getString(View.CONTRACT));
            request.setAttribute(View.CURRENT_SERVICE_PAGE_H,  bundle.getString(View.CURRENT_SERVICE));
            request.setAttribute(View.BLOCK_SUBSCRIBER_BUTTON,  bundle.getString(View.BLOCK_SUBSCRIBER));
            request.setAttribute(View.UNLOCK_SUBSCRIBER_BUTTON,  bundle.getString(View.UNLOCK_SUBSCRIBER));
            request.setAttribute(View.ENABLE_ACTIONS_PAGE,  bundle.getString(View.ENABLE_ACTIONS));
            request.setAttribute(View.REFRESH_BUTTON,  bundle.getString(View.REFRESH));
            request.setAttribute(View.RETURN_CABINET_PAGE,  bundle.getString(View.RETURN_CABINET));
            return ViewURL.USER_LIST_JSP;
        } catch (DAOException e) {
            request.setAttribute(View.ERROR_CAUSE, bundle.getString(View.CANT_DO_REQUEST));
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
