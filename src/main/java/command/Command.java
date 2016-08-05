package command;

import services.ServService;
import services.SubService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Славик on 17.07.2016.
 */
public interface Command {

    /**
     * it's subscriber's service
     */
    SubService subService = SubService.getInstance();


    /**
     * it's service's service
     */
    ServService servService = ServService.getInstance();

    /**
     * Command pattern implementation
     * @param request is request which will be processing
     * @param response is response after processing
     * @return String url
     * @throws ServletException
     * @throws IOException
     */
        String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
