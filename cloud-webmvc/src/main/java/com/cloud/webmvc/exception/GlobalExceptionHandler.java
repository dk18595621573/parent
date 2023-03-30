package com.cloud.webmvc.exception;

import com.cloud.common.constant.HttpStatus;
import com.cloud.common.exception.CallbackException;
import com.cloud.common.exception.DemoModeException;
import com.cloud.common.exception.PayException;
import com.cloud.common.exception.ServiceException;
import com.cloud.common.utils.StringUtils;
import com.cloud.core.exception.BaseException;
import com.cloud.webmvc.domain.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 全局异常处理器
 *
 * @author author
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 权限校验异常
     */
    @ExceptionHandler(AuthenticationException.class)
    public Result<?> handleAuthorizeException(AuthenticationException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',权限校验失败'{}'", requestURI, e.getMessage());
        return Result.error(HttpStatus.FORBIDDEN, "没有权限，请联系管理员授权");
    }

    /**
     * 授权校验异常
     */
    @ExceptionHandler(AuthorizationException.class)
    public Result<?> handleAuthorizationException(AuthorizationException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',用户认证失败'{}'", requestURI, e.getMessage());
        return Result.error(HttpStatus.UNAUTHORIZED, e.getMessage());
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<?> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                          HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',不支持'{}'请求", requestURI, e.getMethod());
        return Result.error(HttpStatus.NOT_FOUND, "不支持该请求，请刷新页面后重试");
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(ServiceException.class)
    public Result<?> handleServiceException(ServiceException e) {
        log.error("业务异常[{}]:{}", e.getCode(), e.getMessage());
        Integer code = e.getCode();
        return StringUtils.isNotNull(code) ? Result.error(code, e.getMessage()) : Result.error(e.getMessage());
    }

    /**
     * 模块业务异常
     */
    @ExceptionHandler(BaseException.class)
    public Result<?> handleBaseException(BaseException e) {
        String message = e.getMessage();
        log.error("[{}]模块出现异常【{}】【{}】:", e.getModule(), message, e.getArgs(), e);
        return Result.error(message);
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public Result<?> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生未知异常.", requestURI, e);
        if ("org.apache.dubbo.rpc.RpcException".equals(e.getClass().getName())) {
            return Result.error("服务异常，请稍后再试");
        }
        return Result.error("操作异常，请联系管理员");
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常.", requestURI, e);
        return Result.error("系统异常，请联系管理员");
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public Result<?> handleBindException(BindException e) {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return Result.error(message);
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return Result.error(message);
    }

    /**
     * IO异常
     */
    @ExceptionHandler(IOException.class)
    public void handleIOException(IOException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.warn("请求地址'{}',发生IO异常:{}", requestURI, e.getMessage());
    }

    /**
     * 请求参数异常
     */
    @ExceptionHandler({MissingServletRequestPartException.class, IllegalStateException.class, IllegalArgumentException.class})
    public Result<?> handleIllegalStateException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.error("请求参数异常");
    }

    /**
     * 文件大小限制异常
     */
    @ExceptionHandler(MultipartException.class)
    public Result<?> multipartException(MultipartException e) {
        log.error(e.getMessage(), e);
        if ("io.undertow.server.RequestTooBigException".equals(e.getCause().getCause().getClass().getName())) {
            return Result.error("文件大小超出限制");
        }
        return Result.error("文件异常");
    }

    /**
     * 演示模式异常
     */
    @ExceptionHandler(DemoModeException.class)
    public Result<?> handleDemoModeException(DemoModeException e) {
        return Result.error("演示模式，不允许操作");
    }

    /**
     * 支付异常
     */
    @ResponseStatus
    @ExceptionHandler(PayException.class)
    public Result<?> handlePayException(PayException e) {
        return Result.error();
    }

    /**
     * 回调异常
     */
    @ResponseStatus
    @ExceptionHandler(CallbackException.class)
    public Result<?> handlePayException(CallbackException e) {
        return Result.error();
    }

}
