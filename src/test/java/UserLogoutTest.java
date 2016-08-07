import command.UserFootTheBill;
import command.UserLogout;
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
public class UserLogoutTest extends Mockito{
    @Test
    public void execute() throws Exception {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        UserLogout command = new UserLogout();
        ResourceBundle bundle = ResourceBundle.getBundle(View.BUNDLE_NAME,View.localeUA);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(View.BUNDLE)).thenReturn(bundle);

        String testString = command.execute(request,response);

        verify(session, atLeastOnce()).removeAttribute(View.SUBSCRIBER_SESSION);
        verify(request, atLeastOnce()).setAttribute(View.PASSWORD_PAGE, bundle.getString(View.PASSWORD));
        verify(request, atLeastOnce()).setAttribute(View.LOGIN_PAGE, bundle.getString(View.LOGIN));
        verify(request, atLeastOnce()).setAttribute(View.CREATE_ACCOUNT_PAGE, bundle.getString(View.CREATE_ACCOUNT));
        verify(request, atLeastOnce()).setAttribute(View.ENTER_PAGE, bundle.getString(View.ENTER));

        assertEquals(testString, ViewURL.INDEX_JSP);
    }

}