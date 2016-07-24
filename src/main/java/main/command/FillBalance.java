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
        Double value = CabinetServlet.getSub().getBalance()+Double.valueOf(d);

//        request.setAttribute("login", CabinetServlet.getSub().getInfo().getLogin());
//        request.setAttribute("password", CabinetServlet.getSub().getInfo().getPassword());
        if (value!=null){
            CabinetServlet.getSub().setBalance(value);
        }
        try {
            telService.changeBalance(CabinetServlet.getSub());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return "/cabinet";
    }

}
