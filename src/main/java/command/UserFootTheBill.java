package command;

import dao.DAOException;
import ent.Subscriber;
import services.SubService;
import views.View;
import views.ViewURL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Created by potaychuk on 03.08.2016.
 */
public class UserFootTheBill implements Command {

    private SubService subService = SubService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResourceBundle bundle = (ResourceBundle)request.getSession().getAttribute(View.BUNDLE);
        Subscriber sub = (Subscriber)request.getSession().getAttribute(View.SUBSCRIBER_SESSION);
        if(request.getParameter(View.FOOT_THE_BILL_BUTTON).equals(bundle.getString(View.FOOT_THE_BILL))){   //from Home jsp
            request.setAttribute(View.MONEY_VALUE_PAGE_L,bundle.getString(View.MONEY_VALUE_L));
            request.setAttribute(View.MONEY_PAY_PAGE,bundle.getString(View.MONEY_PAY ));
            request.setAttribute(View.RETURN_CABINET_PAGE, bundle.getString(View.RETURN_CABINET));
            return ViewURL.FILL_BALANCE_JSP;
        }else {
            String d = request.getParameter(View.MONEY_VALUE_PAGE);
            Double value = sub.getBalance()+Double.valueOf(d);
            sub.setBalance(value);
            request.getSession().setAttribute(View.SUBSCRIBER_SESSION,sub);
            try {
                subService.setSub(sub);
            } catch (DAOException e) {
                request.setAttribute(View.ERROR_CAUSE, bundle.getString(View.CANT_DO_REQUEST));
                return ViewURL.ERROR_PAGE;
            }
            Command command = CommandList.valueOf(View.USER_CABINET).getCommand();
            return command.execute(request, response);
        }

    }

    public SubService getSubService() {
        return subService;
    }

    public void setSubService(SubService subService) {
        this.subService = subService;
    }
}
