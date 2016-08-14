package command;

import dao.DAOException;
import ent.Subscriber;
import org.apache.log4j.Logger;
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

    /**
     * Logger
     */
    private static Logger log =  Logger.getLogger(UserFootTheBill.class);

    /**
     * Subscriber's service
     */
    private SubService subService = SubService.getInstance();

    /**
     * If request from Home.jsp, method sets attributes and returns FillBalance.jsp's url
     * Else session's subscriber will be updated with balnce
     * @param request is request which will be processing
     * @param response is response after processing
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.trace(View.COMMAND_EXECUTE + this.getClass().getName());
        ResourceBundle bundle = (ResourceBundle)request.getSession().getAttribute(View.BUNDLE);
        Subscriber sub = (Subscriber)request.getSession().getAttribute(View.SUBSCRIBER_SESSION);
        //from Home jsp
        if(request.getParameter(View.FOOT_THE_BILL_BUTTON).equals(bundle.getString(View.FOOT_THE_BILL))){
            request.setAttribute(View.MONEY_VALUE_PAGE_L,bundle.getString(View.MONEY_VALUE_L));
            request.setAttribute(View.MONEY_PAY_PAGE,bundle.getString(View.MONEY_PAY ));
            request.setAttribute(View.RETURN_CABINET_PAGE, bundle.getString(View.RETURN_CABINET));
            return ViewURL.FILL_BALANCE_JSP;
            //from FillBalance.jsp
        }else {
            sub.setBalance(sub.getBalance()+Double.valueOf(request.getParameter(View.MONEY_VALUE_PAGE)));
            request.getSession().setAttribute(View.SUBSCRIBER_SESSION,sub);
            try {
                subService.setSub(sub);
            } catch (DAOException e) {
                request.setAttribute(View.ERROR_CAUSE, bundle.getString(View.CANT_DO_REQUEST));
                log.error(View.LOG_BY_USER + request.getSession().getAttribute(View.SUBSCRIBER_SESSION));
                return ViewURL.ERROR_PAGE;
            }
            Command command = CommandList.valueOf(View.USER_CABINET).getCommand();
            return command.execute(request, response);
        }

    }

    //getters & setters
    public SubService getSubService() {
        return subService;
    }

    public void setSubService(SubService subService) {
        this.subService = subService;
    }
}
