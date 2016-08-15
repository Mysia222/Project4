import command.LocaleApp;
import command.LocaleAppIndex;
import dao.jdbc.JdbcDaoFactory;
import ent.Subscriber;
import org.junit.Test;
import org.mockito.Mockito;
import views.View;
import views.ViewURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.DriverManager;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

/**
 * Created by Potaychuk Sviatoslav on 06.08.2016.
 */
public class LocaleAppIndexTest extends Mockito{
    @Test
    public void execute() throws Exception {
        Subscriber sub = new Subscriber();
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute(View.BUNDLE)).thenReturn(ResourceBundle.getBundle(View.BUNDLE_NAME,View.localeUA));
        when(session.getAttribute(View.SUBSCRIBER_SESSION)).thenReturn(sub);
        when(request.getParameter(View.COUNTRY_PAGE)).thenReturn(View.UA);
        assertTrue(new LocaleAppIndex().execute(request,response).equals(ViewURL.INDEX_JSP));
    }

}