package com.cloud.security.service;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.cloud.common.config.SystemConfig;
import com.cloud.common.constant.Constants;
import com.cloud.common.utils.StringUtils;
import com.cloud.common.utils.ip.AddressUtils;
import com.cloud.common.utils.ip.IpUtils;
import com.cloud.common.utils.uuid.IdUtils;
import com.cloud.framework.redis.RedisCache;
import com.cloud.framework.utils.ServletUtils;
import com.cloud.security.model.LoginUser;
import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.access.AuthorizationServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    class SimpleTokenStrategy implements TokenStrategy {

        private final SystemConfig.TokenProperties tokenProperties;

        public SimpleTokenStrategy(final SystemConfig.TokenProperties tokenProperties) {
            this.tokenProperties = tokenProperties;
        }

        @Override
        public LoginUser getLoginUser(final HttpServletRequest request) {
            // 获取请求携带的令牌
            String token = getToken(request, tokenProperties.getHeader());
            if (StringUtils.isNotEmpty(token)) {
                try {
                    Claims claims = parseToken(token, tokenProperties.getSecret());
                    // 解析用户信息
                    return claims.get(Constants.LOGIN_USER_KEY, LoginUser.class);
                } catch (Exception e) {
                }
            }
            return null;
        }

        @Override
        public String createToken(final LoginUser loginUser) {
            Map<String, Object> claims = new HashMap<>();
            claims.put(Constants.LOGIN_USER_KEY, loginUser);
            return Jwts.builder()
                .setExpiration(DateUtil.date().offset(DateField.MINUTE, tokenProperties.getExpireTime()))
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, tokenProperties.getSecret()).compact();
        }

    }

    class RedisTokenStrategy implements TokenStrategy {

        private final RedisCache redisCache;

        private final SystemConfig.TokenProperties tokenProperties;

        public RedisTokenStrategy(final RedisCache redisCache, final SystemConfig.TokenProperties tokenProperties) {
            this.redisCache = redisCache;
            this.tokenProperties = tokenProperties;
        }

        @Override
        public LoginUser getLoginUser(final HttpServletRequest request) {
            // 获取请求携带的令牌
            String token = getToken(request, tokenProperties.getHeader());
            if (StringUtils.isNotEmpty(token)) {
                try {
                    Claims claims = parseToken(token, tokenProperties.getSecret());
                    // 解析对应的权限以及用户信息
                    String uuid = (String) claims.get(Constants.LOGIN_USER_KEY);
                    String userKey = getTokenKey(uuid);
                    return redisCache.getCacheObject(userKey);
                } catch (Exception e) {
                }
            }
            return null;
        }

        @Override
        public String createToken(final LoginUser loginUser) {
            String token = IdUtils.fastUUID();
            loginUser.setToken(token);
            // setUserAgent(loginUser);
            refreshToken(loginUser);
            Map<String, Object> claims = new HashMap<>();
            claims.put(Constants.LOGIN_USER_KEY, token);
            return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, tokenProperties.getSecret()).compact();
        }

        @Override
        public void refreshToken(final LoginUser loginUser) {
            loginUser.setLoginTime(System.currentTimeMillis());
            int expireTime = tokenProperties.getExpireTime();
            loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLIS_MINUTE);
            // 根据uuid将loginUser缓存
            String userKey = getTokenKey(loginUser.getToken());
            redisCache.setCacheObject(userKey, loginUser, expireTime, TimeUnit.MINUTES);
        }

        @Override
        public void delLoginUser(final String token) {
            if (StringUtils.isNotEmpty(token)) {
                String userKey = getTokenKey(token);
                redisCache.deleteObject(userKey);
            }
        }

        private String getTokenKey(String uuid) {
            return Constants.LOGIN_TOKEN_KEY + uuid;
        }

    }
}
