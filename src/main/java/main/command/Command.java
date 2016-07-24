package main.command;

import model.TelService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Славик on 17.07.2016.
 */
public interface Command {
        TelService telService = TelService.getInstance();
        String execute(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException;

}
