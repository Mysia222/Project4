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

/**
 * Created by potaychuk on 03.08.2016.
 */
public class UserSetService implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Subscriber sub = (Subscriber) request.getSession().getAttribute(View.SUBSCRIBER_SESSION);
        int id = Integer.parseInt(request.getParameter(View.ID_PAGE));
        try {
            //is account blocked?  --> disable access to DirectService.jsp
            if(subService.subByLog(sub.getInfo().getLogin()).isBlocked()){
                Command command = CommandList.valueOf(View.USER_CABINET).getCommand();
                return command.execute(request, response);
            }
            Service service = servService.getService(id);
            //Is service in edit --> hold on DirectService.jsp
            if(service.isEdit()){
                request.setAttribute(View.USER_SERVICE_IN_USE_PAGE, View.USER_SERVICE_IN_USE);
                Command command = CommandList.valueOf(View.USER_DIRECT_SERVICE).getCommand();
                return command.execute(request, response);
            }
            //All is ok! Set, update
            sub.getCurrentService().add(service);
            sub.setBalance(sub.getBalance() - service.getPrice());
            subService.setBalance(sub);
            subService.setService(sub);
        }catch (DAOException e){
            request.setAttribute(View.ERROR_CAUSE, View.CANT_DO_REQUEST);
            return ViewURL.ERROR_PAGE;
        }

        request.getSession().setAttribute(View.SUBSCRIBER_SESSION,sub);
        Command command = CommandList.valueOf(View.USER_CABINET).getCommand();
        return command.execute(request, response);
    }
}
