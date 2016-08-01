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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Славик on 24.07.2016.
 */
public class OrderService implements Command {

    /**
     * This method sets parameters on ServiceList.jsp and redirects user there
     * @param request is request which will be processing
     * @param response is response after processing
     * @return string url
     * @throws ServletException
     * @throws IOException
     */
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
            Set idSet = new HashSet();
            for(Service s: sub.getCurrentService()){
                idSet.add(s.getId());
            }
            request.setAttribute("idSet",idSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        return "/view/ServiceList.jsp";
    }
}
