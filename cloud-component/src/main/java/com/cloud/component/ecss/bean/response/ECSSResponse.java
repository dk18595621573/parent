package com.cloud.component.ecss.bean.response;

import cn.hutool.core.date.DateUtil;
import com.cloud.component.ecss.consts.ECSSEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
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
@JacksonXmlRootElement(localName = "response")
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

    /**
     * 返回成功消息.
     *
     * @param functionId API方法
     * @return 成功消息
     */
    public static ECSSResponse success(final String functionId) {
        return ECSSResponse.error(ECSSEnum.RespCode.CODE_0.getCode(), functionId, ECSSEnum.RespCode.CODE_0.getDesc());
    }

    /**
     * 返回成功消息.
     *
     * @param functionId API方法
     * @param respCode   响应
     * @return 成功消息
     */
    public static ECSSResponse success(final String functionId, final ECSSEnum.RespCode respCode) {
        return ECSSResponse.error(respCode.getCode(), functionId, respCode.getDesc());
    }

    /**
     * 返回成功消息.
     *
     * @param functionId API方法
     * @param desc       响应描述
     * @return 成功消息
     */
    public static ECSSResponse success(final String functionId, final String desc) {
        return ECSSResponse.error(ECSSEnum.RespCode.CODE_0.getCode(), functionId, desc);
    }

    /**
     * 返回成功消息.
     *
     * @param code       响应码
     * @param functionId API方法
     * @param desc       响应描述
     * @return 成功消息
     */
    public static ECSSResponse success(final Integer code, final String functionId, final String desc) {
        ECSSResponse response = new ECSSResponse();
        response.setTime(DateUtil.now());
        response.setCode(code);
        response.setFunctionId(functionId);
        response.setDesc(desc);
        return response;
    }

    /**
     * 返回错误消息.
     *
     * @param functionId API方法
     * @param respCode   响应
     * @return 错误消息
     */
    public static ECSSResponse error(final String functionId, final ECSSEnum.RespCode respCode) {
        return ECSSResponse.error(respCode.getCode(), functionId, respCode.getDesc());
    }

    /**
     * 返回错误消息.
     *
     * @param functionId API方法
     * @param desc       响应描述
     * @return 错误消息
     */
    public static ECSSResponse error(final String functionId, final String desc) {
        return ECSSResponse.error(ECSSEnum.RespCode.CODE_99.getCode(), functionId, desc);
    }

    /**
     * 返回错误消息.
     *
     * @param code       响应码
     * @param functionId API方法
     * @param desc       响应描述
     * @return 错误消息
     */
    public static ECSSResponse error(final Integer code, final String functionId, final String desc) {
        ECSSResponse response = new ECSSResponse();
        response.setTime(DateUtil.now());
        response.setCode(code);
        response.setFunctionId(functionId);
        response.setDesc(desc);
        return response;
    }

}
