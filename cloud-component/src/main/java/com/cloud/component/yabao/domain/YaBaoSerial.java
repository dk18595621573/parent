package com.cloud.component.yabao.domain;

import com.cloud.common.utils.StringUtils;
import lombok.Data;

import java.io.Serializable;

/**
 * 序列号查询结果
 */
@Data
public class YaBaoSerial implements Serializable {
    /**
     * 手机串码
     */
    private String sn;
    /**
     * 手机类别
     */
    private String model;
    /**
     * 是否激活
     */
    private Boolean activated;
    /**
     * 异常信息
     */
    private String message;

    public boolean ok() {
        return StringUtils.isBlank(message);
    }
}
