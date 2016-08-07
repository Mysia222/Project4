import command.UserLogout;
import command.UserRemoveService;
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
 * Created by Славик on 07.08.2016.
 */
public class UserRemoveServiceTest extends Mockito {
    @Test
    public void execute() throws Exception {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        UserRemoveService command = new UserRemoveService();
        ResourceBundle bundle = ResourceBundle.getBundle(View.BUNDLE_NAME,View.localeUA);
        ServService servService = mock(ServService.class);
        SubService subService = mock(SubService.class);
        command.setSubService(subService);
        command.setServService(servService);
        Service service = new Service();
        Subscriber sub = new Subscriber();
        sub.setInfo(sub.new SubInfo());

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(View.BUNDLE)).thenReturn(bundle);
        when(request.getParameter(View.ID_PAGE)).thenReturn("0");
        when(session.getAttribute(View.SUBSCRIBER_SESSION)).thenReturn(sub);
        when(servService.getService(anyInt())).thenReturn(service);
        JdbcDaoFactory.setConnection(DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "root"));
        String testString = command.execute(request,response);

        verify(subService,atLeastOnce()).setService(any(Subscriber.class));

        assertEquals(testString, ViewURL.ERROR_PAGE);
    }

}