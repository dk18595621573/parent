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

    private static final long serialVersionUID = 5591572268463163013L;

    @JsonProperty("ActionStatus")
    private String actionStatus;

    @JsonProperty("ErrorInfo")
    private String errorInfo;

    @JsonProperty("ErrorCode")
    private Integer errorCode;

}

