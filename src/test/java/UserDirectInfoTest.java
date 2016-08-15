import command.UserCreate;
import command.UserDirectInfo;
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
 * Created by Potaychuk Sviatoslav on 07.08.2016.
 */
public class UserDirectInfoTest extends Mockito {


    @Test
    public void executeFromHomeJsp() throws Exception {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        UserDirectInfo command = new UserDirectInfo();
        ResourceBundle bundle = ResourceBundle.getBundle(View.BUNDLE_NAME,View.localeUA);
        Subscriber sub = new Subscriber();
        sub.setInfo(sub.new SubInfo());

        when(request.getSession()).thenReturn(session);
        when(request.getParameter(View.DIRECT_INFO_BUTTON)).thenReturn(bundle.getString(View.DIRECT_INFO));
        when(session.getAttribute(View.BUNDLE)).thenReturn(bundle);
        when(session.getAttribute(View.SUBSCRIBER_SESSION)).thenReturn(sub);

        String testString = command.execute(request,response);
        verify(request, atLeastOnce()).setAttribute(View.SAVED_FIRST_NAME, sub.getInfo().getFirstName());
        verify(request, atLeastOnce()).setAttribute(View.SAVED_SECOND_NAME, sub.getInfo().getSecondName());
//        verify(request, atLeastOnce()).setAttribute(View.SAVED_PASSWORD, sub.getInfo().getPassword());
        verify(request, atLeastOnce()).setAttribute(View.SAVED_LOGIN, sub.getInfo().getLogin());
        verify(request, atLeastOnce()).setAttribute(View.USER_INFO_PAGE, bundle.getString(View.USER_INFO));
        verify(request, atLeastOnce()).setAttribute(View.FIRST_NAME_PAGE_H, bundle.getString(View.FIRST_NAME));
        verify(request, atLeastOnce()).setAttribute(View.SECOND_NAME_PAGE_H, bundle.getString(View.SECOND_NAME));
        verify(request, atLeastOnce()).setAttribute(View.LOGIN_PAGE_H, bundle.getString(View.LOGIN));
        verify(request, atLeastOnce()).setAttribute(View.PASSWORD_PAGE_H, bundle.getString(View.PASSWORD));
        verify(request, atLeastOnce()).setAttribute(View.PASSWORD_PAGE_H, bundle.getString(View.PASSWORD));
        verify(request, atLeastOnce()).setAttribute(View.CREATE_PAGE, bundle.getString(View.CREATE));
        verify(request, atLeastOnce()).setAttribute(View.SAVE_PAGE, bundle.getString(View.SAVE));
        verify(request, atLeastOnce()).setAttribute(View.RETURN_CABINET_PAGE, bundle.getString(View.RETURN_CABINET));


        assertEquals(testString, ViewURL.DIRECT_INFO_JSP);


    }


    @Test
    public void executeFromDirectInfoJsp() throws Exception {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        UserDirectInfo command = new UserDirectInfo();
        SubService service = mock(SubService.class);
        command.setSubService(service);
        ResourceBundle bundle = ResourceBundle.getBundle(View.BUNDLE_NAME,View.localeUA);
        Subscriber sub = new Subscriber();
        Subscriber sub2 = new Subscriber();
        sub2.setInfo(sub.new SubInfo());
        sub2.setContract(sub.getContract()+1);
        sub.setInfo(sub.new SubInfo());

        when(request.getSession()).thenReturn(session);
        when(service.find(anyString())).thenReturn(sub);
        when(request.getParameter(View.DIRECT_INFO_BUTTON)).thenReturn("test");
        when(session.getAttribute(View.BUNDLE)).thenReturn(bundle);
        when(session.getAttribute(View.SUBSCRIBER_SESSION)).thenReturn(sub2);
        when(request.getParameter(View.QUERY_LOGIN)).thenReturn("0");
        when(request.getParameter(View.FIRST_NAME_PAGE)).thenReturn("test");
        when(request.getParameter(View.SECOND_NAME_PAGE)).thenReturn("test");
        when(request.getParameter(View.LOGIN_PAGE)).thenReturn("test");
        when(request.getParameter(View.PASSWORD_PAGE)).thenReturn("test");

        String testString = command.execute(request,response);

        verify(request, atLeastOnce()).setAttribute(View.SAVED_FIRST_NAME, sub.getInfo().getFirstName());
        verify(request, atLeastOnce()).setAttribute(View.SAVED_SECOND_NAME, sub.getInfo().getSecondName());
        verify(request, atLeastOnce()).setAttribute(View.SAVED_PASSWORD, sub.getInfo().getPassword());
        verify(request, atLeastOnce()).setAttribute(View.LOGIN_IN_USE_PAGE, bundle.getString(View.LOGIN_IN_USE));
        verify(request, atLeastOnce()).setAttribute(View.USER_INFO_PAGE, bundle.getString(View.USER_INFO));
        verify(request, atLeastOnce()).setAttribute(View.FIRST_NAME_PAGE_H, bundle.getString(View.FIRST_NAME));
        verify(request, atLeastOnce()).setAttribute(View.SECOND_NAME_PAGE_H, bundle.getString(View.SECOND_NAME));
        verify(request, atLeastOnce()).setAttribute(View.LOGIN_PAGE_H, bundle.getString(View.LOGIN));
        verify(request, atLeastOnce()).setAttribute(View.PASSWORD_PAGE_H, bundle.getString(View.PASSWORD));
        verify(request, atLeastOnce()).setAttribute(View.PASSWORD_PAGE_H, bundle.getString(View.PASSWORD));
        verify(request, atLeastOnce()).setAttribute(View.CREATE_PAGE, bundle.getString(View.CREATE));
        verify(request, atLeastOnce()).setAttribute(View.SAVE_PAGE, bundle.getString(View.SAVE));
        verify(request, atLeastOnce()).setAttribute(View.RETURN_CABINET_PAGE, bundle.getString(View.RETURN_CABINET));


        assertEquals(testString, ViewURL.DIRECT_INFO_JSP);


    }

}