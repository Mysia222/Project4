package main.command;

import main.ent.Service;
import main.ent.Subscriber;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Славик on 27.07.2016.
 */
public class SetService implements Command {

    /**
     * This method sets service is user service list
     * if service is already in use? user will be informed about it
     * @param request is request which will be processing
     * @param response is response after processing
     * @return String url
     * @throws ServletException
     * @throws IOException
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Subscriber sub = (Subscriber) request.getSession().getAttribute("sub");
        int id = Integer.parseInt(request.getParameter("id"));
        Service service= servService.getService(id);
        if (sub.getCurrentService().contains(service)){
            request.getRequestDispatcher("/view/ServiceAlreadyInUse.jsp");
            return "/T1Servlet?command=ORDER_SERVICE";
        }
        sub.getCurrentService().add(service);
        sub.setBalance(sub.getBalance()-service.getPrice());
        subService.setBalance(sub);
        subService.setService(sub);
       request.getSession().setAttribute("sub",sub);
        return "/cabinet";
    }
}
