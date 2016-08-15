import command.ServiceDeleteService;
import command.ServiceSaveService;
import dao.jdbc.JdbcDaoFactory;
import ent.Service;
import org.junit.Test;
import org.mockito.Mockito;
import services.ServService;
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
public class ServiceSaveServiceTest extends Mockito{


    @Test
    public void executeNameInUse() throws Exception {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        ServiceSaveService command = new ServiceSaveService();
        ServService service = mock(ServService.class);
        command.setServService(service);
        ResourceBundle bundle = ResourceBundle.getBundle(View.BUNDLE_NAME,View.localeUA);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(View.BUNDLE)).thenReturn(bundle);
        when(request.getParameter(View.NAME_PAGE)).thenReturn("test");
        when(service.nameInUse(request.getParameter(View.NAME_PAGE))).thenReturn(true);
        when(request.getParameter(View.NAME_PAGE)).thenReturn(View.JUST_CREATED_SERVICE_NAME);
        JdbcDaoFactory.setConnection(DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "root"));
        String test = command.execute(request,response);
        verify(request,atLeast(1)).getSession();
        verify(session,atLeast(1)).getAttribute(View.BUNDLE);
        verify(request,atLeast(1)).getParameter(View.NAME_PAGE);
        assertEquals(test, ViewURL.SERVICE_LIST_EDIT_JSP);
    }


    @Test
    public void executeFree() throws Exception {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        ServiceSaveService command = new ServiceSaveService();
        ServService service = mock(ServService.class);
        command.setServService(service);
        ResourceBundle bundle = ResourceBundle.getBundle(View.BUNDLE_NAME,View.localeUA);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(View.ID_PAGE)).thenReturn("0");
        when(session.getAttribute(View.BUNDLE)).thenReturn(bundle);
        when(request.getParameter(View.NAME_PAGE)).thenReturn("test");
        when(service.nameInUse(request.getParameter(View.NAME_PAGE))).thenReturn(true);
        when(service.getService(0)).thenReturn(new Service());
        when(request.getParameter(View.NAME_PAGE)).thenReturn("test");
        JdbcDaoFactory.setConnection(DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "root"));
        String test = command.execute(request,response);
        verify(request,atLeast(1)).getSession();
        verify(session,atLeast(1)).getAttribute(View.BUNDLE);
        verify(request,atLeast(1)).getParameter(View.NAME_PAGE);
        assertEquals(test, ViewURL.SERVICE_LIST_EDIT_JSP);
    }

}