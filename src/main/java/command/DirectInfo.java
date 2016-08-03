package command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Славик on 23.07.2016.
 */
public class DirectInfo implements Command{

    /**
     * This method redirect user on /DirectInfo.jsp
     * @param request is request which will be processing
     * @param response is response after processing
     * @return String url
     * @throws ServletException
     * @throws IOException
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "/view/DirectInfo.jsp";
    }
}
