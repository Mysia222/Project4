package main.command;

import main.ent.Subscriber;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Potaychuk Sviatoslav on 27.07.2016.
 */
public class ShowSubs implements Command {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Subscriber> list = telService.getSubsList();
            request.setAttribute("users", list);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return "/view/UserList.jsp";
    }
}
