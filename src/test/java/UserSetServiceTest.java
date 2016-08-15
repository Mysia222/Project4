import command.UserRemoveService;
import command.UserSetService;
import dao.jdbc.JdbcDaoFactory;
import ent.Service;
import ent.Subscriber;
import org.junit.Test;
import org.mockito.Mockito;
import services.ServService;
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
 * Created by Potaychuk Sviatoslav on 07.08.2016.
 */
public class UserSetServiceTest extends Mockito {

    @Test
    public void executeAccountBlocked() throws Exception {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        UserSetService command = new UserSetService();
        ResourceBundle bundle = ResourceBundle.getBundle(View.BUNDLE_NAME,View.localeUA);
        ServService servService = mock(ServService.class);
        SubService subService = mock(SubService.class);
        command.setSubService(subService);
        command.setServService(servService);
        Subscriber sub = new Subscriber();
        sub.setBlocked(true);
        sub.setInfo(sub.new SubInfo());
        sub.getInfo().setLogin("test");
        sub.setContract(1);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(View.BUNDLE)).thenReturn(bundle);
        when(request.getParameter(View.ID_PAGE)).thenReturn("0");
        when(session.getAttribute(View.SUBSCRIBER_SESSION)).thenReturn(sub);
        when(subService.find(sub.getInfo().getLogin())).thenReturn(sub);


        JdbcDaoFactory.setConnection(DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "root"));
        String testString = command.execute(request,response);

        verify(subService,atLeastOnce()).find(anyString());

        assertEquals(testString, ViewURL.ERROR_PAGE);
    }


    @Test
    public void executeServiceInEdit() throws Exception {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        UserSetService command = new UserSetService();
        ResourceBundle bundle = ResourceBundle.getBundle(View.BUNDLE_NAME,View.localeUA);
        ServService servService = mock(ServService.class);
        SubService subService = mock(SubService.class);
        command.setSubService(subService);
        command.setServService(servService);
        Subscriber sub = new Subscriber();
        sub.setInfo(sub.new SubInfo());
        sub.getInfo().setLogin("Admin");
        Service service = new Service();
        service.setEdit(true);
        sub.setContract(1);


        when(request.getSession()).thenReturn(session);
        when(servService.getService(anyInt())).thenReturn(service);
        when(session.getAttribute(View.BUNDLE)).thenReturn(bundle);
        when(request.getParameter(View.ID_PAGE)).thenReturn("0");
        when(session.getAttribute(View.SUBSCRIBER_SESSION)).thenReturn(sub);
        when(subService.find(sub.getInfo().getLogin())).thenReturn(sub);


        JdbcDaoFactory.setConnection(DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "root"));
        String testString = command.execute(request,response);

        verify(subService,atLeastOnce()).find(anyString());
        verify(request,atLeastOnce()).setAttribute(View.USER_SERVICE_IN_USE_PAGE, bundle.getString(View.USER_SERVICE_IN_USE));

        assertEquals(testString, ViewURL.ERROR_PAGE);
    }

    @Test
    public void execute() throws Exception {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        UserSetService command = new UserSetService();
        ResourceBundle bundle = ResourceBundle.getBundle(View.BUNDLE_NAME,View.localeUA);
        ServService servService = mock(ServService.class);
        SubService subService = mock(SubService.class);
        command.setSubService(subService);
        command.setServService(servService);
        Subscriber sub = new Subscriber();
        sub.setInfo(sub.new SubInfo());
        sub.getInfo().setLogin("Ivan");
        Service service = new Service();
        sub.setContract(1);


        when(request.getSession()).thenReturn(session);
        when(servService.getService(anyInt())).thenReturn(service);
        when(session.getAttribute(View.BUNDLE)).thenReturn(bundle);
        when(request.getParameter(View.ID_PAGE)).thenReturn("0");
        when(session.getAttribute(View.SUBSCRIBER_SESSION)).thenReturn(sub);
        when(subService.find(sub.getInfo().getLogin())).thenReturn(sub);


        JdbcDaoFactory.setConnection(DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "root"));
        String testString = command.execute(request,response);

        verify(subService,atLeastOnce()).setBalance(any(Subscriber.class));
        verify(subService,atLeastOnce()).setService(any(Subscriber.class));

        assertEquals(testString, ViewURL.ERROR_PAGE);
    }

}