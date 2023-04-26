package com.cloud.component.honor.bean.request.order;

import cn.hutool.core.annotation.Alias;
import com.cloud.component.honor.bean.request.BaseHonorRequest;
import com.cloud.component.honor.bean.response.order.OrderCreateResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 荣耀 订单创建 请求参数.
 *
 * @author Luo
 * @date 2023-04-25 15:16
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class OrderCreateRequest extends BaseHonorRequest<OrderCreateResponse> implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 客户（买家）编码.
     */
    @Alias("cust_id")
    @JsonProperty("cust_id")
    private String custId;

    /**
     * 店铺编码(供应商).
     */
    @Alias("shop_no")
    @JsonProperty("shop_no")
    private String shopNo;

    /**
     * 客户自定义合同号.
     */
    @Alias("cust_contract_no")
    @JsonProperty("cust_contract_no")
    private String custContractNo;

    /**
     * 买家备注.
     */
    @Alias("comments")
    @JsonProperty("comments")
    private String comments;

    /**
     * 自动提交支付方式:2：线下付款 3：线上支付(纯资金池支付),4：先货后款,5：线上支付(工行子账户支付)
     * 说明：不指定支付方式时，则需要在e栈界面操作提交支付.
     */
    @Alias("pay_mode")
    @JsonProperty("pay_mode")
    private String payMode;

    /**
     * 自动提交支付时激励使用方式:1：自动按最大比例使用激励抵扣,2：不使用激励。.
     */
    @Alias("special_offer_mode")
    @JsonProperty("special_offer_mode")
    private String specialOfferMode;

    /**
     * 发票类型 1：普票 2：专票 空：不开发票
     * 说明：买家在所选店铺下，必须有对应类型的发票信息.
     */
    @Alias("invoice_type")
    @JsonProperty("invoice_type")
    private String invoiceType;

    /**
     * 物流方式。1送货，2自提.
     */
    @Alias("logistics_type")
    @JsonProperty("logistics_type")
    private String logisticsType;

    /**
     * 来源类型.
     */
    @Alias("sourceType")
    @JsonProperty("sourceType")
    private String sourceType;

    /**
     * 商品信息.
     */
    @Alias("order_lines")
    @JsonProperty("order_lines")
    private List<OrderLines> orderLines;

    /**
     * 收货地址信息.
     */
    @Alias("order_delivery_Info")
    @JsonProperty("order_delivery_Info")
    private OrderDeliveryInfo orderDeliveryInfo;

    /**
     * 收货地址信息.
     */
    @Data
    @Accessors(chain = true)
    public static class OrderDeliveryInfo implements Serializable {

        private static final long serialVersionUID = 7558815266035655285L;

        /**
         * 地址简称.
         */
        @Alias("address_name")
        @JsonProperty("address_name")
        private String addressName;

        /**
         * 收货人姓名.
         */
        @Alias("name")
        @JsonProperty("name")
        private String name;

        /**
         * 收货人联系电话.
         */
        @Alias("phone")
        @JsonProperty("phone")
        private String phone;

        /**
         * 收货国家.
         */
        @Alias("country")
        @JsonProperty("country")
        private String country;

        /**
         * 收货省份.
         */
        @Alias("province")
        @JsonProperty("province")
        private String province;

        /**
         * 收货城市.
         */
        @Alias("city")
        @JsonProperty("city")
        private String city;

        /**
         * 县区.
         */
        @Alias("region")
        @JsonProperty("region")
        private String region;

        /**
         * 乡镇/街道.
         */
        @Alias("street")
        @JsonProperty("street")
        private String street;

        /**
         * 详细地址.
         */
        @Alias("address")
        @JsonProperty("address")
        private String address;

    }

    /**
     * 商品信息.
     */
    @Data
    @Accessors(chain = true)
    public static class OrderLines implements Serializable {

        private static final long serialVersionUID = 8898050419915150199L;

        /**
         * 荣耀商品编码、EAN编码。按照先后顺序查询.
         */
        @Alias("prod_code_sale")
        @JsonProperty("prod_code_sale")
        private String prodCodeSale;

        /**
         * 商品数量.
         */
        @Alias("quantity")
        @JsonProperty("quantity")
        private Integer quantity;

        /**
         * 实际采购单价(=单价-单台满减-圈层优惠）.
         */
        @Alias("prince")
        @JsonProperty("prince")
        private BigDecimal prince;

    }

    /**
     * 获取API请求地址.
     *
     * @return API请求地址
     */
    @Override
    public String getApiUrl() {
        return "/order/create";
    }

}
