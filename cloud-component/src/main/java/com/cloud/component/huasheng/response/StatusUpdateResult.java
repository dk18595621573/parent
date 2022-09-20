package com.cloud.component.huasheng.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 异步下单处理结果.
 *
 * @author wyk
 * @date 2022/8/21
 */
@Data
public class StatusUpdateResult implements Serializable {

    private Resp resp;

    @Data
    public class Resp {

        private String code;

        private String msg;

    }
}
