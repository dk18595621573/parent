package com.cloud.webmvc.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NamedInheritableThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求耗时记录拦截.
 *
 * @author breggor
 */
@Slf4j
@Component
public class ElapsedTimeFilter extends OncePerRequestFilter {

    /**
     * 开始时间点记录.
     */
    private static final ThreadLocal<Long> ELAPSED_TIME_THREAD_LOCAL = new NamedInheritableThreadLocal<>("ELAPSED_TIME_THREAD_LOCAL");

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain) throws ServletException, IOException {
        ELAPSED_TIME_THREAD_LOCAL.set(System.currentTimeMillis());
        try {
            chain.doFilter(request, response);
        } finally {
            this.after(request);
        }
    }
    
    /**
     * 后置处理.
     *
     * @param request request
     */
    private void after(final HttpServletRequest request) {
        long consumeTime = System.currentTimeMillis() - ELAPSED_TIME_THREAD_LOCAL.get();
        log.info("path={}, elapsed time:{} (ms)", request.getRequestURI(), consumeTime);
        ELAPSED_TIME_THREAD_LOCAL.remove();
    }
}
