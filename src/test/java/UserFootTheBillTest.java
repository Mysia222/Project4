import command.UserEnter;
import command.UserFootTheBill;
import dao.jdbc.JdbcDaoFactory;
import ent.Subscriber;
import org.junit.Test;
import org.mockito.Mockito;
import services.SubService;
import views.View;
import views.ViewURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.DriverManager;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

/**
 * Created by Potaychuk Sviatoslav on 07.08.2016.
 */
public class UserFootTheBillTest extends Mockito{

    @Test
    public void executeFromHomeJsp() throws Exception {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        UserFootTheBill command = new UserFootTheBill();
        SubService service = mock(SubService.class);
        command.setSubService(service);
        ResourceBundle bundle = ResourceBundle.getBundle(View.BUNDLE_NAME,View.localeUA);
        Subscriber sub = new Subscriber();
        sub.setInfo(sub.new SubInfo());

        when(request.getSession()).thenReturn(session);
        when(request.getParameter(View.FOOT_THE_BILL_BUTTON)).thenReturn(bundle.getString(View.FOOT_THE_BILL));
        when(session.getAttribute(View.SUBSCRIBER_SESSION)).thenReturn(sub);
        when(session.getAttribute(View.BUNDLE)).thenReturn(bundle);
        String testString =command.execute(request,response);

        verify(request, atLeastOnce()).setAttribute(View.MONEY_VALUE_PAGE_L,bundle.getString(View.MONEY_VALUE_L));
        verify(request, atLeastOnce()).setAttribute(View.MONEY_PAY_PAGE,bundle.getString(View.MONEY_PAY ));
        verify(request, atLeastOnce()).setAttribute(View.RETURN_CABINET_PAGE, bundle.getString(View.RETURN_CABINET));

        assertEquals(testString, ViewURL.FILL_BALANCE_JSP);
    }


    @Test
    public void executeFromFillBalanceJsp() throws Exception {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        UserFootTheBill command = new UserFootTheBill();
        SubService service = mock(SubService.class);
        command.setSubService(service);
        ResourceBundle bundle = ResourceBundle.getBundle(View.BUNDLE_NAME,View.localeUA);
        Subscriber sub = new Subscriber();
        sub.setContract(1);
        sub.setInfo(sub.new SubInfo());

        when(request.getSession()).thenReturn(session);
        when(request.getParameter(View.FOOT_THE_BILL_BUTTON)).thenReturn("test");
        when(session.getAttribute(View.SUBSCRIBER_SESSION)).thenReturn(sub);
        when(session.getAttribute(View.BUNDLE)).thenReturn(bundle);
        when(request.getParameter(View.MONEY_VALUE_PAGE)).thenReturn("0");
        JdbcDaoFactory.setConnection(DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "root"));
        String testString =command.execute(request,response);

        verify(service, atLeastOnce()).setSub(any(Subscriber.class));
        verify(request, atLeastOnce()).getParameter(View.MONEY_VALUE_PAGE);

        assertEquals(testString, ViewURL.ERROR_PAGE);
    }
}