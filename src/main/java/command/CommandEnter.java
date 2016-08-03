package command;

import ent.Subscriber;
import resources.View;
import resources.ViewURL;
import model.SubService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by potaychuk on 02.08.2016.
 */
public class CommandEnter implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String login = request.getParameter(View.PAGE_LOGIN);
        String password = request.getParameter(View.PAGE_PASSWORD);

        if(SubService.getInstance().existByLogPas(login,password)){
            Subscriber subscriber = new Subscriber();
            if (request.getSession().getAttribute("sub")==null) {
               String log = request.getParameter("login");
               subscriber = SubService.getInstance().subByLog(log);
               if (subscriber.isAdmin()){
                    return ViewURL.HOME_JSP;
               }
            }else {
                subscriber = (Subscriber) request.getSession().getAttribute("sub");
                if (subscriber.isAdmin()){
                    return  ViewURL.ADMIN_HOME_JSP;
                }
                subscriber= SubService.getInstance().subByContract(subscriber.getContract());
            }
            request.setAttribute(View.LOGIN_L, View.LOGIN_B);
            request.setAttribute(View.PASSWORD_L, View.PASSWORD_B);
            request.setAttribute(View.QUERY_BALANCE, subscriber.getBalance());
            request.setAttribute(View.QUERY_CONTRACT, subscriber.getContract());
            request.setAttribute(View.QUERY_F_NAME, subscriber.getInfo().getFirstName());
            request.setAttribute(View.QUERY_S_NAME, subscriber.getInfo().getSecondName());
            request.setAttribute(View.QUERY_PASSWORD, subscriber.getInfo().getPassword());
            request.setAttribute(View.QUERY_LOGIN, subscriber.getInfo().getLogin());
            request.setAttribute("current_service", subscriber.getCurrentService());
            if (subscriber.isBlocked()) {
                request.setAttribute(View.QUERY_BLOCKED, "STATUS BLOCKED!");
            }else {
                request.setAttribute(View.QUERY_BLOCKED, "STATUS: active!");
            }
            HttpSession session = request.getSession();
            session.setAttribute("sub", subscriber);
            return  ViewURL.HOME_JSP;
        }
        else {
            request.setAttribute("wrongLogin", View.WRONG_ENTER);
            return  ViewURL.INDEX_JSP;
        }
    }
}
