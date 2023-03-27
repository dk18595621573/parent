package com.cloud.idempotent.aspect;

import com.cloud.common.utils.RedisKeyUtil;
import com.cloud.idempotent.annotation.AutoIdempotent;
import com.cloud.idempotent.exception.IdempotentException;
import com.cloud.idempotent.model.IdempotentResult;
import com.cloud.idempotent.service.IdempotentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 幂等性校验切面.
 *
 * @author zenghao
 * @date 2023/2/27
 */
@Slf4j
@Aspect
@AllArgsConstructor
public class IdempotentAspect {

    private static final String LOCK_KEY_PREFIX = "IDEMPOTENT_LOCK";

    private static final ExpressionParser EXP_PARSER = new SpelExpressionParser();

    private static final ParameterNameDiscoverer NAME_DISCOVERER = new DefaultParameterNameDiscoverer();

    private IdempotentService idempotentService;

    private RedissonClient redissonClient;

    /**
     * 环绕通知.
     *
     * @param joinPoint 切面方法
     * @param idempotent 注解
     * @return 方法返回
     * @throws Exception 处理异常
     */
    @Around(value = "@annotation(idempotent)")
    public Object around(final ProceedingJoinPoint joinPoint, final AutoIdempotent idempotent) {
        if (log.isDebugEnabled()) {
            log.debug("[幂等] - 拦截进入处理");
        }

        String bizId = buildBizId(joinPoint, idempotent);
        String lockKey = RedisKeyUtil.generate(LOCK_KEY_PREFIX, idempotent.module(), bizId);
        RLock lock = redissonClient.getLock(lockKey);

        try {
            if (!lock.tryLock(idempotent.expire(), TimeUnit.SECONDS)) {
                throw new IdempotentException(idempotent.message());
            }
            //获取之前处理的结果
            IdempotentResult idempotentResult = idempotentService.load(idempotent.module(), bizId, idempotent.resultType());
            if (idempotentResult.getSuccess()) {
                return idempotentResult.getData();
            }
            Object result = joinPoint.proceed();
            //保存结果
            idempotentService.save(idempotent.module(), bizId, result);
            return result;
        } catch (InterruptedException e) {
            log.error("[幂等] - module={}, bizId={}, 获取锁中断:", idempotent.module(), bizId, e);
            throw new IdempotentException("未知异常，请稍后再试");
        } catch (Throwable e) {
            log.error("[幂等] - module={}, bizId={}, 业务处理异常:", idempotent.module(), bizId, e);
            if (e instanceof RuntimeException) {
                throw (RuntimeException) e;
            }
            throw new IdempotentException("业务处理失败", e);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 异常处理.
     *
     * @param joinPoint 切面方法
     * @param idempotent 幂等性注解
     * @param ex        异常处理
     * @throws Throwable 异常
     */
    @AfterThrowing(value = "@annotation(idempotent)", throwing = "ex")
    public void afterThrowing(final JoinPoint joinPoint, final AutoIdempotent idempotent, final Throwable ex) {
        log.error("[幂等] - 拦截内部异常", ex);
    }

    /**
     * 正常返回处理.
     *
     * @param joinPoint 切面方法
     * @param idempotent 幂等性注解
     * @param result    正常返回值
     */
    @AfterReturning(value = "@annotation(idempotent)", returning = "result")
    public void afterReturning(final JoinPoint joinPoint, final AutoIdempotent idempotent, final Object result) {
        log.debug("[幂等] - 实际返回值：{}", result);
    }

    /**
     * 构建业务id.
     *
     * @param joinPoint 切面方法
     * @param idempotent 幂等性注解
     * @return 业务id
     */
    private String buildBizId(final ProceedingJoinPoint joinPoint, final AutoIdempotent idempotent) {
        List<String> keyList = getSpelKey(idempotent.keys(), getMethod(joinPoint), joinPoint.getArgs());
        return StringUtils.collectionToDelimitedString(keyList, "-");
    }

    /**
     * 获取切面方法对象.
     *
     * @param joinPoint 切面方法
     * @return 切面方法对象.
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

    /**
     * 切面方法生成key列表.
     *
     * @param defKeys    默认key组
     * @param method     切面方法
     * @param paraValues 切面方法参数值
     * @return 生成key列表
     */
    private List<String> getSpelKey(final String[] defKeys, final Method method, final Object[] paraValues) {
        List<String> keyList = new ArrayList<>();
        EvaluationContext eva;
        Object value;
        for (String key : defKeys) {
            if (StringUtils.hasLength(key)) {
                eva = new MethodBasedEvaluationContext(null, method, paraValues, NAME_DISCOVERER);
                value = EXP_PARSER.parseExpression(key).getValue(eva);
                if (Objects.isNull(value)) {
                    Assert.notNull(value, String.format("幂等业务Id=%s, 值不能为null", key));
                }
                keyList.add(value.toString());
            }
        }
        return keyList;
    }
}
