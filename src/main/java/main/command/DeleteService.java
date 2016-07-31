package main.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Славик on 31.07.2016.
 */
public class DeleteService implements Command {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        servService.delete(Integer.valueOf(request.getParameter("id")));
        return "/secure/Controller2";
    }
}
