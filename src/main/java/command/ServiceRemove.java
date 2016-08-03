package command;

import ent.Service;
import ent.Subscriber;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Славик on 01.08.2016.
 */
public class ServiceRemove implements Command {

    /**
     * This method removes service from user's service list
     * @param request is request which will be processing
     * @param response is response after processing
     * @return string url
     * @throws ServletException
     * @throws IOException
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Service service= servService.getService(id);
        Subscriber sub = (Subscriber) request.getSession().getAttribute("sub");
        sub.getCurrentService().remove(service);
        subService.setService(sub);
        request.getSession().setAttribute("sub",sub);
        return "/cabinet";
    }

}
