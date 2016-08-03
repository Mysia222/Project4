package command;

import ent.Service;
import ent.Subscriber;
import views.View;

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
        Service service= servService.getService(id);
        sub.getCurrentService().add(service);
        sub.setBalance(sub.getBalance()-service.getPrice());
        subService.setBalance(sub);
        subService.setService(sub);
        request.getSession().setAttribute(View.SUBSCRIBER_SESSION,sub);
        Command command = CommandList.valueOf(View.USER_CABINET).getCommand();
        return command.execute(request, response);
    }
}
