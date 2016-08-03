package command;

import ent.Subscriber;
import views.View;
import views.ViewURL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by potaychuk on 03.08.2016.
 */
public class UserFootTheBill implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType ("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Subscriber sub = (Subscriber)request.getSession().getAttribute(View.SUBSCRIBER_SESSION);
        if(request.getParameter(View.FOOT_THE_BILL_BUTTON).equals(View.FOOT_THE_BILL)){   //from Home jsp
            request.setAttribute(View.MONEY_VALUE_PAGE_L,View.MONEY_VALUE_L);
            request.setAttribute(View.MONEY_PAY_PAGE,View.MONEY_PAY );
            request.setAttribute(View.RETURN_CABINET_PAGE, View.RETURN_CABINET);
            return ViewURL.FILL_BALANCE_JSP;
        }else {
            String d = request.getParameter(View.MONEY_VALUE_PAGE);
            Double value = sub.getBalance()+Double.valueOf(d);
            sub.setBalance(value);
            request.getSession().setAttribute(View.SUBSCRIBER_SESSION,sub);
            subService.setSub(sub);
            Command command = CommandList.valueOf(View.USER_CABINET).getCommand();
            return command.execute(request, response);
        }

    }
}
