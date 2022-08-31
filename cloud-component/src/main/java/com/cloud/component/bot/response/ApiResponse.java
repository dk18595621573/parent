package com.cloud.component.bot.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 句子接口响应数据.
 *
 * @author zenghao
 * @date 2022/8/31
 */
@Data
public class ApiResponse implements Serializable {

    public static final int SUCCESS_CODE = 0;

    private Integer errcode;

    private String errmsg;
}
