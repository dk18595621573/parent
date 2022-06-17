package com.cloud.security.service.strategy;

import com.cloud.common.config.SystemConfig;
import com.cloud.common.constant.Constants;
import com.cloud.common.utils.StringUtils;
import com.cloud.common.utils.uuid.IdUtils;
import com.cloud.framework.redis.RedisCache;
import com.cloud.security.model.LoginUser;
import com.cloud.security.service.TokenStrategy;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RedisTokenStrategy implements TokenStrategy {

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