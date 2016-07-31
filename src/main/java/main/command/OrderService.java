package main.command;

import main.ent.Service;
import main.ent.Subscriber;
import main.resources.View;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Славик on 24.07.2016.
 */
public class OrderService implements Command {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Subscriber sub = (Subscriber) request.getSession().getAttribute("sub");
        if (subService.subByContract(sub.getContract()).isBlocked()){
            request.setAttribute(View.QUERY_BLOCKED, View.STATUS_BLOCKED);
            return "/cabinet";
        }
        List <Service> services;
        try {
            services = servService.getServiceList();
            request.setAttribute("services",services);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        return "/view/ServiceList.jsp";
    }
}
