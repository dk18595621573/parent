package com.cloud.webmvc.service;

import com.cloud.common.constant.Constants;
import com.cloud.common.core.model.BaseRequestInfo;
import com.cloud.common.core.model.RequestUser;
import com.cloud.common.utils.StringUtils;
import com.cloud.common.utils.json.JsonUtil;
import com.cloud.common.utils.uuid.IdUtils;
import com.cloud.webmvc.properties.TokenProperties;
import com.cloud.webmvc.exception.AuthorizationException;
import com.cloud.webmvc.utils.ServletUtils;
import com.cloud.webmvc.utils.ip.AddressUtils;
import com.cloud.webmvc.utils.ip.IpUtils;
import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * token验证处理
 *
 * @author author
 */
@Slf4j
@Component
public class TokenService {

    @Autowired
    private TokenProperties tokenProperties;

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
        // 获取请求携带的令牌
        String token = getToken(request, tokenProperties.getHeader());
        if (StringUtils.isNotEmpty(token)) {
            try {
                Claims claims = parseToken(token, tokenProperties.getSecret());
                // 解析用户信息
                String jsonUser = claims.get(Constants.LOGIN_USER_KEY, String.class);
                return JsonUtil.parse(jsonUser, RequestUser.class);
            } catch (Exception e) {
                log.warn("解析token登录用户失败:", e);
            }
        }
        return null;
    }

    public String createToken(RequestUser loginUser) {
        long now = System.currentTimeMillis();
        if (StringUtils.isBlank(loginUser.getToken())) {
            loginUser.setToken(IdUtils.simpleUUID());
            loginUser.setLoginTime(now);
        }
        return Jwts.builder()
            .claim(Constants.LOGIN_USER_KEY, JsonUtil.toJson(loginUser))
            .claim(Constants.VERSION_KEY, Constants.TOKEN_CURRENT_VERSION)
            .setExpiration(new Date(now + (1000L * 60 * tokenProperties.getExpireTime())))
            .compressWith(CompressionCodecs.GZIP)
            .signWith(SignatureAlgorithm.HS512, tokenProperties.getSecret()).compact();
    }

    /**
     * 刷新token数据
     *
     * @param loginUser 登录信息
     */
    public String refreshToken(RequestUser loginUser) {
        return createToken(loginUser);
    }

    /**
     * 删除用户身份信息
     */
    public void delLoginUser(Long userId, String token) {
        throw new AuthorizationException("系统踢出");
    }

    /**
     * 清除过期无效的token
     */
    public void clearInvalidToken() {

    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims parseToken(String token, String secret) {
        return Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .getBody();
    }

    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    private String getToken(HttpServletRequest request, String header) {
        String token = request.getHeader(header);
        if (StringUtils.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX)) {
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }
}
