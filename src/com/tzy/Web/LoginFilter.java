package com.tzy.Web;

import com.tzy.Entity.Customer;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter", urlPatterns = {"*.jsp", "/controller"})
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String action = request.getParameter("action");

        Customer customer = (Customer) request.getSession().getAttribute("customer");

        if(customer == null && !"login".equals(action) && !"reg_init".equals(action)){
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
