import command.ServiceUnlockSubscriber;
import command.UserCabinet;
import ent.Subscriber;
import org.junit.Test;
import org.mockito.Mockito;
import services.SubService;
import views.View;
import views.ViewURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

/**
 * Created by Славик on 07.08.2016.
 */
public class UserCabinetTest extends Mockito{

    @Test
    public void execute() throws Exception {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        UserCabinet command = new UserCabinet();
        SubService service = mock(SubService.class);
        command.setSubService(service);
        Subscriber sub = new Subscriber();
        sub.setInfo(sub.new SubInfo());
        ResourceBundle bundle = ResourceBundle.getBundle(View.BUNDLE_NAME,View.localeUA);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(View.SUBSCRIBER_SESSION)).thenReturn(sub);
        when(session.getAttribute(View.BUNDLE)).thenReturn(bundle);
        when(service.find(0)).thenReturn(sub);
        String testString =command.execute(request,response);
        verify(request, atLeastOnce()).setAttribute(View.BLOCKED_PAGE, sub.isBlocked()?bundle.getString(View.BLOCKED_TRUE):bundle.getString(View.BLOCKED_FALSE));
        verify(request, atLeastOnce()).setAttribute(View.FIRST_NAME_PAGE, sub.getInfo().getFirstName());
        verify(request, atLeastOnce()).setAttribute(View.SECOND_NAME_PAGE, sub.getInfo().getSecondName());
        verify(request, atLeastOnce()).setAttribute(View.LOGIN_PAGE, sub.getInfo().getLogin());
//        verify(request, atLeastOnce()).setAttribute(View.PASSWORD_PAGE, sub.getInfo().getPassword());
        verify(request, atLeastOnce()).setAttribute(View.BALANCE_PAGE, sub.getBalance());
        verify(request, atLeastOnce()).setAttribute(View.CONTRACT_PAGE, sub.getContract());
        verify(request, atLeastOnce()).setAttribute(View.CURRENT_SERVICE_PAGE, sub.getCurrentService());
        verify(request, atLeastOnce()).setAttribute(View.FIRST_NAME_PAGE_H, bundle.getString(View.FIRST_NAME));
        verify(request, atLeastOnce()).setAttribute(View.SECOND_NAME_PAGE_H, bundle.getString(View.SECOND_NAME));
        verify(request, atLeastOnce()).setAttribute(View.LOGIN_PAGE_H, bundle.getString(View.LOGIN));
//        verify(request, atLeastOnce()).setAttribute(View.PASSWORD_PAGE_H, bundle.getString(View.PASSWORD));
        verify(request, atLeastOnce()).setAttribute(View.BALANCE_PAGE_H, bundle.getString(View.BALANCE));
        verify(request, atLeastOnce()).setAttribute(View.CONTRACT_PAGE_H, bundle.getString(View.CONTRACT));
        verify(request, atLeastOnce()).setAttribute(View.CURRENT_SERVICE_PAGE_H, bundle.getString(View.CURRENT_SERVICE));
        verify(request, atLeastOnce()).setAttribute(View.DIRECT_SERVICE_BUTTON, bundle.getString(View.DIRECT_SERVICE));
        verify(request, atLeastOnce()).setAttribute(View.FOOT_THE_BILL_PAGE, bundle.getString(View.FOOT_THE_BILL));
        verify(request, atLeastOnce()).setAttribute(View.LOGOUT_PAGE, bundle.getString(View.LOGOUT));
        verify(request, atLeastOnce()).setAttribute(View.DIRECT_INFO_BUTTON, bundle.getString(View.DIRECT_INFO));
        assertEquals(testString, ViewURL.HOME_JSP);
    }

}