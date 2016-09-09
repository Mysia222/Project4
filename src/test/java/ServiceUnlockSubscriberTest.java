import command.ServiceSubscribersList;
import command.ServiceUnlockSubscriber;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

/**
 * Created by Potaychuk Sviatoslav on 07.08.2016.
 */
public class ServiceUnlockSubscriberTest  extends Mockito{


    @Test
    public void execute() throws Exception {
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpSession session = mock(HttpSession.class);
//        ServiceUnlockSubscriber command = new ServiceUnlockSubscriber();
//        SubService service = mock(SubService.class);
//        command.setSubService(service);
//        Subscriber sub = new Subscriber();
//        ResourceBundle bundle = ResourceBundle.getBundle(View.BUNDLE_NAME,View.localeUA);
//        when(request.getSession()).thenReturn(session);
//        when(service.find(anyInt())).thenReturn(sub);
//        when(session.getAttribute(View.BUNDLE)).thenReturn(bundle);
//        when(request.getParameter(View.NAME_PAGE)).thenReturn("test");
//        when(request.getParameter(View.ID_PAGE)).thenReturn("0");
//        JdbcDaoFactory.setConnection(DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "root"));
//        String testString = command.execute(request,response);
//
//        verify(service,atLeastOnce()).unlockId(anyInt());
//        verify(session,atLeastOnce()).getAttribute(View.BUNDLE);
//        verify(request,atLeastOnce()).getSession();
//        assertEquals(testString, ViewURL.USER_LIST_JSP);

    }

}