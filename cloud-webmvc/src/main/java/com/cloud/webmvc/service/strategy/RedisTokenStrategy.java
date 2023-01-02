package com.cloud.webmvc.service.strategy;

import com.cloud.common.core.model.RequestUser;
import com.cloud.common.utils.StringUtils;
import com.cloud.common.utils.uuid.CodeUtil;
import com.cloud.common.utils.uuid.IdUtils;
import com.cloud.core.redis.RedisCache;
import com.cloud.webmvc.properties.TokenProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class RedisTokenStrategy implements TokenStrategy {

    private static final String LOGIN_USER_CODE = "login_user_code";
    private final RedisCache redisCache;
    private final TokenProperties tokenProperties;

    public RedisTokenStrategy(final RedisCache redisCache, final TokenProperties tokenProperties) {
        this.redisCache = redisCache;
        this.tokenProperties = tokenProperties;
    }

    @Override
    public RequestUser getLoginUser(final HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = getToken(request, tokenProperties.getHeader());
        if (StringUtils.isNotEmpty(token)) {
            try {
                Claims claims = parseToken(token, tokenProperties.getSecret());
                // 解析对应的权限以及用户信息
                String uuid = claims.get(LOGIN_USER_KEY, String.class);
                Long userId = CodeUtil.toId(claims.get(LOGIN_USER_CODE, String.class));
                String userKey = getTokenKey(userId);

                RequestUser loginUser = redisCache.getCacheMapValue(userKey, uuid);
                long expireTime = loginUser.getExpireTime();
                long currentTime = System.currentTimeMillis();
                if (expireTime < currentTime) {
                    delLoginUser(loginUser.getUserId(), loginUser.getToken());
                    return null;
                }

                if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
                    refreshToken(loginUser);
                }
                return loginUser;
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    @Override
    public String createToken(final RequestUser loginUser) {
        String token;
        if (StringUtils.isBlank(loginUser.getToken())) {
            token = IdUtils.simpleUUID();
            loginUser.setToken(token);
            refreshCache(loginUser);
        } else {
            token = loginUser.getToken();
        }
        Map<String, Object> claims = new HashMap<>();
        claims.put(LOGIN_USER_KEY, token);
        claims.put(LOGIN_USER_CODE, CodeUtil.toCode(loginUser.getUserId()));
        return Jwts.builder()
            .setClaims(claims)
            .signWith(SignatureAlgorithm.HS512, tokenProperties.getSecret()).compact();
    }

    @Override
    public String refreshToken(final RequestUser loginUser) {
        refreshCache(loginUser);
        return createToken(loginUser);
    }

    @Override
    public void delLoginUser(final Long userId, final String token) {
        String userKey = getTokenKey(userId);
        if (StringUtils.isNotBlank(token)) {
            //删除用户指定登录记录
            redisCache.delCacheMapValue(userKey, token);
        } else {
            //删除用户所有登录记录
            redisCache.deleteObject(userKey);
        }
    }

    private String getTokenKey(Long userCode) {
        return tokenProperties.getCachePrefix() + userCode;
    }

    private void refreshCache(final RequestUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        int expireTime = tokenProperties.getExpireTime();
        loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLIS_MINUTE);
        // 根据uuid将loginUser缓存
        String userKey = getTokenKey(loginUser.getUserId());

        redisCache.setCacheMapValue(userKey, loginUser.getToken(), loginUser);
    }

}