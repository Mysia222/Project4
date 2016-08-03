package command;

import ent.Service;
import views.View;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Славик on 03.08.2016.
 */
public class ServiceSaveService implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Service service = servService.getService(Integer.valueOf(request.getParameter(View.ID_PAGE)));
        if (servService.nameInUse(request.getParameter(View.NAME_PAGE))&& !request.getParameter(View.NAME_PAGE).equals(service.getName())){
            request.setAttribute(View.NAME_IN_USE_PAGE, View.NAME_IN_USE);
        }else {
            service.setName(request.getParameter(View.NAME_PAGE));
            service.setPrice(Double.valueOf(request.getParameter(View.PRICE_PAGE)));
            servService.unEditService(Integer.valueOf(request.getParameter(View.ID_PAGE)));
            servService.update(service);
        }
        Command command = CommandList.valueOf(View.SERVICES_LIST_EDIT).getCommand();
        return command.execute(request,response);
    }
}
