package com.cloud.common.core.message;

import lombok.Data;

import java.io.Serializable;

/**
 * SSE 推送数据.
 *
 * @author zenghao
 * @date 2022/7/14
 */
@Data
public class SseData implements Serializable {

    /**
     * 指定客户端
     */
    private String client;

    /**
     * 指定客户端后缀
     */
    private String suffix;

}
