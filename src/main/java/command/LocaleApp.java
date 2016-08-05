package command;

import ent.Subscriber;
import views.View;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by potaychuk on 04.08.2016.
 */
public class LocaleApp implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String country = request.getParameter(View.COUNTRY_PAGE);
        if(request.getParameter(View.COUNTRY_PAGE).equals(View.UA)){
           request.getSession().setAttribute(View.BUNDLE,ResourceBundle.getBundle(View.BUNDLE_NAME , View.localeUA));
        }else {
            request.getSession().setAttribute(View.BUNDLE,ResourceBundle.getBundle(View.BUNDLE_NAME , View.localeEN));
        }
        Command command = ((Subscriber)request.getSession().getAttribute(View.SUBSCRIBER_SESSION)).isAdmin() ?
                CommandList.valueOf(View.ADMIN_CABINET).getCommand() :  CommandList.valueOf(View.USER_CABINET).getCommand();
        return command.execute(request,response);
    }
}
