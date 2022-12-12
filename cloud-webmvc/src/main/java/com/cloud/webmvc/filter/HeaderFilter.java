package com.cloud.webmvc.filter;

import com.cloud.common.constant.Constants;
import com.cloud.common.utils.StringUtils;
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

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain) throws ServletException, IOException {
        try {
            String traceId = request.getHeader(Constants.MDC_TRACE_ID);
            MDC.put(Constants.MDC_TRACE_ID, StringUtils.defaultString(traceId, String.valueOf(System.currentTimeMillis())));
            chain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }
}
