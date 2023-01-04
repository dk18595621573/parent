package com.cloud.webmvc.service;

import com.cloud.common.core.model.BaseRequestInfo;
import com.cloud.common.core.model.RequestUser;
import com.cloud.webmvc.service.strategy.TokenStrategy;
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
    @Autowired
    private TokenStrategy tokenStrategy;

    public BaseRequestInfo buildRequestInfo() {
        try {
            HttpServletRequest request = ServletUtils.getRequest();
            UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader(HttpHeaders.USER_AGENT));
            String ip = IpUtils.getIpAddr(request);

            BaseRequestInfo requestInfo = new BaseRequestInfo();
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
    public RequestUser getLoginUser(HttpServletRequest request) {
        return tokenStrategy.getLoginUser(request);
    }

    public String createToken(RequestUser loginUser) {
        return tokenStrategy.createToken(loginUser);
    }

    /**
     * 刷新token数据
     *
     * @param loginUser 登录信息
     */
    public String refreshToken(RequestUser loginUser) {
        return tokenStrategy.refreshToken(loginUser);
    }

    /**
     * 删除用户身份信息
     */
    public void delLoginUser(Long userId, String token) {
        tokenStrategy.delLoginUser(userId, token);
    }

    /**
     * 清除过期无效的token
     */
    public void clearInvalidToken() {
        tokenStrategy.clearInvalidToken();
    }
}
