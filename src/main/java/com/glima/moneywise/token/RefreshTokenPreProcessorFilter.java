package com.glima.moneywise.token;

import org.apache.catalina.util.ParameterMap;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Guilherme on 09/01/2018.
 *
 * This class is used as spring filter to intersect
 * every request and filter the oauth/token refresh token.
 * the method doFilter gets the refrshToken from Cookie and map it
 * to request parameters.
 *
 * Without request token, client will not be able to do any request after
 * token expired
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE) // high priority
public class RefreshTokenPreProcessorFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if("/oauth/token".equalsIgnoreCase(request.getRequestURI())
                && "refresh_token".equals(request.getParameter("grant_type"))
                && request.getCookies() != null){
            for(Cookie cookie : request.getCookies()){
                if(cookie.getName().equals("refreshToken")){
                    String refreshToken = cookie.getValue();
                    request = new MyServletRequestWrapper(request, refreshToken);
                }
            }
        }

        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    static class MyServletRequestWrapper extends HttpServletRequestWrapper{

        private String refreshToken;

        public MyServletRequestWrapper(HttpServletRequest request,String refreshToken) {
            super(request);
            this.refreshToken = refreshToken;
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            ParameterMap<String, String[]> map = new ParameterMap<>(getRequest().getParameterMap());
            map.put("refresh_token", new String[]{ refreshToken });
            map.setLocked(true);
            return map;
        }
    }
}
