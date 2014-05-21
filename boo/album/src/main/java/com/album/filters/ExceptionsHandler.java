package com.album.filters;

import com.album.exceptions.ResourceNotFoundException;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by akarpinska on 5/20/14.
 */
public class ExceptionsHandler implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (IOException e) {
            throw e;
        } catch (ServletException e) {
            if (e instanceof ResourceNotFoundException) {
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
            else
                throw e;
        }
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
