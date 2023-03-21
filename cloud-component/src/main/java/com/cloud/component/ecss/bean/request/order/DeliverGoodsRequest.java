package com.cloud.component.ecss.bean.request.order;

import com.cloud.component.ecss.bean.request.BaseECSSRequest;
import com.cloud.component.ecss.bean.response.order.DeliverGoodsResponse;
import com.cloud.component.ecss.consts.ECSSEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 发货 接口请求参数.
 *
 * @author Luo
 * @date 2023-03-17 13:31
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@JacksonXmlRootElement(localName = "request")
public class DeliverGoodsRequest extends BaseECSSRequest<DeliverGoodsResponse> implements Serializable {

    private static final long serialVersionUID = 8843183695867375069L;

    /**
     * 订单列表信息.
     */
    @JacksonXmlProperty(localName = "orderList")
    @JacksonXmlElementWrapper(localName = "orderList")
    private List<Order> orderList;

    /**
     * 获取请求方法.
     *
     * @return 请求方法
     */
    @Override
    @JsonIgnore
    public String getMethod() {
        return ECSSEnum.Method.DELIVER_GOODS.getCode();
    }

    /**
     * 单张订单信息.
     */
    @Data
    @JacksonXmlRootElement(localName = "order")
    public static class Order implements Serializable {

        private static final long serialVersionUID = 8422326011966662876L;

        /**
         * 订单内容.
         */
        @JacksonXmlProperty(localName = "outOrderNo")
        private String outOrderNo;

        /**
         * Ecss订单号.
         */
        @JacksonXmlProperty(localName = "orderCoce")
        private String orderCoce;

        /**
         * 配送方式1：送货上门   2：自提.
         */
        @JacksonXmlProperty(localName = "supplyType")
        private Integer supplyType;

        /**
         * 物流公司（自提回传23 客户自提）.
         */
        @JacksonXmlProperty(localName = "express")
        private String express;

        /**
         * 快递单号（自提不用）.
         */
        @JacksonXmlProperty(localName = "expressOrderNo")
        private String expressOrderNo;

        /**
         * 发货时间.
         */
        @JacksonXmlProperty(localName = "supplyTime")
        private String supplyTime;

        /**
         * 发票号码.
         */
        @JacksonXmlProperty(localName = "invoiceCode")
        private String invoiceCode;

        /**
         * 需要回传的 imei对象列表.
         */
        @JacksonXmlProperty(localName = "imeiList")
        @JacksonXmlElementWrapper(localName = "imeiList")
        private List<Imei> imeiList;

    }

    /**
     * Imei对象.
     */
    @Data
    @JacksonXmlRootElement(localName = "imei")
    public static class Imei implements Serializable {

        private static final long serialVersionUID = -5672694497350611519L;

        /**
         * Ecss系统中 商品的条形码(如回传imei，必填).
         */
        @JacksonXmlProperty(localName = "barCode")
        private String barCode;

        /**
         * 手机imei，多个imei用英文逗号隔开.
         */
        @JacksonXmlProperty(localName = "imeiCode")
        private String imeiCode;

        /**
         * 扩展字段.
         * 格式： 名称：值；名称：值；例如
         * a:1;b:2;
         * ksbm:客商编码;
         * invoiceurl:发票链接
         */
        @JacksonXmlProperty(localName = "expansion")
        private String expansion;

    }


}
