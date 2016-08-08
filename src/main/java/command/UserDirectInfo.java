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
public class UserDirectInfo implements Command {


    private SubService subService = SubService.getInstance();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         ResourceBundle bundle = (ResourceBundle)request.getSession().getAttribute(View.BUNDLE);
        Subscriber sub = (Subscriber)request.getSession().getAttribute(View.SUBSCRIBER_SESSION);
        //request from Home.jsp
        if(request.getParameter(View.DIRECT_INFO_BUTTON).equals(bundle.getString(View.DIRECT_INFO))){
            request.setAttribute(View.SAVED_FIRST_NAME, sub.getInfo().getFirstName());
            request.setAttribute(View.SAVED_SECOND_NAME, sub.getInfo().getSecondName());
            request.setAttribute(View.SAVED_PASSWORD, sub.getInfo().getPassword());
            request.setAttribute(View.SAVED_LOGIN, sub.getInfo().getLogin());
            request.setAttribute(View.USER_INFO_PAGE, bundle.getString(View.USER_INFO));
            request.setAttribute(View.FIRST_NAME_PAGE_H, bundle.getString(View.FIRST_NAME));
            request.setAttribute(View.SECOND_NAME_PAGE_H, bundle.getString(View.SECOND_NAME));
            request.setAttribute(View.LOGIN_PAGE_H, bundle.getString(View.LOGIN));
            request.setAttribute(View.PASSWORD_PAGE_H, bundle.getString(View.PASSWORD));
            request.setAttribute(View.PASSWORD_PAGE_H, bundle.getString(View.PASSWORD));
            request.setAttribute(View.CREATE_PAGE, bundle.getString(View.CREATE));
            request.setAttribute(View.SAVE_PAGE, bundle.getString(View.SAVE));
            request.setAttribute(View.RETURN_CABINET_PAGE, bundle.getString(View.RETURN_CABINET));
            return ViewURL.DIRECT_INFO_JSP;
         //request from DirectInfo.jsp
        }else {
            try {
                Subscriber temp = subService.find(request.getParameter(View.LOGIN_PAGE));
                //login in use?
                if (temp!=null && temp.getContract()!=sub.getContract()){
                    request.setAttribute(View.SAVED_FIRST_NAME, sub.getInfo().getFirstName());
                    request.setAttribute(View.SAVED_SECOND_NAME, sub.getInfo().getSecondName());
                    request.setAttribute(View.SAVED_PASSWORD, sub.getInfo().getPassword());
                    request.setAttribute(View.LOGIN_IN_USE_PAGE, bundle.getString(View.LOGIN_IN_USE));
                    request.setAttribute(View.USER_INFO_PAGE, bundle.getString(View.USER_INFO));
                    request.setAttribute(View.FIRST_NAME_PAGE_H, bundle.getString(View.FIRST_NAME));
                    request.setAttribute(View.SECOND_NAME_PAGE_H, bundle.getString(View.SECOND_NAME));
                    request.setAttribute(View.LOGIN_PAGE_H, bundle.getString(View.LOGIN));
                    request.setAttribute(View.PASSWORD_PAGE_H, bundle.getString(View.PASSWORD));
                    request.setAttribute(View.PASSWORD_PAGE_H, bundle.getString(View.PASSWORD));
                    request.setAttribute(View.CREATE_PAGE, bundle.getString(View.CREATE));
                    request.setAttribute(View.SAVE_PAGE, bundle.getString(View.SAVE));
                    request.setAttribute(View.RETURN_CABINET_PAGE, bundle.getString(View.RETURN_CABINET));
                    return ViewURL.DIRECT_INFO_JSP;
                }else {
                    sub.getInfo().setLogin(request.getParameter(View.LOGIN_PAGE));
                    sub.getInfo().setPassword(request.getParameter(View.PASSWORD_PAGE));
                    sub.getInfo().setFirstName(request.getParameter(View.FIRST_NAME_PAGE));
                    sub.getInfo().setSecondName(request.getParameter(View.SECOND_NAME_PAGE));
                    request.getSession().setAttribute(View.SUBSCRIBER_SESSION, sub);
                    subService.setSub(sub);
                    Command command = CommandList.valueOf(View.USER_CABINET).getCommand();
                    return command.execute(request, response);
                }
            }catch (DAOException e){
                request.setAttribute(View.ERROR_CAUSE, bundle.getString(View.CANT_DO_REQUEST));
                return ViewURL.ERROR_PAGE;
            }
        }
    }

    public SubService getSubService() {
        return subService;
    }

    public void setSubService(SubService subService) {
        this.subService = subService;
    }
}
