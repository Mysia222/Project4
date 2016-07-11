package servlets;

import moreless.javaGame.Model;
import moreless.javaGame.View;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Potaychuk Sviatoslav on 11.07.2016.
 */
@WebServlet("/s")
public class Servlet extends HttpServlet {
    private Model model;

    @Override
    public void init() throws ServletException {
        super.init();
        model=new Model();
        model.rand(0,100);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setAttribute(View.POSITION, View.INPUT_INT_DATA_BETWEEN+" "+model.getStart()+" "+model.getEnd());
        String number = request.getParameter("number");
        if (number!=null){
            try {
                int a = Integer.valueOf(number);
                if (a < model.getStart() || a > model.getEnd()){
                    request.setAttribute(View.POSITION,View.KEEP_BORDER +"\n"+ View.INPUT_INT_DATA_BETWEEN+" "+model.getStart()+" "+model.getEnd());
                }else {
                    if (model.tryGuess(a)){
                        request.setAttribute(View.POSITION, View.CONGRATULATION);
                        request.setAttribute("log", View.LOG+model.log());
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/log.jsp");
                        dispatcher.forward(request,response);
                        this.init();
                    }else {
                        request.setAttribute(View.POSITION, View.INPUT_INT_DATA_BETWEEN + " " + model.getStart() + " " + model.getEnd());
                    }
                }
            } catch (NumberFormatException e){
                request.setAttribute(View.POSITION, View.WRONG_INPUT_INT_DATA +"\n"+View.INPUT_INT_DATA_BETWEEN + " " + model.getStart() + " " + model.getEnd());
            }


        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request,response);
    }
}
