import command.UserCreate;
import command.UserDirectService;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

/**
 * Created by Славик on 07.08.2016.
 */
public class UserDirectServiceTest  extends Mockito{


    @Test
    public void execute() throws Exception {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        SubService subService = mock(SubService.class);
        ServService servService = mock(ServService.class);
        UserDirectService command = new UserDirectService();
        command.setSubService(subService);
        command.setServService(servService);
        ResourceBundle bundle = ResourceBundle.getBundle(View.BUNDLE_NAME,View.localeUA);
        Subscriber sub = new Subscriber();
        sub.setInfo(sub.new SubInfo());

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(View.SUBSCRIBER_SESSION)).thenReturn(sub);
        when(session.getAttribute(View.BUNDLE)).thenReturn(bundle);
        when(subService.find(sub.getInfo().getLogin())).thenReturn(sub);
        when(servService.getServiceList()).thenReturn(new ArrayList<Service>());

        String testString = command.execute(request,response);

        verify(request,atLeastOnce()).setAttribute(View.REMOVE_USER_SERVICE_PAGE, bundle.getString(View.REMOVE_USER_SERVICE));
        verify(request,atLeastOnce()).setAttribute(View.SET_USER_SERVICE_PAGE, bundle.getString(View.SET_USER_SERVICE));
        verify(request,atLeastOnce()).setAttribute(View.PRICE_PAGE, bundle.getString(View.PRICE));
        verify(request,atLeastOnce()).setAttribute(View.NAME_PAGE, bundle.getString(View.NAME));
        verify(request,atLeastOnce()).setAttribute(View.ENABLE_ACTIONS_PAGE, bundle.getString(View.ENABLE_ACTIONS));
        verify(request,atLeastOnce()).setAttribute(View.RETURN_CABINET_PAGE, bundle.getString(View.RETURN_CABINET));

        assertEquals(testString, ViewURL.SERVICE_LIST_JSP);

    }

}