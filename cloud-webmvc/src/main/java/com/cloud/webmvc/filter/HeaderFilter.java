package com.cloud.webmvc.filter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * header拦截器把header数据放到MDC.
 *
 * @author breggor
 */
@Slf4j
public class HeaderFilter extends OncePerRequestFilter {

    public static final String TRACE_ID = "TRACE";

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain) throws ServletException, IOException {
        MDC.put(TRACE_ID, String.valueOf(System.currentTimeMillis()));
        try {
            chain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }
}
