package command;

import ent.Subscriber;
import org.apache.log4j.Logger;
import views.View;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by  Potaychuck Sviatoslav on 04.08.2016.
 */
public class LocaleApp implements Command {

    /**
     * Logger
     */
    private static Logger log =  Logger.getLogger(LocaleApp.class);

    /**
     * This method sets Locale in session in cabinet
     * @param request is request which will be processing
     * @param response is response after processing
     * @return execution result of ServiceAdminCabinet or UserCabinet in according session's sub
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.trace(View.COMMAND_EXECUTE + this.getClass().getName());
        if(request.getParameter(View.COUNTRY_PAGE).equals(View.UA)){
           request.getSession().setAttribute(View.BUNDLE,ResourceBundle.getBundle(View.BUNDLE_NAME , View.localeUA));
        }else {
            request.getSession().setAttribute(View.BUNDLE,ResourceBundle.getBundle(View.BUNDLE_NAME , View.localeEN));
        }
        log.trace(View.LOG_LOCALE_SETTED+request.getSession().getAttribute(View.BUNDLE)+View.LOG_SUB+request.getSession().getAttribute(View.SUBSCRIBER_SESSION));
        Command command = ((Subscriber)request.getSession().getAttribute(View.SUBSCRIBER_SESSION)).isAdmin() ?
                CommandList.valueOf(View.ADMIN_CABINET).getCommand() :  CommandList.valueOf(View.USER_CABINET).getCommand();
        return command.execute(request,response);
    }
}
