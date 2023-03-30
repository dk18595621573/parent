package com.cloud.component.ecss.bean.response.order;

import com.cloud.component.ecss.bean.response.ECSSResponse;
import com.cloud.component.ecss.bean.response.common.BaseResult;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 订单同步接口响应参数.
 *
 * @author Luo
 * @date 2023-03-20 14:25
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "response")
public class OrderCreateResponse extends ECSSResponse {

    private static final long serialVersionUID = -889303419942088773L;

    /**
     * 请求订单.
     */
    @JacksonXmlProperty(localName = "results")
    @JacksonXmlElementWrapper(localName = "results")
    private List<Result> results;

    /**
     * 处理结果.
     */
    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Result extends BaseResult {
        private static final long serialVersionUID = -5925571455738929136L;

        /**
         * 请求订单编号.
         */
        @JacksonXmlProperty(localName = "orderId")
        private String orderId;

    }

}
