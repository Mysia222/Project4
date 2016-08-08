package command;

import views.View;
import views.ViewURL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Created by Potaychuck Sviatoslav on 05.08.2016.
 */
public class DrawIndex implements Command {

    /**
     * This method sets attributes to Index.jsp according session's Locale
     * @param request is request which will be processing
     * @param response is response after processing
     * @return String url of Index.jsp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResourceBundle bundle = (ResourceBundle)request.getSession().getAttribute(View.BUNDLE) ;
        request.setAttribute(View.PASSWORD_PAGE, bundle.getString(View.PASSWORD));
        request.setAttribute(View.LOGIN_PAGE,  bundle.getString(View.LOGIN));
        request.setAttribute(View.CREATE_ACCOUNT_PAGE,  bundle.getString(View.CREATE_ACCOUNT));
        request.setAttribute(View.ENTER_PAGE,  bundle.getString(View.ENTER));
        return  ViewURL.INDEX_JSP;
    }
}
