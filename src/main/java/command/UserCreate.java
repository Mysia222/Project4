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
 * Created by potaychuk on 02.08.2016.
 */
public class UserCreate implements Command {

    private SubService subService = SubService.getInstance();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute(View.BUNDLE)==null){
            request.getSession().setAttribute(View.BUNDLE,ResourceBundle.getBundle(View.BUNDLE_NAME , View.localeEN));
        }
        ResourceBundle bundle = (ResourceBundle)request.getSession().getAttribute(View.BUNDLE);
        response.setContentType ("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");


        //request from index.jsp
        if(request.getParameter(View.CREATE_ACCOUNT_PAGE).equals(bundle.getString(View.CREATE_ACCOUNT)) ||
                request.getParameter(View.CREATE_ACCOUNT_PAGE).equals(View.CREATE_ACCOUNT_PAGE_DEFAULT)){
            request.setAttribute(View.SAVED_FIRST_NAME, "");
            request.setAttribute(View.SAVED_SECOND_NAME, "");
            request.setAttribute(View.SAVED_PASSWORD,"");
            request.setAttribute(View.USER_INFO_PAGE, bundle.getString(View.USER_INFO));
            request.setAttribute(View.FIRST_NAME_PAGE,  bundle.getString(View.FIRST_NAME));
            request.setAttribute(View.SECOND_NAME_PAGE,  bundle.getString(View.SECOND_NAME));
            request.setAttribute(View.LOGIN_PAGE,  bundle.getString(View.LOGIN));
            request.setAttribute(View.PASSWORD_PAGE,  bundle.getString(View.PASSWORD));
            request.setAttribute(View.PASSWORD_PAGE,  bundle.getString(View.PASSWORD));
            request.setAttribute(View.CREATE_PAGE,  bundle.getString(View.CREATE));
            return ViewURL.CREATE_SUB_JSP;
        }
        //request from CreateSub.jsp
        try{

            Subscriber sub = subService.find(request.getParameter(View.QUERY_LOGIN));
            //Is login in use?
            if(sub==null) {
                sub=new Subscriber();
                sub.setInfo(sub.new SubInfo());
                sub.getInfo().setFirstName(request.getParameter(View.FIRST_NAME_PAGE));
                sub.getInfo().setSecondName(request.getParameter(View.SECOND_NAME_PAGE));
                sub.getInfo().setLogin(request.getParameter(View.LOGIN_PAGE));
                sub.getInfo().setPassword(request.getParameter(View.PASSWORD_PAGE));
                subService.create(sub);
                sub = subService.find(sub.getInfo().getLogin());
                request.getSession().setAttribute(View.SUBSCRIBER_SESSION, sub);
                Command command = CommandList.valueOf(View.USER_CABINET).getCommand();
                return command.execute(request, response);
            }else {
                request.setAttribute(View.SAVED_FIRST_NAME, request.getParameter(View.FIRST_NAME_PAGE));
                request.setAttribute(View.SAVED_SECOND_NAME, request.getParameter(View.SECOND_NAME_PAGE));
                request.setAttribute(View.SAVED_PASSWORD, request.getParameter(View.PASSWORD_PAGE));
                request.setAttribute(View.LOGIN_IN_USE_PAGE,  bundle.getString(View.LOGIN_IN_USE));
                request.setAttribute(View.USER_INFO_PAGE,  bundle.getString(View.USER_INFO));
                request.setAttribute(View.FIRST_NAME_PAGE,  bundle.getString(View.FIRST_NAME));
                request.setAttribute(View.SECOND_NAME_PAGE,  bundle.getString(View.SECOND_NAME));
                request.setAttribute(View.LOGIN_PAGE,  bundle.getString(View.LOGIN));
                request.setAttribute(View.PASSWORD_PAGE,  bundle.getString(View.PASSWORD));
                request.setAttribute(View.PASSWORD_PAGE,  bundle.getString(View.PASSWORD));
                request.setAttribute(View.CREATE_PAGE, bundle.getString(View.CREATE));
                return ViewURL.CREATE_SUB_JSP;
            }
        }catch (DAOException e){
            request.setAttribute(View.ERROR_CAUSE, View.CANT_DO_REQUEST);
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
