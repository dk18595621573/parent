package com.cloud.component.ecss.bean.response;

import com.cloud.component.ecss.consts.ECSSEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * ECSS 接口 应答报文.
 *
 * @author Luo
 * @date 2023-3-20 10:13:45
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ECSSResponse implements Serializable {

    private static final long serialVersionUID = -6811550517417623460L;

    /**
     * 响应码.
     */
    @JacksonXmlProperty(localName = "code")
    private Integer code;

    /**
     * 响应描述.
     */
    @JacksonXmlProperty(localName = "desc")
    private String desc;

    /**
     * 时间.
     */
    @JacksonXmlProperty(localName = "time")
    private String time;

    /**
     * API方法.
     */
    @JacksonXmlProperty(localName = "functionId")
    private String functionId;

    /**
     * 是否请求成功.
     *
     * @return 结果
     */
    public boolean success() {
        return ECSSEnum.RespCode.CODE_0.getCode().equals(getCode());
    }

}
