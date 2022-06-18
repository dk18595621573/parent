package com.cloud.webmvc.security.service;

import com.cloud.common.constant.Constants;
import com.cloud.common.utils.StringUtils;
import com.cloud.common.utils.ip.AddressUtils;
import com.cloud.common.utils.ip.IpUtils;
import com.cloud.framework.utils.ServletUtils;
import com.cloud.webmvc.domain.LoginUser;
import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.access.AuthorizationServiceException;

import javax.servlet.http.HttpServletRequest;

/**
 * token策略.
 *
 * @author zenghao
 * @date 2022/6/16
 */
public interface TokenStrategy {

    long MILLIS_SECOND = 1000L;

    long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    /**
     * 从请求数据中获取登录用户
     * @param request request
     * @return 登录用户
     */
    LoginUser getLoginUser(HttpServletRequest request);

    /**
     * 根据登录用户创建token
     * @param loginUser 登录用户
     * @return token
     */
    String createToken(LoginUser loginUser);

    /**
     * 移除登录用户
     * @param token 用户token
     */
    default void delLoginUser(String token) {
        //默认抛出异常，踢出登录用户
        throw new AuthorizationServiceException("系统踢出");
    }

    /**
     * 使用新的用户信息刷新token
     * @param loginUser 登录用户
     */
    default void refreshToken(LoginUser loginUser) {
        //默认抛出异常刷新用户
        throw new AuthorizationServiceException("系统刷新");
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    default Claims parseToken(String token, String secret) {
        return Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .getBody();
    }

    /**
     * 设置用户代理信息
     *
     * @param loginUser 登录信息
     */
    default void setUserAgent(LoginUser loginUser) {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        loginUser.setIpaddr(ip);
        loginUser.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
        loginUser.setBrowser(userAgent.getBrowser().getName());
        loginUser.setOs(userAgent.getOperatingSystem().getName());
    }

    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    default String getToken(HttpServletRequest request, String header) {
        String token = request.getHeader(header);
        if (StringUtils.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX)) {
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }


}
