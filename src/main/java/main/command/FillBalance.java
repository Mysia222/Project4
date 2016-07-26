package main.command;

import main.ent.Subscriber;
import servlets.CabinetServlet;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Славик on 24.07.2016.
 */
public class FillBalance implements Command {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String d = request.getParameter("addBalance");
        Subscriber sub = (Subscriber) request.getSession().getAttribute("sub");

        Double value = sub.getBalance()+Double.valueOf(d);

//        request.setAttribute("login", CabinetServlet.getSub().getInfo().getLogin());
//        request.setAttribute("password", CabinetServlet.getSub().getInfo().getPassword());
        if (value!=null){
            sub.setBalance(value);
            request.getSession().setAttribute("sub",sub);
        }
        try {
            telService.setSub(sub);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return "/cabinet";
    }

}
