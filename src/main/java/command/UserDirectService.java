package command;

import dao.DAOException;
import ent.Service;
import ent.Subscriber;
import views.View;
import views.ViewURL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by potaychuk on 03.08.2016.
 */
public class UserDirectService implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType ("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Subscriber sub = (Subscriber)request.getSession().getAttribute(View.SUBSCRIBER_SESSION);
        try {
            if(subService.subByLog(sub.getInfo().getLogin()).isBlocked()){
                Command command = CommandList.valueOf(View.USER_CABINET).getCommand();
                return command.execute(request, response);
            }
            List<Service>    services = servService.getServiceList();
            request.setAttribute("services",services);
            request.setAttribute(View.REMOVE_USER_SERVICE_PAGE, View.REMOVE_USER_SERVICE);
            request.setAttribute(View.SET_USER_SERVICE_PAGE, View.SET_USER_SERVICE);
            request.setAttribute(View.PRICE_PAGE, View.PRICE);
            request.setAttribute(View.NAME_PAGE, View.NAME);
            request.setAttribute(View.ENABLE_ACTIONS_PAGE, View.ENABLE_ACTIONS);
            request.setAttribute(View.RETURN_CABINET_PAGE, View.RETURN_CABINET);
            Set idSet = new HashSet();
            for(Service s: sub.getCurrentService()){
                idSet.add(s.getId());
            }
            request.setAttribute("idSet",idSet);
            return ViewURL.SERVICE_LIST_JSP;

        } catch (DAOException e) {
            request.setAttribute(View.ERROR_CAUSE, View.CANT_DO_REQUEST);
            return ViewURL.ERROR_PAGE;
        }
    }
}
