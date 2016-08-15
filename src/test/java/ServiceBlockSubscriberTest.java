import command.ServiceBlockSubscriber;
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

import java.sql.DriverManager;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

/**
 * Created by Potaychuk Sviatoslav on 06.08.2016.
 */
public class ServiceBlockSubscriberTest extends Mockito{
    @Test
    public void execute() throws Exception {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        ServiceBlockSubscriber command = new ServiceBlockSubscriber();
        SubService service = mock(SubService.class);
        command.setSubService(service);
        ResourceBundle bundle = ResourceBundle.getBundle(View.BUNDLE_NAME,View.localeUA);
        when(session.getAttribute(View.BUNDLE)).thenReturn(bundle);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(View.ID_PAGE)).thenReturn("0");
        when(session.getAttribute(View.BUNDLE)).thenReturn(bundle);
        when(service.find(Integer.parseInt(request.getParameter(View.ID_PAGE)))).thenReturn(new Subscriber());
        JdbcDaoFactory.setConnection(DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "root"));
        command.execute(request,response);
        verify(service,atLeastOnce()).blockId(anyInt());
        verify(session,atLeastOnce()).getAttribute(View.BUNDLE);
        verify(request,atLeastOnce()).getSession();
        assertEquals( command.execute(request,response), ViewURL.ERROR_PAGE);

    }

}