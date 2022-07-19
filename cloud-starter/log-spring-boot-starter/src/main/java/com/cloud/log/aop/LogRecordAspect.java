package com.cloud.log.aop;


import cn.hutool.core.util.StrUtil;
import com.cloud.log.annotation.LogRecord;
import com.cloud.log.context.LogRecordContext;
import com.cloud.log.model.LogStroage;
import com.cloud.log.service.IOperatorGetService;
import com.cloud.log.service.ISysLogService;
import com.cloud.log.spel.LogRecordEvaluationContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.lang.reflect.Method;

/**
 * AOP操作日志.
 *
 * @author xht
 */
@Slf4j
@Aspect
public class LogRecordAspect {

    private static final String HEADER_CLIENT_ID = "GW-TENANT-ID";

    private static final String HEADER_USER = "GW-USER-ID";

    @Autowired
    private ISysLogService iSysLogService;

    @Autowired
    private IOperatorGetService iOperatorGetService;

    private static final ParameterNameDiscoverer PARAMETER_NAME_DISCOVERER = new DefaultParameterNameDiscoverer();

    private static final ExpressionParser EXPRESSION_PARSER = new SpelExpressionParser();

    /**
     * 环绕通知.
     *
     * @param point 切面方法
     * @return 方法返回
     */
    @Around("@annotation(com.nengliang.log.annotation.LogRecord)")
    @SneakyThrows
    public Object around(final ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        LogRecord logRecord = signature.getMethod().getAnnotation(LogRecord.class);
        if (log.isDebugEnabled()) {
            log.debug("执行审计日志：[模块]:{},[类名]:{},[方法]:{},[日志]:{}", logRecord.category(), point.getTarget().getClass().getName(), point.getSignature().getName());
        }

        Object ret = null;
        String errorMsg = null;
        try {
            ret = point.proceed();
        } catch (Exception e) {
            throw e;
        } finally {
            iSysLogService.save(buildISysLogService(point, logRecord, ret, errorMsg));
            LogRecordContext.clear();
        }
        return ret;
    }

    private LogStroage buildISysLogService(final ProceedingJoinPoint point, final LogRecord logRecord, final Object ret, final String errorMsg) {
        LogRecordEvaluationContext context = new LogRecordEvaluationContext(null, getMethod(point), point.getArgs(), PARAMETER_NAME_DISCOVERER, ret, errorMsg);
        return LogStroage.builder().bizNo(EXPRESSION_PARSER.parseExpression(logRecord.bizNo()).getValue(context).toString())
            .success(EXPRESSION_PARSER.parseExpression(logRecord.success()).getValue(context).toString()).category(logRecord.category())
            .operator(StrUtil.isBlank(logRecord.operator()) ? iOperatorGetService.getUser().getUserId() : EXPRESSION_PARSER.parseExpression(logRecord.operator()).getValue(context).toString()).build();
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


    public static void main(String[] args) {
        SpelExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("#root+'111'");

        System.out.println(expression.getValue("xxx"));
    }

}
