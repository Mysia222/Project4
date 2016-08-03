package command;

import ent.Subscriber;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Potaychuk Sviatoslav on 27.07.2016.
 */
public class UnlockUser implements Command {

    /**
     * This method unlock user who has balance>0 and is blocked still
     * @param request is request which will be processing
     * @param response is response after processing
     * @return String url
     * @throws ServletException
     * @throws IOException
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Subscriber sub = subService.subByContract(Integer.parseInt(request.getParameter("id")));
        sub.setBlocked(false);
        subService.unlockId(sub.getContract());
        return "/T1Servlet?command=SHOW_USERS";
    }
}
