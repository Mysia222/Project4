package command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Славик on 17.07.2016.
 */
public class CreateUser implements Command{


    /**
     * This method check existing of Login and creates user if login isn't already in use
     * else user will be informed about it
     * @param request is request which will be processing
     * @param response is response after processing
     * @return string url
     * @throws ServletException
     * @throws IOException
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//         Subscriber sub = subService.subByLog(request.getParameter(View.QUERY_LOGIN));
//
//        if(sub==null) {
//            Subscriber subscriber = new Subscriber();
//            subscriber.setInfo(subscriber.new SubInfo());
//            subscriber.getInfo().setFirstName(request.getParameter(View.QUERY_F_NAME));
//            subscriber.getInfo().setSecondName(request.getParameter(View.QUERY_S_NAME));
//            subscriber.getInfo().setLogin(request.getParameter(View.QUERY_LOGIN));
//            subscriber.getInfo().setPassword(request.getParameter(View.QUERY_PASSWORD));
//            subService.create(subscriber);
//        }else {
//            request.setAttribute("loginInUse", "Login is already in use");
//            return "/view/CreateSub.jsp";
//        }
//
//
        return "/cabinet";
    }
}
