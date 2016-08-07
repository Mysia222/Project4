package command;

import dao.DAOException;
import ent.Subscriber;
import services.SubService;
import views.View;
import views.ViewURL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Created by Славик on 03.08.2016.
 */
public class ServiceUnlockSubscriber implements Command {

    private SubService subService = SubService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResourceBundle bundle = (ResourceBundle)request.getSession().getAttribute(View.BUNDLE);
        try {
            Subscriber sub = subService.find(Integer.parseInt(request.getParameter(View.ID_PAGE)));
            sub.setBlocked(true);
            subService.unlockId(sub.getContract());
            Command command = CommandList.valueOf(View.SERVICE_SUBSCRIBERS).getCommand();
            return command.execute(request, response);
        }catch (DAOException e){
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
}
