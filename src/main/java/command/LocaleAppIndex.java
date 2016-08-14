package command;

import org.apache.log4j.Logger;
import views.View;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Created by  Potaychuck Sviatoslav on 04.08.2016.
 */
public class LocaleAppIndex implements Command {

    /**
     * Logger
     */
    private static Logger log =  Logger.getLogger(LocaleAppIndex.class);

    /**
     * This method sets Locale in session in cabinet and draws Index.jsp
     * @param request is request which will be processing
     * @param response is response after processing
     * @return execution result of DrawIndex command
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
        Command command = CommandList.valueOf(View.DRAW_INDEX).getCommand();
        return command.execute(request,response);
    }
}
