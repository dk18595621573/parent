package com.cloud.component.ecss.bean.response.common;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 基础业务响应.
 *
 * @author Luo
 * @date 2023-03-20 14:27
 */
@Data
@Accessors(chain = true)
public class BaseResult implements Serializable {

    private static final long serialVersionUID = 6180376521561914488L;

    /**
     * 处理结果，参考“响应码表”（单张订单处理结果）.
     */
    @JacksonXmlProperty(localName = "resultCode")
    private String resultCode;

    /**
     * 处理结果中文描述.
     */
    @JacksonXmlProperty(localName = "resultDesc")
    private String resultDesc;

}
