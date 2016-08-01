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

    /**
     * This method redirect admin to secure/Controller and shows an user lsit
     * @param request is request which will be processing
     * @param response is response after processing
     * @return string url
     * @throws ServletException
     * @throws IOException
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "/secure/Controller";
    }
}
