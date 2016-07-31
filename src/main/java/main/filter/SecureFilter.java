package main.filter;

import main.ent.Subscriber;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Славик on 30.07.2016.
 */
@WebFilter( "/secure/*")
public class SecureFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        Subscriber sub =(Subscriber)((HttpServletRequest) req).getSession().getAttribute("sub");
        if (!sub.isAdmin()){
            req.getRequestDispatcher("/view/errorPage.html").forward(req, resp);
        }else {
            System.out.println("ASD");
            chain.doFilter(req,resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
