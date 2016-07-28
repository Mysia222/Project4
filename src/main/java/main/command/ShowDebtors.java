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
public class ShowDebtors implements Command {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Subscriber> list = telService.getDebtorsList();
        request.setAttribute("users", list);
        return "/view/UserList.jsp";
    }
}
