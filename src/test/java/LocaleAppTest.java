import command.Command;
import command.DrawIndex;
import command.LocaleApp;
import dao.DAOException;
import dao.jdbc.JdbcDaoFactory;
import ent.Subscriber;
import org.junit.Test;
import org.mockito.Mockito;
import services.SubService;
import views.View;
import views.ViewURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.AssertTrue;
import java.sql.DriverManager;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

/**
 * Created by Славик on 06.08.2016.
 */
public class LocaleAppTest extends Mockito{

    @Test
    public void execute() throws Exception {
        Subscriber sub = new Subscriber();
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(View.SUBSCRIBER_SESSION)).thenReturn(sub);
        when(request.getParameter(View.COUNTRY_PAGE)).thenReturn(View.UA);
        JdbcDaoFactory.setConnection(DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "root"));
        assertTrue(new LocaleApp().execute(request,response).equals(ViewURL.ERROR_PAGE));
    }


}