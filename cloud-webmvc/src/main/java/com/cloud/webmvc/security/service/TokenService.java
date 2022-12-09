package com.cloud.webmvc.security.service;

import com.cloud.common.core.model.BaseRequestInfo;
import com.cloud.common.utils.StringUtils;
import com.cloud.webmvc.domain.LoginUser;
import com.cloud.webmvc.utils.ServletUtils;
import com.cloud.webmvc.utils.ip.AddressUtils;
import com.cloud.webmvc.utils.ip.IpUtils;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * token验证处理
 *
 * @author author
 */
@Slf4j
@Component
public class TokenService {

    private static final long MILLIS_MINUTE_TEN = 20 * TokenStrategy.MILLIS_MINUTE;
    @Autowired
    private TokenStrategy tokenStrategy;

    public BaseRequestInfo buildRequestInfo() {
        try {
            BaseRequestInfo requestInfo = new BaseRequestInfo();
            UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader(HttpHeaders.USER_AGENT));
            String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
            requestInfo.setIpaddr(ip);
            requestInfo.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
            requestInfo.setBrowser(userAgent.getBrowser().getName());
            requestInfo.setOs(userAgent.getOperatingSystem().getName());
            return requestInfo;
        } catch (Exception e) {
            log.warn("获取客户端信息失败:", e);
            return null;
        }
    }
    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        return tokenStrategy.getLoginUser(request);
    }

    public String createToken(LoginUser loginUser) {
        return tokenStrategy.createToken(loginUser);
    }

    /**
     * 设置用户身份信息
     */
    public void setLoginUser(LoginUser loginUser) {
        if (StringUtils.isNotNull(loginUser) && StringUtils.isNotEmpty(loginUser.getToken())) {
            refreshToken(loginUser);
        }
    }

    /**
     * 删除用户身份信息
     */
    public void delLoginUser(String token) {
        tokenStrategy.delLoginUser(token);
    }


    /**
     * 验证令牌有效期，相差不足20分钟，自动刷新缓存
     *
     * @param loginUser
     * @return 令牌
     */
    public void verifyToken(LoginUser loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
            refreshToken(loginUser);
        }
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginUser 登录信息
     */
    public void refreshToken(LoginUser loginUser) {
        tokenStrategy.refreshToken(loginUser);
    }

}
