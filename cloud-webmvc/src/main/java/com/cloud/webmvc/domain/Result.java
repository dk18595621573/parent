package com.cloud.webmvc.domain;

import com.cloud.common.constant.HttpStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * controller接口返回对象.
 *
 * @author zenghao
 * @date 2022/5/17
 */
@Data
@ApiModel("接口统一返回对象")
@NoArgsConstructor
public class Result<T> implements Serializable {

    private static final String SUCCESS_MSG = "操作成功";

    @ApiModelProperty("状态码 200:成功")
    private int code;

    @ApiModelProperty("提示信息")
    private String msg;

    @ApiModelProperty("数据")
    private T data;

    /**
     * 初始化一个新创建的 Result 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     */
    private Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 初始化一个新创建的 Result 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    private Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public boolean ok() {
        return HttpStatus.SUCCESS == this.getCode();
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static <T> Result<T> result(boolean result) {
        return result ? Result.success() : Result.error();
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static <T> Result<T> success() {
        return Result.success(SUCCESS_MSG);
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static <T> Result<T> data(T data) {
        return Result.success(SUCCESS_MSG, data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static <T> Result<T> success(String msg) {
        return new Result<>(HttpStatus.SUCCESS, msg);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(HttpStatus.SUCCESS, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return 错误消息
     */
    public static <T> Result<T> error() {
        return Result.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 错误消息
     */
    public static <T> Result<T> error(String msg) {
        return Result.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 错误消息
     */
    public static <T> Result<T> error(String msg, T data) {
        return new Result<>(HttpStatus.ERROR, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 错误消息
     */
    public static <T> Result<T> error(int code, String msg) {
        return new Result<>(code, msg);
    }

}
