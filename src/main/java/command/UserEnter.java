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
 * Created by potaychuk on 03.08.2016.
 */
public class UserEnter implements Command {

    private SubService subService = SubService.getInstance();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute(View.BUNDLE)==null){
            request.getSession().setAttribute(View.BUNDLE,ResourceBundle.getBundle(View.BUNDLE_NAME , View.localeEN));
        }
        ResourceBundle bundle = (ResourceBundle)request.getSession().getAttribute(View.BUNDLE);
        try {
            //successful enter
            if (subService.exist(request.getParameter(View.LOGIN_PAGE), request.getParameter(View.PASSWORD_PAGE))) {
                Subscriber sub = subService.find(request.getParameter(View.LOGIN_PAGE));
                request.getSession().setAttribute(View.SUBSCRIBER_SESSION, sub);
                Command command = sub.isAdmin() ? CommandList.valueOf(View.ADMIN_CABINET).getCommand() :
                        CommandList.valueOf(View.USER_CABINET).getCommand();
                request.setAttribute("subServiceTT",subService);
                request.setAttribute("bundleSizeTT",bundle.getString(View.SUBS_COUNT));
                return command.execute(request, response);
            } else {
                //inform about wrong login
                if (subService.find(request.getParameter(View.LOGIN_PAGE)) != null) {
                    request.setAttribute(View.WRONG_LOGIN_PAGE, bundle.getString(View.WRONG_PASSWORD));
                    request.setAttribute(View.PREPARED_LOGIN, request.getParameter(View.LOGIN_PAGE));
                //inform about wrong password
                } else {
                    request.setAttribute(View.WRONG_LOGIN_PAGE, bundle.getString(View.WRONG_LOGIN));
                    request.setAttribute(View.PREPARED_LOGIN, "");
                }
                //draw index.jsp page
                request.setAttribute(View.PASSWORD_PAGE, bundle.getString(View.PASSWORD));
                request.setAttribute(View.LOGIN_PAGE, bundle.getString(View.LOGIN));
                request.setAttribute(View.CREATE_ACCOUNT_PAGE, bundle.getString(View.CREATE_ACCOUNT));
                request.setAttribute(View.ENTER_PAGE, bundle.getString(View.ENTER));
                return ViewURL.INDEX_JSP;
            }
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
}
