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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

/**
 * Created by potaychuk on 02.08.2016.
 */
public class UserCreate implements Command {

    /**
     * Logger
     */
    private static Logger log =  Logger.getLogger(UserCreate.class);

    /**
     * Subscriber's service
     */
    private SubService subService = SubService.getInstance();

    /**
     * This method creates new subscriber.
     * If request comes from Index.jsp - method sets attributes to CreateSub.jsp and return String url of CreateSub.jsp
     * If request comes from CreateSub.jsp method checks for unique login.
     * If login is able method creates new subscriber, sets it in session and returns UserCabinet command's execution result
     * If login is already in use, method will inform about it user with setting attributes to CreateSub.jsp and returning CreateSub.jsp String url
     * @param request is request which will be processing
     * @param response is response after processing
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.trace(View.COMMAND_EXECUTE + this.getClass().getName());
        if (request.getSession().getAttribute(View.BUNDLE)==null){
            request.getSession().setAttribute(View.BUNDLE,ResourceBundle.getBundle(View.BUNDLE_NAME , View.localeEN));
        }
        ResourceBundle bundle = (ResourceBundle)request.getSession().getAttribute(View.BUNDLE);
        //request from index.jsp
        //in this case CreateSub.jsp will be "painted" and it's url will be returned as String
        if(request.getParameter(View.CREATE_ACCOUNT_PAGE).equals(bundle.getString(View.CREATE_ACCOUNT)) ||
                request.getParameter(View.CREATE_ACCOUNT_PAGE).equals(View.CREATE_ACCOUNT_PAGE_DEFAULT)){
            log.trace(View.COMMAND_EXECUTE + this.getClass().getName());
            request = setPatterns(request);
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
        //in this case request will be checked for valid data and processed
        try{
            //empty fields
            //inform about it while all fields will be filled according patterns
            if (request.getParameter(View.FIRST_NAME_PAGE).equals("") || request.getParameter(View.SECOND_NAME_PAGE).equals("")||
                    request.getParameter(View.LOGIN_PAGE).equals("")|| request.getParameter(View.PASSWORD_PAGE).equals("")){
                request = setPatterns(request);
                request.setAttribute(View.SAVED_FIRST_NAME, request.getParameter(View.FIRST_NAME_PAGE));
                request.setAttribute(View.SAVED_SECOND_NAME, request.getParameter(View.SECOND_NAME_PAGE));
                request.setAttribute(View.SAVED_PASSWORD, request.getParameter(View.PASSWORD_PAGE));
                request.setAttribute(View.SAVED_LOGIN, request.getParameter(View.LOGIN_PAGE));
                request.setAttribute(View.LOGIN_IN_USE_PAGE,  bundle.getString(View.EMPTY_FIELDS));
                request.setAttribute(View.USER_INFO_PAGE,  bundle.getString(View.USER_INFO));
                request.setAttribute(View.FIRST_NAME_PAGE,  bundle.getString(View.FIRST_NAME));
                request.setAttribute(View.SECOND_NAME_PAGE,  bundle.getString(View.SECOND_NAME));
                request.setAttribute(View.LOGIN_PAGE,  bundle.getString(View.LOGIN));
                request.setAttribute(View.PASSWORD_PAGE,  bundle.getString(View.PASSWORD));
                request.setAttribute(View.PASSWORD_PAGE,  bundle.getString(View.PASSWORD));
                request.setAttribute(View.CREATE_PAGE, bundle.getString(View.CREATE));
                return ViewURL.CREATE_SUB_JSP;
            }
            Subscriber sub = subService.find(request.getParameter(View.QUERY_LOGIN));
            //Is login able?
            //if it's redirect to cabinet
            if(sub==null) {
                log.trace(View.LOG_ABLE_LOGIN);
                sub=new Subscriber();
                sub.setInfo(sub.new SubInfo());
                sub.getInfo().setFirstName(request.getParameter(View.FIRST_NAME_PAGE));
                sub.getInfo().setSecondName(request.getParameter(View.SECOND_NAME_PAGE));
                sub.getInfo().setLogin(request.getParameter(View.LOGIN_PAGE));
                sub.getInfo().setPassword(DigestUtils.md5Hex(request.getParameter(View.PASSWORD_PAGE)));
                subService.create(sub);
                sub = subService.find(sub.getInfo().getLogin());
                request.getSession().setAttribute(View.SUBSCRIBER_SESSION, sub);
                Command command = CommandList.valueOf(View.USER_CABINET).getCommand();
                return command.execute(request, response);
                //login in use
                //inform about it with attribute View.LOGIN_IN_USE_PAGE
            }else {
                log.trace(View.LOG_ENABLE_LOGIN);
                request.setAttribute(View.LOGIN_IN_USE_PAGE,  bundle.getString(View.LOGIN_IN_USE));
                request.setAttribute(View.SAVED_FIRST_NAME, request.getParameter(View.FIRST_NAME_PAGE));
                request.setAttribute(View.SAVED_SECOND_NAME, request.getParameter(View.SECOND_NAME_PAGE));
                request.setAttribute(View.SAVED_PASSWORD, request.getParameter(View.PASSWORD_PAGE));
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
            log.error(View.LOG_BY_USER + request.getSession().getAttribute(View.SUBSCRIBER_SESSION));
            request.setAttribute(View.ERROR_CAUSE, View.CANT_DO_REQUEST);
            return ViewURL.ERROR_PAGE;
        }

    }

    /**
     * This method setts login, name and password's patterns to jsp
     * @param request is request in which attributes patterns will be setted
     * @return request
     */
    private HttpServletRequest setPatterns(HttpServletRequest request){
        request.setAttribute(View.USER_LOG_PATTERN_PAGE, ((ResourceBundle)request.getSession().getAttribute(View.BUNDLE)).getString(View.USER_LOG_PATTERN));
        request.setAttribute(View.USER_NAME_PATTERN_PAGE, ((ResourceBundle)request.getSession().getAttribute(View.BUNDLE)).getString(View.USER_NAME_PATTERN));
        request.setAttribute(View.USER_PASSWORD_PATTERN_PAGE, ((ResourceBundle)request.getSession().getAttribute(View.BUNDLE)).getString(View.USER_PASSWORD_PATTERN));
        request.setAttribute(View.PATTERN_LOGIN_PAGE, View.PATTERN_LOGIN);
        request.setAttribute(View.PATTERN_NAME_PAGE, View.PATTERN_NAME);
        request.setAttribute(View.PATTERN_PASSWORD_PAGE, View.PATTERN_PASSWORD);
        return request;
    }

    public SubService getSubService() {
        return subService;
    }

    public void setSubService(SubService subService) {
        this.subService = subService;
    }
}
