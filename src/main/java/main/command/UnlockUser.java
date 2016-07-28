package main.command;

import main.ent.Subscriber;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Potaychuk Sviatoslav on 27.07.2016.
 */
public class UnlockUser implements Command {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Subscriber sub = telService.subByContract(Integer.parseInt(request.getParameter("id")));
        sub.setBlocked(false);
        telService.unlockId(sub.getContract());
//        response.sendRedirect("/view/UserLis.jsp");
//        request.removeAttribute("id");
        return "/T1Servlet?command=SHOW_USERS";
    }
}
