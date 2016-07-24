package main.command;

import model.TelService;
import servlets.CabinetServlet;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Славик on 24.07.2016.
 */
public class OrderService implements Command {
    ConcurrentMap rm;
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            rm = telService.getServiceList();
            request.setAttribute("services",rm.values());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        return "/view/ServiceList.jsp";
    }
}
