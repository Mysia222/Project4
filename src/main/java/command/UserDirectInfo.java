package command;

import dao.DAOException;
import ent.Subscriber;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
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

    /**
     * Logger
     */
    private static Logger log =  Logger.getLogger(UserDirectInfo.class);

    /**
     * It's subscriber's service
     */
    private SubService subService = SubService.getInstance();

    /**
     * This method updates user's info (login, password, name).
     * If request is from Home.jsp, method sets attributes and return String url of DirectInfo.jsp
     * If request from DirectInfo.jsp method checks for login free and if it's - saves info and executes UserCabinet command
     * else method informs about incorrect login
     * @param request is request which will be processing
     * @param response is response after processing
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.trace(View.COMMAND_EXECUTE + this.getClass().getName());
        ResourceBundle bundle = (ResourceBundle)request.getSession().getAttribute(View.BUNDLE);
        Subscriber sub = (Subscriber)request.getSession().getAttribute(View.SUBSCRIBER_SESSION);
        //request from Home.jsp
        if(request.getParameter(View.DIRECT_INFO_BUTTON).equals(bundle.getString(View.DIRECT_INFO))){
            request = setPatterns(request);
            request.setAttribute(View.SAVED_FIRST_NAME, sub.getInfo().getFirstName());
            request.setAttribute(View.SAVED_SECOND_NAME, sub.getInfo().getSecondName());
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

                //empty fields
                //inform about it while all fields will be filled according patterns
                if (request.getParameter(View.FIRST_NAME_PAGE).equals("") || request.getParameter(View.SECOND_NAME_PAGE).equals("")||
                        request.getParameter(View.LOGIN_PAGE).equals("")|| request.getParameter(View.PASSWORD_PAGE).equals("")){
                    request = setPatterns(request);
                    request=setPatterns(request);
                    request.setAttribute(View.SAVED_FIRST_NAME, sub.getInfo().getFirstName());
                    request.setAttribute(View.SAVED_SECOND_NAME, sub.getInfo().getSecondName());
                    request.setAttribute(View.SAVED_LOGIN, sub.getInfo().getLogin());
                    request.setAttribute(View.LOGIN_IN_USE_PAGE, bundle.getString(View.EMPTY_FIELDS));
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
                }

                //login in use?
                if (temp!=null && temp.getContract()!=sub.getContract()){
                    log.trace(View.LOG_ENABLE_LOGIN);
                    request=setPatterns(request);
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
                    //all right
                }else {
                    log.trace(View.LOG_ABLE_LOGIN);
                    sub.getInfo().setLogin(request.getParameter(View.LOGIN_PAGE));
                    sub.getInfo().setPassword(DigestUtils.md5Hex(request.getParameter(View.PASSWORD_PAGE)));
                    sub.getInfo().setFirstName(request.getParameter(View.FIRST_NAME_PAGE));
                    sub.getInfo().setSecondName(request.getParameter(View.SECOND_NAME_PAGE));
                    request.getSession().setAttribute(View.SUBSCRIBER_SESSION, sub);
                    subService.setSub(sub);
                    Command command = CommandList.valueOf(View.USER_CABINET).getCommand();
                    return command.execute(request, response);
                }
            }catch (DAOException e){
                log.error(View.LOG_BY_USER + request.getSession().getAttribute(View.SUBSCRIBER_SESSION));
                request.setAttribute(View.ERROR_CAUSE, bundle.getString(View.CANT_DO_REQUEST));
                return ViewURL.ERROR_PAGE;
            }
        }
    }

    /**
     * This method setts login, name and password's patterns to jsp
     * @param request is request in which attributes patterns will be setted
     * @return request
     */
    private HttpServletRequest setPatterns(HttpServletRequest request){
        request.setAttribute(View.PATTERN_LOGIN_PAGE, View.PATTERN_LOGIN);
        request.setAttribute(View.PATTERN_NAME_PAGE, View.PATTERN_NAME);
        request.setAttribute(View.PATTERN_PASSWORD_PAGE, View.PATTERN_PASSWORD);
        request.setAttribute(View.USER_LOG_PATTERN_PAGE, ((ResourceBundle)request.getSession().getAttribute(View.BUNDLE)).getString(View.USER_LOG_PATTERN));
        request.setAttribute(View.USER_NAME_PATTERN_PAGE, ((ResourceBundle)request.getSession().getAttribute(View.BUNDLE)).getString(View.USER_NAME_PATTERN));
        request.setAttribute(View.USER_PASSWORD_PATTERN_PAGE, ((ResourceBundle)request.getSession().getAttribute(View.BUNDLE)).getString(View.USER_PASSWORD_PATTERN));
        return request;
    }
    //getters & setters
    public SubService getSubService() {
        return subService;
    }

    public void setSubService(SubService subService) {
        this.subService = subService;
    }
}
