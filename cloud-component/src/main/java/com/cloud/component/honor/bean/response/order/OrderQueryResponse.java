package com.cloud.component.honor.bean.response.order;

import cn.hutool.core.annotation.Alias;
import com.cloud.component.honor.bean.response.HonorResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 荣耀 订单查询 响应参数.
 *
 * @author Luo
 * @date 2023-04-25 16:27
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderQueryResponse extends HonorResponse implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 返回结果数量.
     */
    @Alias("totalNumber")
    @JsonProperty("totalNumber")
    private Integer totalNumber;

    /**
     * 数据.
     */
    @Alias("data")
    @JsonProperty("data")
    private DataDTO data;

    /**
     * 数据模型.
     */
    @Data
    @Accessors(chain = true)
    public static class DataDTO implements Serializable {

        private static final long serialVersionUID = 1181377029868582852L;

        /**
         * 订单数据集合.
         */
        @Alias("order_list")
        @JsonProperty("order_list")
        private List<Order> orderList;

    }

    /**
     * 订单数据.
     */
    @Data
    @Accessors(chain = true)
    public static class Order implements Serializable {

        private static final long serialVersionUID = 3761340447083012308L;

        /**
         * 店铺编码.
         */
        @Alias("shop_no")
        @JsonProperty("shop_no")
        private String shopNo;

        /**
         * 客户（买家）编码.
         */
        @Alias("cust_id")
        @JsonProperty("cust_id")
        private String custId;

        /**
         * 客户（买家）名称.
         */
        @Alias("cust_name")
        @JsonProperty("cust_name")
        private String custName;

        /**
         * 订单号.
         */
        @Alias("honor_contract_no")
        @JsonProperty("honor_contract_no")
        private String honorContractNo;

        /**
         * 客户自定义合同号.
         */
        @Alias("cust_contract_no")
        @JsonProperty("cust_contract_no")
        private String custContractNo;

        /**
         * 是否涉及退款，Y涉及，N不涉及.
         */
        @Alias("refund_flag")
        @JsonProperty("refund_flag")
        private String refundFlag;

        /**
         * 退款成功的申请单ID，仅退款成功后返回该字段.
         */
        @Alias("refund_id")
        @JsonProperty("refund_id")
        private List<String> refundId;

        /**
         * 产品总金额.
         */
        @Alias("commodity_amount")
        @JsonProperty("commodity_amount")
        private BigDecimal commodityAmount;

        /**
         * 退款产品金额，仅退款成功后返回该字段.
         */
        @Alias("commodity_amount_refund")
        @JsonProperty("commodity_amount_refund")
        private BigDecimal commodityAmountRefund;

        /**
         * 满减总金额(行满减金额求和).
         */
        @Alias("reduction_mount")
        @JsonProperty("reduction_mount")
        private BigDecimal reductionMount;

        /**
         * 运费.
         */
        @Alias("freight_fee")
        @JsonProperty("freight_fee")
        private BigDecimal freightFee;

        /**
         * 退款运费金额，仅退款成功后返回该字段.
         */
        @Alias("freight_fee_refund")
        @JsonProperty("freight_fee_refund")
        private BigDecimal freightFeeRefund;

        /**
         * 手续费.
         */
        @Alias("commission")
        @JsonProperty("commission")
        private BigDecimal commission;

        /**
         * 订单总金额.
         */
        @Alias("order_amount")
        @JsonProperty("order_amount")
        private BigDecimal orderAmount;

        /**
         * 退款总金额.
         */
        @Alias("refund_amount")
        @JsonProperty("refund_amount")
        private BigDecimal refundAmount;

        /**
         * 退款应付金额(扣除退款激励后的金额).
         */
        @Alias("refund_payment")
        @JsonProperty("refund_payment")
        private BigDecimal refundPayment;

        /**
         * 退款后订单总金额，仅退款成功后返回该字段.
         */
        @Alias("order_amount_refund")
        @JsonProperty("order_amount_refund")
        private BigDecimal orderAmountRefund;

        /**
         * 订单支付方式:1：线上支付,2：线下支付,3：线上支付(纯资金池支付),4：先货后款.
         */
        @Alias("pay_mode")
        @JsonProperty("pay_mode")
        private String payMode;

        /**
         * 订单支付时间.
         */
        @Alias("pay_time")
        @JsonProperty("pay_time")
        private Date payTime;

        /**
         * 支付状态：1待支付,2已支付,3部分支付/支付中,4重新支付
         * 订单退款状态：11退款中,12退款成功,13退款失败.
         */
        @Alias("pay_status")
        @JsonProperty("pay_status")
        private String payStatus;

        /**
         * 支付结果说明.
         */
        @Alias("pay_results")
        @JsonProperty("pay_results")
        private String payResults;

        /**
         * 订单创建时间.
         */
        @Alias("create_time")
        @JsonProperty("create_time")
        private Date createTime;

        /**
         * 买家备注.
         */
        @Alias("comments")
        @JsonProperty("comments")
        private String comments;

        /**
         * 卖家备注.
         */
        @Alias("seller_comments")
        @JsonProperty("seller_comments")
        private String sellerComments;

        /**
         * 订单状态.
         * Submitted(订单已提交/待确认),Accepted(已确认/待发货),
         * Signed(订单已变更),SignFinish(变更完成),SellerConfirmed(商家已确认),
         * Shipping(发货中/待收货),Completed(已完成),Cancelled(已取消),
         * Rejected(已拒绝),Closed(已关闭).
         */
        @Alias("status")
        @JsonProperty("status")
        private String status;

        /**
         * 物流方式。1送货，2自提.
         */
        @Alias("logistics_type")
        @JsonProperty("logistics_type")
        private String logisticsType;

        /**
         * 订单是否多地址。0单地址，1多地址。.
         */
        @Alias("multi_address")
        @JsonProperty("multi_address")
        private String multiAddress;

        /**
         * 商品信息.
         */
        @Alias("order_lines")
        @JsonProperty("order_lines")
        private List<OrderLines> orderLines;

        /**
         * 收货地址信息.
         */
        @Alias("ship_to")
        @JsonProperty("ship_to")
        private ShipTo shipTo;

        /**
         * 激励使用明细.
         */
        @Alias("special_offer")
        @JsonProperty("special_offer")
        private List<?> specialOffer;

        /**
         * 支付明细.
         */
        @Alias("payment_info")
        @JsonProperty("payment_info")
        private List<?> paymentInfo;

        /**
         * 开票信息.
         */
        @Alias("bill_to")
        @JsonProperty("bill_to")
        private BillTo billTo;

    }

    /**
     * 商品信息.
     */
    @Data
    @Accessors(chain = true)
    public static class OrderLines implements Serializable {

        private static final long serialVersionUID = -2274220595892975546L;

        /**
         * 荣耀商品编码.
         */
        @Alias("prod_code_sale")
        @JsonProperty("prod_code_sale")
        private String prodCodeSale;

        /**
         * 店铺商品编码.
         */
        @Alias("prod_code_cust")
        @JsonProperty("prod_code_cust")
        private String prodCodeCust;

        /**
         * 商品描述.
         */
        @Alias("prod_desc_cust")
        @JsonProperty("prod_desc_cust")
        private String prodDescCust;

        /**
         * 商品型号.
         */
        @Alias("prod_model")
        @JsonProperty("prod_model")
        private String prodModel;

        /**
         * 商品颜色.
         */
        @Alias("color")
        @JsonProperty("color")
        private String color;

        /**
         * 商品类型。1商务机、2样机.
         */
        @Alias("prod_type")
        @JsonProperty("prod_type")
        private String prodType;

        /**
         * 单价（实际付款单价).
         */
        @Alias("price")
        @JsonProperty("price")
        private BigDecimal price;

        /**
         * 分货类型：Distribution(有分货)、NonDistribution(无分货).
         */
        @Alias("distribution_type")
        @JsonProperty("distribution_type")
        private String distributionType;

        /**
         * 单品满减金额.
         */
        @Alias("unit_redction_amount")
        @JsonProperty("unit_redction_amount")
        private BigDecimal unitRedctionAmount;

        /**
         * 商品数量.
         */
        @Alias("quantity")
        @JsonProperty("quantity")
        private Integer quantity;

        /**
         * 退款商品数量.
         */
        @Alias("quantity_refund")
        @JsonProperty("quantity_refund")
        private Integer quantityRefund;

        /**
         * 行总金额（满减后行金额）.
         */
        @Alias("line_amount")
        @JsonProperty("line_amount")
        private BigDecimal lineAmount;

    }

    /**
     * 收货地址信息.
     */
    @Data
    @Accessors(chain = true)
    public static class ShipTo implements Serializable {

        private static final long serialVersionUID = -3479169601258618158L;

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
         * 国家ISO二位编码.
         */
        @Alias("country_code")
        @JsonProperty("country_code")
        private String countryCode;

        /**
         * 收货省份.
         */
        @Alias("province")
        @JsonProperty("province")
        private String province;

        /**
         * 省份ISO二位编码.
         */
        @Alias("province_code")
        @JsonProperty("province_code")
        private String provinceCode;

        /**
         * 收货城市.
         */
        @Alias("city")
        @JsonProperty("city")
        private String city;

        /**
         * 城市ISO二位编码.
         */
        @Alias("city_code")
        @JsonProperty("city_code")
        private String cityCode;

        /**
         * 收货乡镇/街道.
         */
        @Alias("region")
        @JsonProperty("region")
        private String region;

        /**
         * 区县ISO二位编码.
         */
        @Alias("region_code")
        @JsonProperty("region_code")
        private String regionCode;

        /**
         * 乡镇/街道.
         */
        @Alias("street")
        @JsonProperty("street")
        private String street;

        /**
         * 乡镇/街道ISO二位编码.
         */
        @Alias("street_code")
        @JsonProperty("street_code")
        private String streetCode;

        /**
         * 详细地址.
         */
        @Alias("address")
        @JsonProperty("address")
        private String address;

        /**
         * 收货人邮箱.
         */
        @Alias("email")
        @JsonProperty("email")
        private String email;

        /**
         * 地址ID.
         */
        @Alias("addressId")
        @JsonProperty("addressId")
        private String addressId;

        /**
         * 自定义地址ID.
         */
        @Alias("addressId_custom")
        @JsonProperty("addressId_custom")
        private String addressIdCustom;

        /**
         * 邮编.
         */
        @Alias("zip_code")
        @JsonProperty("zip_code")
        private String zipCode;

        /**
         * 自提人.
         */
        @Alias("pickupPerson")
        @JsonProperty("pickupPerson")
        private String pickupPerson;

        /**
         * 自提人手机号码.
         */
        @Alias("pickupPersonPhone")
        @JsonProperty("pickupPersonPhone")
        private String pickupPersonPhone;

        /**
         * 自提人身份证.
         */
        @Alias("pickupPersonIdCard")
        @JsonProperty("pickupPersonIdCard")
        private String pickupPersonIdCard;

    }

    /**
     * 开票信息.
     */
    @Data
    @Accessors(chain = true)
    public static class BillTo implements Serializable {

        private static final long serialVersionUID = 743294330249040678L;

        /**
         * 发票类型 1：普票 2：专票 空：不开发票.
         */
        @Alias("invoice_type")
        @JsonProperty("invoice_type")
        private String invoiceType;

        /**
         * 单位名称.
         */
        @Alias("company_name")
        @JsonProperty("company_name")
        private String companyName;

        /**
         * 纳税人识别号.
         */
        @Alias("vat_tax_payer_id")
        @JsonProperty("vat_tax_payer_id")
        private String vatTaxPayerId;

        /**
         * 注册地址.
         */
        @Alias("registered_address")
        @JsonProperty("registered_address")
        private String registeredAddress;

        /**
         * 注册电话.
         */
        @Alias("registered_phone")
        @JsonProperty("registered_phone")
        private String registeredPhone;

        /**
         * 开户银行.
         */
        @Alias("account_bank")
        @JsonProperty("account_bank")
        private String accountBank;

        /**
         * 银行账户.
         */
        @Alias("account")
        @JsonProperty("account")
        private String account;

        /**
         * 开户姓名.
         */
        @Alias("account_name")
        @JsonProperty("account_name")
        private String accountName;

        /**
         * 手机号码.
         */
        @Alias("cellphone")
        @JsonProperty("cellphone")
        private String cellphone;

        /**
         * 发票收货国家.
         */
        @Alias("bill_country")
        @JsonProperty("bill_country")
        private String billCountry;

        /**
         * 发票收货国家编码.
         */
        @Alias("country_code")
        @JsonProperty("country_code")
        private String countryCode;

        /**
         * 发票收货省.
         */
        @Alias("bill_province")
        @JsonProperty("bill_province")
        private String billProvince;

        /**
         * 发票收货省编码.
         */
        @Alias("province_code")
        @JsonProperty("province_code")
        private String provinceCode;

        /**
         * 发票收货城市.
         */
        @Alias("bill_city")
        @JsonProperty("bill_city")
        private String billCity;

        /**
         * 发票收货城市编码.
         */
        @Alias("city_code")
        @JsonProperty("city_code")
        private String cityCode;

        /**
         * 发票收货县区.
         */
        @Alias("county")
        @JsonProperty("county")
        private String county;

        /**
         * 发票收货县区编码.
         */
        @Alias("county_code")
        @JsonProperty("county_code")
        private String countyCode;

        /**
         * 邮政编码.
         */
        @Alias("bill_zip_code")
        @JsonProperty("bill_zip_code")
        private String billZipCode;

        /**
         * 详细地址.
         */
        @Alias("bill_address")
        @JsonProperty("bill_address")
        private String billAddress;

    }

    /**
     * 激励使用明细.
     */
    @Data
    @Accessors(chain = true)
    public static class SpecialOffer implements Serializable {

        private static final long serialVersionUID = 743294330249040678L;

        /**
         * 激励类型。Special Offer-Price Protection、Special Offer-MDF、Special Offer-Rebate、Special Offer、Other Fee.
         */
        @Alias("line_type")
        @JsonProperty("line_type")
        private String lineType;

        /**
         * 激励金额.
         */
        @Alias("amount")
        @JsonProperty("amount")
        private String amount;

        /**
         * 退款激励金额.
         */
        @Alias("amount_refund")
        @JsonProperty("amount_refund")
        private String amountRefund;

    }

    /**
     * 支付明细.
     */
    @Data
    @Accessors(chain = true)
    public static class PaymentInfo implements Serializable {

        private static final long serialVersionUID = 743294330249040678L;

        /**
         * 支付类型。包括：1-CN/2-Receipt/3-IPS/4-线下付款/5-预付款 /6先货后款.
         */
        @Alias("pay_type")
        @JsonProperty("pay_type")
        private String payType;

        /**
         * 转账单号.
         */
        @Alias("transfer_voucher_no")
        @JsonProperty("transfer_voucher_no")
        private String transferVoucherNo;

        /**
         * 转账日期.
         */
        @Alias("transfer_date")
        @JsonProperty("transfer_date")
        private Date transferDate;

        /**
         * 付款人.
         */
        @Alias("payer")
        @JsonProperty("payer")
        private String payer;

        /**
         * 付款方账号.
         */
        @Alias("payment_account")
        @JsonProperty("payment_account")
        private String paymentAccount;

        /**
         * 付款金额.
         */
        @Alias("payment_amount")
        @JsonProperty("payment_amount")
        private String paymentAmount;

        /**
         * 收款方开户行.
         */
        @Alias("payee_bank")
        @JsonProperty("payee_bank")
        private String payeeBank;

        /**
         * 收款方账号.
         */
        @Alias("payee_account")
        @JsonProperty("payee_account")
        private String payeeAccount;

        /**
         * 线下支付方式.
         */
        @Alias("offline_pay_way")
        @JsonProperty("offline_pay_way")
        private String offlinePayWay;

        /**
         * 上传的转账凭证文件名.
         */
        @Alias("transfer_file_name")
        @JsonProperty("transfer_file_name")
        private String transferFileName;

    }

}
