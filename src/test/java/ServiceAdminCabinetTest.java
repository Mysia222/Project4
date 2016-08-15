import command.ServiceAdminCabinet;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import views.View;
import views.ViewURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ResourceBundle;

import static org.junit.Assert.*;

/**
 * Created by Potaychuk Sviatoslav on 06.08.2016.
 */
public class ServiceAdminCabinetTest extends Mockito {
    @Test
    public void execute() throws Exception {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        ResourceBundle bundle = ResourceBundle.getBundle(View.BUNDLE_NAME,View.localeUA);
        when(session.getAttribute(View.BUNDLE)).thenReturn(bundle);
        String testString = new ServiceAdminCabinet().execute(request,response);
        verify(request,atLeast(1)).setAttribute(View.LOGOUT_BUTTON, bundle.getString(View.LOGOUT));
        verify(request,atLeast(1)).setAttribute(View.ADMIN_VIEW_SUBS_BUTTON, bundle.getString(View.ADMIN_VIEW_SUBS));
        verify(request,atLeast(1)).setAttribute(View.ADMIN_VIEW_SERVICES_BUTTON, bundle.getString(View.ADMIN_VIEW_SERVICES));
        verify(request,atLeast(1)).setAttribute(View.ADMIN_CREATE_SERVICE_BUTTON, bundle.getString(View.ADMIN_CREATE_SERVICE));
        assertEquals(testString, ViewURL.ADMIN_HOME_JSP);
    }

}