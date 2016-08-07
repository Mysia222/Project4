import command.ServiceSaveService;
import command.ServiceServicesList;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

/**
 * Created by Славик on 07.08.2016.
 */
public class ServiceServicesListTest extends Mockito {
    @Test
    public void execute() throws Exception {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        ServiceServicesList command = new ServiceServicesList();
        ServService service = mock(ServService.class);
        command.setServService(service);
        List list = new ArrayList<Service>();
        ResourceBundle bundle = ResourceBundle.getBundle(View.BUNDLE_NAME,View.localeUA);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(View.BUNDLE)).thenReturn(bundle);
        when(request.getParameter(View.NAME_PAGE)).thenReturn("test");
        when(service.getServiceList()).thenReturn(list);
        String testString = command.execute(request,response);
        verify(request,atLeastOnce()).setAttribute(View.SERVICES_LIST_PAGE,list);
        verify(request,atLeastOnce()).setAttribute(View.NAME_PAGE, bundle.getString(View.NAME));
        verify(request,atLeastOnce()).setAttribute(View.PRICE_PAGE, bundle.getString(View.PRICE));
        verify(request,atLeastOnce()).setAttribute(View.CHANGE_PAGE, bundle.getString(View.CHANGE));
        verify(request,atLeastOnce()).setAttribute(View.DELETE_PAGE, bundle.getString(View.DELETE));
        verify(request,atLeastOnce()).setAttribute(View.RETURN_CABINET_PAGE, bundle.getString(View.RETURN_CABINET));
        verify(request,atLeastOnce()).setAttribute(View.SAVE_PAGE, bundle.getString(View.SAVE));
        verify(request,atLeastOnce()).setAttribute(View.CREATE_SERVICE_BUTTON, bundle.getString(View.CREATE_SERVICE));
        assertEquals(testString, ViewURL.SERVICE_LIST_EDIT_JSP);

    }

}