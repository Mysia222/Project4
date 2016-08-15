import command.ServiceServicesList;
import command.ServiceSubscribersList;
import ent.Service;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import services.ServService;
import services.SubService;
import views.View;
import views.ViewURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

/**
 * Created by Potaychuk Sviatoslav on 07.08.2016.
 */
public class ServiceSubscribersListTest extends Mockito{
    @Test
    public void execute() throws Exception {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        ServiceSubscribersList command = new ServiceSubscribersList();
        SubService service = mock(SubService.class);
        command.setSubService(service);
        List list = new ArrayList<>();
        ResourceBundle bundle = ResourceBundle.getBundle(View.BUNDLE_NAME,View.localeUA);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(View.BUNDLE)).thenReturn(bundle);
        when(request.getParameter(View.NAME_PAGE)).thenReturn("test");
        when(service.getSubsList()).thenReturn(list);
        String testString = command.execute(request,response);

        verify(request, atLeastOnce()).setAttribute(View.USER_LIST_PAGE, list);
        verify(request, atLeastOnce()).setAttribute(View.FIRST_NAME_PAGE_H,  bundle.getString(View.FIRST_NAME));
        verify(request, atLeastOnce()).setAttribute(View.SECOND_NAME_PAGE_H,  bundle.getString(View.SECOND_NAME));
        verify(request, atLeastOnce()).setAttribute(View.LOGIN_PAGE_H,  bundle.getString(View.LOGIN));
        verify(request, atLeastOnce()).setAttribute(View.BALANCE_PAGE_H,  bundle.getString(View.BALANCE));
        verify(request, atLeastOnce()).setAttribute(View.CONTRACT_PAGE_H,  bundle.getString(View.CONTRACT));
        verify(request, atLeastOnce()).setAttribute(View.CURRENT_SERVICE_PAGE_H,  bundle.getString(View.CURRENT_SERVICE));
        verify(request, atLeastOnce()).setAttribute(View.BLOCK_SUBSCRIBER_BUTTON,  bundle.getString(View.BLOCK_SUBSCRIBER));
        verify(request, atLeastOnce()).setAttribute(View.UNLOCK_SUBSCRIBER_BUTTON,  bundle.getString(View.UNLOCK_SUBSCRIBER));
        verify(request, atLeastOnce()).setAttribute(View.ENABLE_ACTIONS_PAGE,  bundle.getString(View.ENABLE_ACTIONS));
        verify(request, atLeastOnce()).setAttribute(View.REFRESH_BUTTON,  bundle.getString(View.REFRESH));
        verify(request, atLeastOnce()).setAttribute(View.RETURN_CABINET_PAGE,  bundle.getString(View.RETURN_CABINET));
        assertEquals(testString, ViewURL.USER_LIST_JSP);

    }

}