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
import java.util.*;

/**
 * Created by potaychuk on 03.08.2016.
 */
public class UserDirectService implements Command {
    private SubService subService = SubService.getInstance();
    private ServService servService = ServService.getInstance();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResourceBundle bundle = (ResourceBundle)request.getSession().getAttribute(View.BUNDLE);
        Subscriber sub = (Subscriber)request.getSession().getAttribute(View.SUBSCRIBER_SESSION);
        try {
            if(subService.find(sub.getInfo().getLogin()).isBlocked()){
                Command command = CommandList.valueOf(View.USER_CABINET).getCommand();
                return command.execute(request, response);
            }
            List<Service>    services1 = servService.getServiceList();
            List<Service>    services = new ArrayList<>();
            for (Service s: services1){
                if (!s.isEdit()){
                    services.add(s);
                }
            }
            request.setAttribute("services",services);
            request.setAttribute(View.REMOVE_USER_SERVICE_PAGE, bundle.getString(View.REMOVE_USER_SERVICE));
            request.setAttribute(View.SET_USER_SERVICE_PAGE, bundle.getString(View.SET_USER_SERVICE));
            request.setAttribute(View.PRICE_PAGE, bundle.getString(View.PRICE));
            request.setAttribute(View.NAME_PAGE, bundle.getString(View.NAME));
            request.setAttribute(View.ENABLE_ACTIONS_PAGE, bundle.getString(View.ENABLE_ACTIONS));
            request.setAttribute(View.RETURN_CABINET_PAGE, bundle.getString(View.RETURN_CABINET));
            Set idSet = new HashSet();
            for(Service s: sub.getCurrentService()){
                    idSet.add(s.getId());
            }
            request.setAttribute("idSet",idSet);

            return ViewURL.SERVICE_LIST_JSP;

        } catch (DAOException e) {
            request.setAttribute(View.ERROR_CAUSE, bundle.getString(View.CANT_DO_REQUEST));
            return ViewURL.ERROR_PAGE;
        }
    }

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
