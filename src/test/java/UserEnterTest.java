import command.UserCreate;
import command.UserEnter;
import dao.jdbc.JdbcDaoFactory;
import ent.Subscriber;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import services.SubService;
import views.View;
import views.ViewURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.DriverManager;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

/**
 * Created by Славик on 07.08.2016.
 */
public class UserEnterTest extends Mockito{


    @Test
    public void executeSuccessfulEnter() throws Exception {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        UserEnter command = new UserEnter();
        SubService service = mock(SubService.class);
        command.setSubService(service);
        ResourceBundle bundle = ResourceBundle.getBundle(View.BUNDLE_NAME,View.localeUA);
        Subscriber sub = new Subscriber();
        sub.setContract(1);
        sub.setInfo(sub.new SubInfo("test","test","test","test"));

        when(request.getSession()).thenReturn(session);
        when(request.getParameter(View.PASSWORD_PAGE)).thenReturn("testPassword");
        when(session.getAttribute(View.SUBSCRIBER_SESSION)).thenReturn(sub);
        when(service.exist(anyString(),anyString())).thenReturn(true);
        when(service.find(anyString())).thenReturn(sub);
        when(session.getAttribute(View.BUNDLE)).thenReturn(bundle);
        JdbcDaoFactory.setConnection(DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "root"));
        String testString =command.execute(request,response);

        verify(session,atLeastOnce()).getAttribute(View.BUNDLE);
        verify(request,atLeastOnce()).getSession();
        verify(service,atLeastOnce()).exist(anyString(),anyString());
        verify(service,atLeastOnce()).find(anyString());

        assertEquals(testString,ViewURL.ERROR_PAGE);
    }


    @Test
    public void executeFailEnter() throws Exception {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        UserEnter command = new UserEnter();
        SubService service = mock(SubService.class);
        command.setSubService(service);
        ResourceBundle bundle = ResourceBundle.getBundle(View.BUNDLE_NAME,View.localeUA);
        Subscriber sub = new Subscriber();
        sub.setInfo(sub.new SubInfo());

        when(request.getSession()).thenReturn(session);
        when(request.getParameter(View.PASSWORD_PAGE)).thenReturn("testPassword");
        when(session.getAttribute(View.SUBSCRIBER_SESSION)).thenReturn(sub);
        when(service.exist(anyString(),anyString())).thenReturn(false);
        when(service.find(anyString())).thenReturn(sub);
        when(session.getAttribute(View.BUNDLE)).thenReturn(bundle);
        JdbcDaoFactory.setConnection(DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "root"));
        String testString =command.execute(request,response);

        verify(session,atLeastOnce()).getAttribute(View.BUNDLE);
        verify(request,atLeastOnce()).getSession();
        verify(service,atLeastOnce()).exist(anyString(),anyString());
        verify(service,atLeastOnce()).find(anyString());
        verify(request,atLeastOnce()).setAttribute(View.WRONG_LOGIN_PAGE, bundle.getString(View.WRONG_PASSWORD));
        verify(request,atLeastOnce()).setAttribute(View.PREPARED_LOGIN, request.getParameter(View.LOGIN_PAGE));

        assertEquals(testString,ViewURL.INDEX_JSP);
    }

}