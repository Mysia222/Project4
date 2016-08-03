package command;

import views.View;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Славик on 03.08.2016.
 */
public class ServiceChangeService implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(View.SAVE_PAGE, View.SAVE);
        servService.editService(Integer.valueOf(request.getParameter(View.ID_PAGE)));
        Command command = CommandList.valueOf(View.SERVICES_LIST_EDIT).getCommand();
        return command.execute(request,response);
    }
}
