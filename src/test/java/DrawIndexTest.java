import command.DrawIndex;
import org.junit.Test;
import org.mockito.Mockito;
import views.View;
import views.ViewURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ResourceBundle;

import static org.junit.Assert.*;

/**
 * Created by Potaychuk Sviatoslav on 05.08.2016.
 */
public class DrawIndexTest extends Mockito{

    @Test
    public void execute() throws Exception {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        ResourceBundle bundle = ResourceBundle.getBundle(View.BUNDLE_NAME,View.localeUA);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(View.BUNDLE)).thenReturn(bundle);
        String testString = new DrawIndex().execute(request,response);
        verify(request, atLeast(1)).getSession();
        verify(session, atLeast(1)).getAttribute(View.BUNDLE);
        verify(request, atLeast(1)).setAttribute(View.PASSWORD_PAGE,bundle.getString(View.PASSWORD) );
        verify(request, atLeast(1)).setAttribute(View.LOGIN_PAGE,  bundle.getString(View.LOGIN));
        verify(request, atLeast(1)).setAttribute(View.CREATE_ACCOUNT_PAGE,  bundle.getString(View.CREATE_ACCOUNT));
        verify(request, atLeast(1)).setAttribute(View.ENTER_PAGE,  bundle.getString(View.ENTER));
        assertEquals(testString, ViewURL.INDEX_JSP);
    }

}