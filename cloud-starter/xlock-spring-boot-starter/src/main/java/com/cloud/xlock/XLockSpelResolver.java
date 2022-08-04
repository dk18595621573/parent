package com.cloud.xlock;

import com.cloud.xlock.annotation.XKey;
import com.cloud.xlock.annotation.XLock;
import com.cloud.xlock.exception.KeyBuilderException;
import com.cloud.xlock.model.KeyInfo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * spring el解析key策略.
 *
 * @author breggor
 */
@Slf4j
public class XLockSpelResolver {

    private static final ThreadLocal<Map<String, KeyInfo>> KEY_INFO_CACHE = new InheritableThreadLocal<>(
    ) {
        @Override
        protected Map<String, KeyInfo> initialValue() {
            return new HashMap<>();
        }
    };

    private static final ParameterNameDiscoverer PARAMETER_NAME_DISCOVERER = new DefaultParameterNameDiscoverer();

    private static final ExpressionParser EXPRESSION_PARSER = new SpelExpressionParser();

    /**
     * 获取KeyInfo.
     *
     * @param joinPoint joinPoint
     * @param xLock     xLock
     * @return KeyInfo
     */
    public KeyInfo getKeyInfo(final JoinPoint joinPoint, final XLock xLock) {
        final String key = joinPoint.toString();
        if (!KEY_INFO_CACHE.get().containsKey(key)) {
            KeyInfo keyInfo = genKeyInfo(joinPoint, xLock);
            log.info("[分布式锁] - 创建keyInfo:{}", keyInfo);
            KEY_INFO_CACHE.get().put(key, keyInfo);
        }
        return KEY_INFO_CACHE.get().get(key);
    }

    /**
     * 清除keyinfo.
     *
     * @param keyInfo keyInfo
     */
    public void remove(final KeyInfo keyInfo) {
        KEY_INFO_CACHE.get().remove(keyInfo.getCacheKey());
    }

    /**
     * key构建对象.
     *
     * @param joinPoint joinPoint
     * @param xLock     锁注解
     * @return key构建对象
     * @throws KeyBuilderException key构建异常
     */
    private KeyInfo genKeyInfo(final JoinPoint joinPoint, final XLock xLock) throws KeyBuilderException {
        KeyInfo.KeyInfoBuilder builder = KeyInfo.builder();
        final Object[] args = joinPoint.getArgs();
        Method method = getMethod(joinPoint);
        List<String> xlockKeys = getXLockKey(xLock.keys(), method, args);
        List<String> xkeyParams = getXkeyParameter(method.getParameters(), args);
        if (CollectionUtils.isEmpty(xlockKeys) && CollectionUtils.isEmpty(xkeyParams)) {
            throw new KeyBuilderException("@XLock @XKey 都没有设置key");
        }
        xlockKeys.addAll(xkeyParams);
        builder.keys(xlockKeys);
        builder.cacheKey(joinPoint.toString());
        builder.lockId("ThreadNo:" + Thread.currentThread().getId() + "---" + joinPoint.getTarget().getClass().getName() + "@" + method.getName());
        builder.leaseTime(xLock.leaseTime()).waitTime(xLock.waitTime()).timeUnit(xLock.timeUnit());
        return builder.build();
    }

    /**
     * 获取锁key信息.
     *
     * @param keys   自定义key
     * @param method 方法
     * @param params 参数
     * @return 锁key信息
     */
    private List<String> getXLockKey(final String[] keys, final Method method, final Object[] params) {
        List<String> result = new ArrayList<>();
        EvaluationContext context = new MethodBasedEvaluationContext(null, method, params, PARAMETER_NAME_DISCOVERER);
        for (String key : keys) {
            if (StringUtils.hasLength(key)) {
                String val = EXPRESSION_PARSER.parseExpression(key).getValue(context).toString();
                result.add(val);
            }
        }
        return result;
    }

    /**
     * 获取锁参数.
     *
     * @param parameters 参数字段
     * @param params     参数值
     * @return 参数key列表
     */
    private List<String> getXkeyParameter(final Parameter[] parameters, final Object[] params) {
        List<String> paramKeys = new ArrayList<>();
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].getAnnotation(XKey.class) != null) {
                XKey anno = parameters[i].getAnnotation(XKey.class);
                if (anno.value().isEmpty()) {
                    paramKeys.add(params[i].toString());
                } else {
                    StandardEvaluationContext context = new StandardEvaluationContext(params[i]);
                    String key = EXPRESSION_PARSER.parseExpression(anno.value()).getValue(context).toString();
                    paramKeys.add(key);
                }
            }
        }
        return paramKeys;
    }

    /**
     * 获取切面方法.
     *
     * @param joinPoint 切面方法
     * @return 方法对象
     */
    private Method getMethod(final JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (method.getDeclaringClass().isInterface()) {
            try {
                method = joinPoint.getTarget().getClass().getDeclaredMethod(signature.getName(), method.getParameterTypes());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        return method;
    }

}
