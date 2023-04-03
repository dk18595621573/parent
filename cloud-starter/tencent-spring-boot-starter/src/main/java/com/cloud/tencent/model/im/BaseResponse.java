package com.cloud.tencent.model.im;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 腾讯云即时通讯IM回调通知 基础 响应参数.
 *
 * @author Luo
 * @date 2023-03-27 14:21
 */
@Data
@Accessors(chain = true)
public class BaseResponse implements Serializable {

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;

    private static final long serialVersionUID = 5591572268463163013L;

    @JsonProperty("ActionStatus")
    private String actionStatus;

    @JsonProperty("ErrorInfo")
    private String errorInfo;

    @JsonProperty("ErrorCode")
    private Integer errorCode;

    /**
     * 返回成功消息.
     *
     * @return 成功消息
     */
    public static BaseResponse success() {
        return BaseResponse.success(SUCCESS, "回调成功");
    }

    /**
     * 返回成功消息.
     *
     * @param errorCode 错误码
     * @param errorInfo 错误信息
     * @return 成功消息
     */
    public static BaseResponse success(final Integer errorCode, final String errorInfo) {
        BaseResponse response = new BaseResponse();
        response.setErrorCode(errorCode);
        response.setErrorInfo(errorInfo);
        response.setActionStatus("OK");
        return response;
    }

    /**
     * 返回失败消息.
     *
     * @return 失败消息
     */
    public static BaseResponse error() {
        return BaseResponse.success(ERROR, "回调出错");
    }

    /**
     * 返回失败消息.
     *
     * @param errorCode 错误码
     * @param errorInfo 错误信息
     * @return 失败消息
     */
    public static BaseResponse error(final Integer errorCode, final String errorInfo) {
        BaseResponse response = new BaseResponse();
        response.setErrorCode(errorCode);
        response.setErrorInfo(errorInfo);
        response.setActionStatus("FAIL");
        return response;
    }

}

