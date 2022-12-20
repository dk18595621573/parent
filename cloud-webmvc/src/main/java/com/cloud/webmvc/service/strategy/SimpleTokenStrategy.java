package com.cloud.webmvc.service.strategy;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.cloud.common.constant.Constants;
import com.cloud.common.core.model.RequestUser;
import com.cloud.common.utils.StringUtils;
import com.cloud.webmvc.properties.TokenProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class SimpleTokenStrategy implements TokenStrategy {

        private final TokenProperties tokenProperties;

        public SimpleTokenStrategy(final TokenProperties tokenProperties) {
            this.tokenProperties = tokenProperties;
        }

        @Override
        public RequestUser getLoginUser(final HttpServletRequest request) {
            // 获取请求携带的令牌
            String token = getToken(request, tokenProperties.getHeader());
            if (StringUtils.isNotEmpty(token)) {
                try {
                    Claims claims = parseToken(token, tokenProperties.getSecret());
                    // 解析用户信息
                    return claims.get(Constants.LOGIN_USER_KEY, RequestUser.class);
                } catch (Exception e) {
                }
            }
            return null;
        }

        @Override
        public String createToken(final RequestUser loginUser) {
            Map<String, Object> claims = new HashMap<>();
            claims.put(Constants.LOGIN_USER_KEY, loginUser);
            return Jwts.builder()
                .setExpiration(DateUtil.date().offset(DateField.MINUTE, tokenProperties.getExpireTime()))
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, tokenProperties.getSecret()).compact();
        }

    }