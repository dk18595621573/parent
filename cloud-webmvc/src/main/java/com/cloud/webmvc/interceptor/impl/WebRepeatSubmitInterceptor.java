package com.cloud.webmvc.interceptor.impl;

import cn.hutool.core.util.ObjectUtil;
import com.cloud.common.constant.Constants;
import com.cloud.common.utils.RedisKeyUtil;
import com.cloud.core.redis.RedisCache;
import com.cloud.webmvc.annotation.RepeatSubmit;
import com.cloud.webmvc.filter.RepeatedlyRequestWrapper;
import com.cloud.webmvc.interceptor.RepeatSubmitInterceptor;
import com.cloud.webmvc.utils.SecurityUtils;
import com.cloud.webmvc.utils.http.HttpHelper;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * web防止重复提交拦截器
 * @author:zhanll
 * @date:2023-01-09 10:21
 */
@Slf4j
public class WebRepeatSubmitInterceptor extends RepeatSubmitInterceptor {

    @Resource
    private RedisCache redisCache;

    @Override
    public boolean isRepeatSubmit(HttpServletRequest request, RepeatSubmit annotation) {
        long expireSeconds = annotation.interval();
        String token = "";
        if(ObjectUtil.equal(annotation.hasToken(), Boolean.TRUE)){
            token = SecurityUtils.getLoginUser().getToken();
        }
        // 获取请求参数
        String nowParams = "";
        if (request instanceof RepeatedlyRequestWrapper) {
            RepeatedlyRequestWrapper repeatedlyRequest = (RepeatedlyRequestWrapper) request;
            nowParams = HttpHelper.getBodyString(repeatedlyRequest);
        }
        // 生成redisKey = repeat_submit: + 请求地址 + (token) + 请求参数
        StringBuilder key = new StringBuilder();
        if(ObjectUtil.isNotEmpty(token)){
            key.append(token).append(":");
        }
        String redisKey = RedisKeyUtil.generate(Constants.REPEAT_SUBMIT_KEY, request.getRequestURI(),
                key.append(nowParams).toString());
        // 生成对应value
        String redisValue = redisKey;
        log.info("redisKey:{}", redisKey);
        return !redisCache.setIfAbsent(redisKey, redisValue, expireSeconds, TimeUnit.MILLISECONDS);
    }

}
