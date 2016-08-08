package command;

import dao.DAOException;
import ent.Service;
import services.ServService;
import views.View;
import views.ViewURL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Created by Potaychuck Sviatoslav on 03.08.2016.
 */
public class ServiceSaveService implements Command {

    /**
     * Service's service
     */
    private ServService servService = ServService.getInstance();

    /**
     * This method checks for able of name. If name is able - method removes edit status and updates service,
     * else - inform about inability of name according session's Locale
     * @param request is request which will be processing
     * @param response is response after processing
     * @return result of ServiceServicesList command execution
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResourceBundle bundle = (ResourceBundle)request.getSession().getAttribute(View.BUNDLE);
        try {
            //is name in use?
            if (request.getParameter(View.NAME_PAGE).equals(View.JUST_CREATED_SERVICE_NAME) ||/*name is default and not edited, that's why can't be saved*/
                    servService.nameInUse(request.getParameter(View.NAME_PAGE))/*name used by another one service*/
                            && !request.getParameter(View.NAME_PAGE).equals(servService.getService(Integer.valueOf(request.getParameter(View.ID_PAGE))).getName())) {

                request.setAttribute(View.NAME_IN_USE_PAGE,
                        request.getParameter(View.NAME_PAGE).equals(View.JUST_CREATED_SERVICE_NAME) ?
                                bundle.getString(View.EDIT_SERVICE_NAME_FIRST) : bundle.getString(View.NAME_IN_USE));
                //name is free
            } else {
                Service service = servService.getService(Integer.valueOf(request.getParameter(View.ID_PAGE)));
                service.setName(request.getParameter(View.NAME_PAGE));
                service.setPrice(Double.valueOf(request.getParameter(View.PRICE_PAGE)));
                servService.unEditService(Integer.valueOf(request.getParameter(View.ID_PAGE)));
                servService.update(service);
            }
            Command command = CommandList.valueOf(View.SERVICES_LIST_EDIT).getCommand();
            return command.execute(request, response);
        }catch (DAOException e){
            request.setAttribute(View.ERROR_CAUSE, bundle.getString(View.CANT_DO_REQUEST));
            return ViewURL.ERROR_PAGE;
        }
    }

    //getters & setters
    public ServService getServService() {
        return servService;
    }

    public void setServService(ServService servService) {
        this.servService = servService;
    }
}
