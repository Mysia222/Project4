package main.command;

import servlets.CabinetServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Славик on 24.07.2016.
 */
public class FootTheBill implements Command {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "/view/FillBalance.jsp";
    }
}
