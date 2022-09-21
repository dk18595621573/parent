package com.cloud.component.bot.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统基础回调.
 *
 * @author zenghao
 * @date 2022/9/21
 */
@Data
public class BaseCallback<T> implements Serializable {

    @JsonProperty("data")
    private T data;
}
