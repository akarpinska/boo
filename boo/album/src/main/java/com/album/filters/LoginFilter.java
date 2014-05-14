package com.album.filters;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by akarpinska on 5/14/14.
 */
@Component(value="/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            StringBuffer url = request.getRequestURL();
            String urlStr = url.toString();
            if (!urlStr.endsWith("/login.htm") && !urlStr.endsWith("/registration.htm")) {
                HttpSession session = request.getSession();
                if (session == null || session.getAttribute("user") == null) {
                    HttpServletResponse response = (HttpServletResponse) servletResponse;
                    response.sendRedirect("/album/login.htm");
                    return;
                }
            }

        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig paramFilterConfig) throws ServletException {
        // do nothing
    }

    @Override
    public void destroy() {
        // do nothing
    }

}