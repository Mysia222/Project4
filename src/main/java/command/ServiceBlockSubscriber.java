package command;

import dao.DAOException;
import ent.Subscriber;
import views.View;
import views.ViewURL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Славик on 03.08.2016.
 */
public class ServiceBlockSubscriber implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Subscriber sub = subService.subByContract(Integer.parseInt(request.getParameter(View.ID_PAGE)));
            sub.setBlocked(true);
            subService.blockId(sub.getContract());
        }catch (DAOException e){
            request.setAttribute(View.ERROR_CAUSE, View.CANT_DO_REQUEST);
            return ViewURL.ERROR_PAGE;
        }
        Command command = CommandList.valueOf(View.SERVICE_SUBSCRIBERS).getCommand();
        return command.execute(request,response);
    }
}
