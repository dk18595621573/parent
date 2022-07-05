package com.cloud.webmvc.security.service.strategy;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.cloud.core.config.SystemConfig;
import com.cloud.common.constant.Constants;
import com.cloud.common.utils.StringUtils;
import com.cloud.webmvc.domain.LoginUser;
import com.cloud.webmvc.security.service.TokenStrategy;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class SimpleTokenStrategy implements TokenStrategy {

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