package com.cloud.webmvc.filter;

import com.cloud.common.constant.Constants;
import com.cloud.common.threads.RequestThread;
import com.cloud.common.utils.StringUtils;
import com.cloud.webmvc.domain.LoginUser;
import com.cloud.webmvc.security.service.TokenService;
import com.cloud.webmvc.utils.SecurityUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token过滤器 验证token有效性
 *
 * @author author
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        try {
            LoginUser loginUser = tokenService.getLoginUser(request);
            if (StringUtils.isNotNull(loginUser) && StringUtils.isNull(SecurityUtils.getAuthentication())) {
                tokenService.verifyToken(loginUser);
                MDC.put(Constants.MDC_USER_ID, String.valueOf(loginUser.getUserId()));
                MDC.put(Constants.MDC_COMPANY_ID, String.valueOf(loginUser.getDeptId()));
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityUtils.setAuthentication(authenticationToken);
                RequestThread.setUser(loginUser.toRequestUser());
            } else {
                MDC.put(Constants.MDC_USER_ID, StringUtils.EMPTY);
                MDC.put(Constants.MDC_COMPANY_ID, StringUtils.EMPTY);
            }
            chain.doFilter(request, response);
        } finally {
            SecurityUtils.clearAuthentication();
            RequestThread.clear();
            MDC.remove(Constants.MDC_USER_ID);
            MDC.remove(Constants.MDC_COMPANY_ID);
        }
    }
}
