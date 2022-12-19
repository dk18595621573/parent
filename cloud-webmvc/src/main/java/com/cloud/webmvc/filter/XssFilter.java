package com.cloud.webmvc.filter;

import com.cloud.common.enums.HttpMethod;
import com.cloud.common.utils.StringUtils;
import com.cloud.webmvc.xss.XssHttpServletRequestWrapper;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 防止XSS攻击的过滤器
 *
 * @author author
 */
public class XssFilter implements Filter {

    /**
     * 排除链接
     */
    private final String[] excludes;

    public XssFilter(final String[] excludes) {
        this.excludes = excludes;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (handleExcludeURL(req, resp)) {
            chain.doFilter(request, response);
            return;
        }
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
        chain.doFilter(xssRequest, response);
    }

    private boolean handleExcludeURL(HttpServletRequest request, HttpServletResponse response) {
        String url = request.getServletPath();
        String method = request.getMethod();
        // GET DELETE 不过滤
        if (method == null || method.matches(HttpMethod.GET.name()) || method.matches(HttpMethod.DELETE.name())) {
            return true;
        }
        return StringUtils.matches(url, excludes);
    }

    @Override
    public void destroy() {

    }
}