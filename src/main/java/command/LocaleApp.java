package command;

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
        String country = request.getParameter("country");
        if(request.getParameter("country").equals("UA")){
            View.setBundle(ResourceBundle.getBundle(View.BUNDLE_NAME , new Locale("uk" ,"UA")));
        }else {
            View.setBundle(ResourceBundle.getBundle(View.BUNDLE_NAME , new Locale("en" ,"EN")));
        }
        Command command = CommandList.valueOf(View.USER_CABINET).getCommand();
        return command.execute(request,response);
    }
}
