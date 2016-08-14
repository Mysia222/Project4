import command.UserCabinet;
import command.UserCreate;
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
public class UserCreateTest extends Mockito{



    @Test
    public void executeFromIndexJsp() throws Exception {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        UserCreate command = new UserCreate();
        ResourceBundle bundle = ResourceBundle.getBundle(View.BUNDLE_NAME,View.localeUA);

        when(request.getSession()).thenReturn(session);
        when(request.getParameter(View.CREATE_ACCOUNT_PAGE)).thenReturn(View.CREATE_ACCOUNT_PAGE_DEFAULT);
        when(session.getAttribute(View.BUNDLE)).thenReturn(bundle);

        String testString = command.execute(request,response);

        verify(request,atLeastOnce()).setAttribute(View.SAVED_FIRST_NAME, "");
        verify(request,atLeastOnce()).setAttribute(View.SAVED_SECOND_NAME, "");
        verify(request,atLeastOnce()).setAttribute(View.SAVED_PASSWORD,"");
        verify(request,atLeastOnce()).setAttribute(View.USER_INFO_PAGE, bundle.getString(View.USER_INFO));
        verify(request,atLeastOnce()).setAttribute(View.FIRST_NAME_PAGE,  bundle.getString(View.FIRST_NAME));
        verify(request,atLeastOnce()).setAttribute(View.SECOND_NAME_PAGE,  bundle.getString(View.SECOND_NAME));
        verify(request,atLeastOnce()).setAttribute(View.LOGIN_PAGE,  bundle.getString(View.LOGIN));
        verify(request,atLeastOnce()).setAttribute(View.PASSWORD_PAGE,  bundle.getString(View.PASSWORD));
        verify(request,atLeastOnce()).setAttribute(View.PASSWORD_PAGE,  bundle.getString(View.PASSWORD));
        verify(request,atLeastOnce()).setAttribute(View.CREATE_PAGE,  bundle.getString(View.CREATE));

        assertEquals(testString, ViewURL.CREATE_SUB_JSP);
    }


    @Test
    public void executeFromCreateSubJsp() throws Exception {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        UserCreate command = new UserCreate();
        SubService service = mock(SubService.class);
        command.setSubService(service);
        ResourceBundle bundle = ResourceBundle.getBundle(View.BUNDLE_NAME,View.localeUA);

        when(request.getSession()).thenReturn(session);
        when(request.getParameter(View.CREATE_ACCOUNT_PAGE)).thenReturn("test");
        when(session.getAttribute(View.BUNDLE)).thenReturn(bundle);
        Subscriber sub = new Subscriber();
        when(request.getParameter(View.QUERY_LOGIN)).thenReturn("0");
        when(request.getParameter(View.FIRST_NAME_PAGE)).thenReturn("test");
        when(request.getParameter(View.SECOND_NAME_PAGE)).thenReturn("test");
        when(request.getParameter(View.LOGIN_PAGE)).thenReturn("test");
        when(request.getParameter(View.PASSWORD_PAGE)).thenReturn("test");

        when(service.find(request.getParameter(View.QUERY_LOGIN))).thenReturn(sub);

        String testString = command.execute(request,response);

        verify(request, atLeastOnce()).setAttribute(View.SAVED_FIRST_NAME, request.getParameter(View.FIRST_NAME_PAGE));
        verify(request, atLeastOnce()).setAttribute(View.SAVED_SECOND_NAME, request.getParameter(View.SECOND_NAME_PAGE));
        verify(request, atLeastOnce()).setAttribute(View.SAVED_PASSWORD, request.getParameter(View.PASSWORD_PAGE));
        verify(request, atLeastOnce()).setAttribute(View.LOGIN_IN_USE_PAGE,  bundle.getString(View.LOGIN_IN_USE));
        verify(request, atLeastOnce()).setAttribute(View.USER_INFO_PAGE,  bundle.getString(View.USER_INFO));
        verify(request, atLeastOnce()).setAttribute(View.FIRST_NAME_PAGE,  bundle.getString(View.FIRST_NAME));
        verify(request, atLeastOnce()).setAttribute(View.SECOND_NAME_PAGE,  bundle.getString(View.SECOND_NAME));
        verify(request, atLeastOnce()).setAttribute(View.LOGIN_PAGE,  bundle.getString(View.LOGIN));
        verify(request, atLeastOnce()).setAttribute(View.PASSWORD_PAGE,  bundle.getString(View.PASSWORD));
        verify(request, atLeastOnce()).setAttribute(View.PASSWORD_PAGE,  bundle.getString(View.PASSWORD));
        verify(request, atLeastOnce()).setAttribute(View.CREATE_PAGE, bundle.getString(View.CREATE));

        assertEquals(testString, ViewURL.CREATE_SUB_JSP);
    }
}