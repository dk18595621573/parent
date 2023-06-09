package com.cloud.webmvc.filter;

import com.cloud.common.constant.Constants;
import com.cloud.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
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
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HeaderFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain) throws ServletException, IOException {
        try {
            MDC.put(Constants.MDC_USER_ID, StringUtils.defaultString(request.getHeader(Constants.MDC_USER_ID), Constants.DASH));
            MDC.put(Constants.MDC_COMPANY_ID, StringUtils.defaultString(request.getHeader(Constants.MDC_COMPANY_ID), Constants.DASH));

            String traceId = request.getHeader(Constants.MDC_TRACE_ID);
            MDC.put(Constants.MDC_TRACE_ID, StringUtils.defaultString(traceId, String.valueOf(System.currentTimeMillis())));
            chain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }
}
