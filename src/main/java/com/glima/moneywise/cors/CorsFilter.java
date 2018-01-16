package com.glima.moneywise.cors;

import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Guilherme on 17/01/2018.
 *
 * Class created for CORS global configutation.
 * to date, my springboot version has an issue with CORS and OAuth2, the issue is related to the access_token
 * through the url localhost:8080/oauth/token, Origin isnt recognized even using Cors Bean configuration
 * A Filter is created in order to be able to allow access token requests
 *
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE) // high priority
public class CorsFilter implements Filter {

    private String originAllowed = "http://localhost:8000"; // TODO: Config globally for production

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.setHeader("Access-Control-Allow-Origin", originAllowed);
        response.setHeader("Access-Control-Allow-Credentials", "true");

        if("OPTIONS".equals(request.getMethod()) && originAllowed.equals(request.getHeader("Origin"))){
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-type, Accept");
            response.setHeader("Access-Control-Max-Age", "3600"); //1hr cache

            response.setStatus(HttpServletResponse.SC_OK);
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
