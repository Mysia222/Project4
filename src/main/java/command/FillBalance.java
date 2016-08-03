package command;

import ent.Subscriber;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Славик on 24.07.2016.
 */
public class FillBalance implements Command {

    /**
     * This method changes user balance according to request
     * @param request is request which will be processing
     * @param response is response after processing
     * @return string url
     * @throws ServletException
     * @throws IOException
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String d = request.getParameter("addBalance");
        Subscriber sub = (Subscriber) request.getSession().getAttribute("sub");
        Double value = sub.getBalance()+Double.valueOf(d);
        sub.setBalance(value);
        request.getSession().setAttribute("sub",sub);
        subService.setSub(sub);
        return "/cabinet";
    }

}
