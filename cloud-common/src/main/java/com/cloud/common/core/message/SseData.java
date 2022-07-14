package com.cloud.common.core.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private String client;

    /**
     * 指定客户端后缀
     */
    @JsonIgnore
    private String suffix;

}
