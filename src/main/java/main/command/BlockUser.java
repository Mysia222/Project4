package main.command;

import main.ent.Subscriber;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Potaychuk Sviatoslav on 27.07.2016.
 */
public class BlockUser implements Command {


    /**
     * This method blocks Sub by id from request
     * @param request
     * @param response
     * @return URL to show users list after blocking
     * @throws ServletException
     * @throws IOException
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Subscriber sub = subService.subByContract(Integer.parseInt(request.getParameter("id")));
        sub.setBlocked(true);
        subService.blockId(sub.getContract());
        return "/T1Servlet?command=SHOW_USERS";
    }
}
