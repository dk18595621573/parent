package com.cloud.component.ecss.bean.request.order;

import com.cloud.component.ecss.bean.request.BaseECSSRequest;
import com.cloud.component.ecss.bean.response.order.OrderCreateResponse;
import com.cloud.component.ecss.consts.ECSSEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 订单同步接口请求参数.
 *
 * @author Luo
 * @date 2023-03-17 13:31
 */
@Data
@Accessors(chain = true)
@JacksonXmlRootElement(localName = "request")
@EqualsAndHashCode(callSuper = true)
public class OrderCreateRequest extends BaseECSSRequest<OrderCreateResponse> implements Serializable {

    private static final long serialVersionUID = 8843183695867375069L;

    /**
     * 订单列表信息.
     */
    @JacksonXmlProperty(localName = "order")
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
        return ECSSEnum.Method.ORDER_CREATE.getCode();
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
        @JacksonXmlProperty(localName = "orderInfo")
        private OrderInfo orderInfo;

        /**
         * 发货信息.
         */
        @JacksonXmlProperty(localName = "sendGoodsInfo")
        private SendGoodsInfo sendGoodsInfo;

        /**
         * 买家信息.
         */
        @JacksonXmlProperty(localName = "buyerInfo")
        private BuyerInfo buyerInfo;

        /**
         * 发票信息.
         */
        @JacksonXmlProperty(localName = "receiptInfo")
        private ReceiptInfo receiptInfo;

        /**
         * 订单编号.
         * 各个平台的订单号
         * 是否必填：是
         */
        @JacksonXmlProperty(localName = "productInfo")
        @JacksonXmlElementWrapper(localName = "productList")
        private List<ProductInfo> productList;

        /**
         * 扩展字段.
         * 格式：名称:值;名称:值;.......
         * 例如：a:1;b:2;
         */
        @JacksonXmlProperty(localName = "expansion")
        private String expansion;

    }

    /**
     * 订单内容.
     */
    @Data
    @JacksonXmlRootElement(localName = "orderInfo")
    public static class OrderInfo implements Serializable {

        private static final long serialVersionUID = 6328493440768713137L;

        /**
         * 订单编号.
         * 各个平台的订单号
         * 是否必填：是
         */
        @JacksonXmlProperty(localName = "orderId")
        private String orderId;

        /**
         * 订单类型.
         * 1：普通订单 2：促销订单 3：合约机
         * 订单 4：分销订单 5：外呼订单 6：换货
         * 订单 7：流量订单 8：话费订单 9：号卡
         * 订单 10：无实物订单 11：代发订单 12：
         * 云商盟信用购 13：待确认订单
         * 是否必填：是
         *
         * @see com.cloud.component.ecss.consts.ECSSOrderEnum
         */
        @JacksonXmlProperty(localName = "orderCode")
        private Integer orderCode;

        /**
         * 订单状态.
         * 0：草稿 1：待审单 2：不用发货 3：
         * 已取消 16：已签收 17：完成（未支付的
         * 单为草稿状态，已支付的是待发货；如果
         * 是货到付款的，订单状态应该为 1，支付
         * 状态为：未支付）
         * -1：异常单 4：缺货 5：待拣货 6：拣货中
         * 7：校验不通过 8：校验通过 9：待发运
         * 10：配送中 11：已换货 12：部分退货 13：
         * 全部退货 14：理赔中 15：理赔完成 18：
         * 待制单 19：充值成功 20：充值失败 21：
         * 充值中
         * 是否必填：是
         *
         * @see com.cloud.component.ecss.consts.ECSSOrderEnum
         */
        @JacksonXmlProperty(localName = "orderState")
        private String orderState;

        /**
         * 订单成交价格.
         * 订单总金额
         * 格式：小数点后两位的正数
         * 是否必填：是
         */
        @JacksonXmlProperty(localName = "transactionPrice")
        private BigDecimal transactionPrice;

        /**
         * 下单员.
         * 下单员账号
         * 是否必填：否
         */
        @JacksonXmlProperty(localName = "username")
        private String username;

        /**
         * 买家支付积分.
         * 积分金额（对应的是 ：分）
         * 是否必填：否
         */
        @JacksonXmlProperty(localName = "payScore")
        private String payScore;

        /**
         * 返点积分.
         * （对应的是 ：分）
         * 是否必填：否
         */
        @JacksonXmlProperty(localName = "returnScore")
        private String returnScore;

        /**
         * 支付佣金.
         * 格式：小数点后两位的正数
         * 是否必填：否
         */
        @JacksonXmlProperty(localName = "payCommission")
        private BigDecimal payCommission;

        /**
         * 是否为预售订单.
         * 0 否 ；1 是（必填）
         * 是否必填：是
         */
        @JacksonXmlProperty(localName = "depositOrder")
        private Integer depositOrder;

        /**
         * 销售渠道.
         * 0:未区分 1: PC 2: APP 3:WAP
         * 4 :IPAD 5 :TV 6:乐芃
         * 是否必填：是
         *
         * @see com.cloud.component.ecss.consts.ECSSOrderEnum
         */
        @JacksonXmlProperty(localName = "paApp")
        private Integer paApp;

        /**
         * 分销所属地市.
         * 如果是分销订单，则必填(由 ecss 提供) 请
         * 看附录
         * 是否必填：否
         */
        @JacksonXmlProperty(localName = "cityCode")
        private Integer cityCode;

        /**
         * 支付状态.
         * 0：未支付 1：已支付 2：待退款 3：已
         * 退款 4：已取消 5：正在支付
         * 是否必填：是
         *
         * @see com.cloud.component.ecss.consts.ECSSOrderEnum
         */
        @JacksonXmlProperty(localName = "payStatus")
        private String payStatus;

        /**
         * 支付方式.
         * 0：货到付款；1：支付宝；10：网点支付；
         * 11：银行汇款；12：财付通；13：线下交
         * 易；14：微信支付；15：其他；16：在线
         * 支付；17：百度钱包支付；18：联动优势；
         * 19：湖南手机支付；2：余额宝；20：和
         * 包支付；21：话费支付；22：无忧行支付；
         * 23：旧机回收；3：网银支付；4：消费卡；
         * 5：快捷支付；6：积分支付；7：红包支
         * 付；8：信用卡分期；9：找人代付；24：
         * 浦发支付 25：云闪付 26：平安支付 27：
         * 农行支付 99：默认
         * 是否必填：是
         *
         * @see com.cloud.component.ecss.consts.ECSSOrderEnum
         */
        @JacksonXmlProperty(localName = "payType")
        private Integer payType;

        /**
         * 支付账户支付宝或其他支付账号.
         * 各个平台的订单号
         * 是否必填：否
         */
        @JacksonXmlProperty(localName = "paymentAccount")
        private String paymentAccount;

        /**
         * 支付机构.
         * 1：支付宝 2.网银 3.财付通 4.快钱
         * 5.壹卡会 6：湖南手机支付平台
         * 7：微信支付平台
         * 8：工商银行
         * 9：建设银行
         * 10：10085
         * 11：集团
         * 12：中国移动手机支付平台
         * 13：中国移动统一支付平台
         * 14：银联
         * 15：广州银行
         * 16：京东
         * 17：和包
         * 是否必填：否
         *
         * @see com.cloud.component.ecss.consts.ECSSOrderEnum
         */
        @JacksonXmlProperty(localName = "payInstitution")
        private Integer payInstitution;

        /**
         * 订货日期,创建时间.
         * 格式 yyyy-MM-dd HH:mm:ss
         * 是否必填：是
         */
        @JacksonXmlProperty(localName = "orderDate")
        private String orderDate;

        /**
         * 成交时间支付成功的时间.
         * 格式 yyyy-MM-dd HH:mm:ss
         * 是否必填：是
         */
        @JacksonXmlProperty(localName = "transactionTime")
        private String transactionTime;

        /**
         * 最后修改账号.
         * 是否必填：否
         */
        @JacksonXmlProperty(localName = "lastUpdAccount")
        private String lastUpdAccount;

        /**
         * 最后修改时间.
         * 格式 yyyy-MM-dd HH:mm:ss
         * 是否必填：是
         */
        @JacksonXmlProperty(localName = "lastUpdTime")
        private String lastUpdTime;

        /**
         * 订单优惠(店铺折扣).
         * 格式：小数点后两位的正数
         * 是否必填：否
         */
        @JacksonXmlProperty(localName = "discount")
        private String discount;

        /**
         * 内部便签.
         * 是否必填：否
         */
        @JacksonXmlProperty(localName = "note")
        private String note;

        /**
         * 买家留言.
         * 是否必填：否
         */
        @JacksonXmlProperty(localName = "buyerFeedback")
        private String buyerFeedback;

        /**
         * 卖家留言.
         * 是否必填：是
         */
        @JacksonXmlProperty(localName = "sellersMessage")
        private String sellersMessage;

        /**
         * 是否虚拟发货.
         * 0 否， 1 是
         * 是否必填：是
         */
        @JacksonXmlProperty(localName = "virtualGoods")
        private Integer virtualGoods;

        /**
         * 合约机类型.
         * 0:无 1:信用购 2:优惠购机 3:大顺差
         * 4:积分购 5:信用购服务包 6:鉴权优惠
         * 7:号码换卡 8:号码补卡 9:5G 金币 10
         * 其他合约 11：顺差让利 12：顺差和金币
         * 如果合约机类型是非 0 的，订单类型必须
         * 填合约机
         * 是否必填：否
         */
        @JacksonXmlProperty(localName = "businessType")
        private Integer businessType;

    }

    /**
     * 发货信息.
     */
    @Data
    @JacksonXmlRootElement(localName = "sendGoodsInfo")
    public static class SendGoodsInfo implements Serializable {

        private static final long serialVersionUID = 7999274483806056074L;

        /**
         * 运费.
         * 格式：小数点后两位的正数
         * 如果包邮则填写 0
         * 是否必填：是
         */
        @JacksonXmlProperty(localName = "freight")
        private BigDecimal freight;

        /**
         * 收货人.
         * 收货人姓名
         * 是否必填：是
         */
        @JacksonXmlProperty(localName = "consignee")
        private String consignee;

        /**
         * 收货人电话.
         * 座机
         * 是否必填：否
         */
        @JacksonXmlProperty(localName = "telephone")
        private String telephone;

        /**
         * 收货人手机.
         * 电话手机二选一
         * 是否必填：否
         */
        @JacksonXmlProperty(localName = "mobile")
        private String mobile;

        /**
         * 邮政编码.
         * 是否必填：否
         */
        @JacksonXmlProperty(localName = "buyerZip")
        private String buyerZip;

        /**
         * 物流商 Id.
         * 1、顺丰速运 2、科捷、3、中通、4、韵
         * 达 5、圆通、6、全峰 7、汇通 8、天天 9、
         * 宅急送 10、国通 11、龙邦物流 12、中国
         * 邮政 13、安能 14、申通 15、EMS
         * 16：德邦 17：京东快递 18：兆航物流
         * 19：南方传媒物流 20：万象物流 21：鸿
         * 讯物流 22：顺捷丰达物流 23：客户自提
         * 26：中山日报 27：全一快递 28：百世快
         * 递 29：宏递快递 30：安时递 31：天津
         * 远达和顺 32：特急送 33：丹鸟 34：万
         * 家通 35：秦邦物流 36：速必达 37：海
         * 辰物流 38：美团
         * 是否必填：否
         *
         * @see com.cloud.component.ecss.consts.ECSSExpressEnum
         */
        @JacksonXmlProperty(localName = "express")
        private Integer express;

        /**
         * 物流单号.
         * 快递单号
         * 是否必填：否
         */
        @JacksonXmlProperty(localName = "expressOrderNo")
        private String expressOrderNo;

        /**
         * 买家地址.
         * 包含省市区极详细地址
         * 是否必填：是
         */
        @JacksonXmlProperty(localName = "buyerAddress")
        private String buyerAddress;

        /**
         * 省.
         * 如：广东（广东省）
         * 是否必填：是
         */
        @JacksonXmlProperty(localName = "province")
        private String province;

        /**
         * 省代号.
         * 如：国家省代号
         * 是否必填：否
         */
        @JacksonXmlProperty(localName = "provinceCode")
        private String provinceCode;

        /**
         * 市.
         * 如：广州（广州市）
         * 是否必填：是
         */
        @JacksonXmlProperty(localName = "city")
        private String city;

        /**
         * 市代号.
         * 国家市代号
         * 是否必填：否
         */
        @JacksonXmlProperty(localName = "cityCode")
        private String cityCode;

        /**
         * 区/县码.
         * 如：天河（天河区）
         * 是否必填：是
         */
        @JacksonXmlProperty(localName = "county")
        private String county;

        /**
         * 区代号.
         * 国家区代号
         * 是否必填：是
         */
        @JacksonXmlProperty(localName = "countyCode")
        private String countyCode;

        /**
         * 地址（除省市区外）.
         * 热敏联想快递单号 省市区,地址必填，如
         * 用背胶可只填 buyerAddress
         * 是否必填：是
         */
        @JacksonXmlProperty(localName = "supplyAddress")
        private String supplyAddress;

        /**
         * 配送方式.
         * 1：送货上门 2：自提
         * 是否必填：否
         */
        @JacksonXmlProperty(localName = "supplyType")
        private String supplyType;

    }

    /**
     * 买家信息.
     */
    @Data
    @JacksonXmlRootElement(localName = "buyerInfo")
    public static class BuyerInfo implements Serializable {

        private static final long serialVersionUID = 3735035555351087429L;

        /**
         * 买家昵称.
         * 是否必填：否
         */
        @JacksonXmlProperty(localName = "buyerNick")
        private String buyerNick;

        /**
         * 真实姓名.
         * 是否必填：否
         */
        @JacksonXmlProperty(localName = "realName")
        private String realName;

        /**
         * 电子邮件.
         * 是否必填：否
         */
        @JacksonXmlProperty(localName = "email")
        private String email;

        /**
         * 买家的手机.
         * 是否必填：否
         */
        @JacksonXmlProperty(localName = "phone")
        private String phone;

    }

    /**
     * 发票信息.
     */
    @Data
    @JacksonXmlRootElement(localName = "receiptInfo")
    public static class ReceiptInfo implements Serializable {

        private static final long serialVersionUID = 5541710782897164376L;

        /**
         * 是否需要发票.
         * 0 否，1 是
         * 是否必填：是
         */
        @JacksonXmlProperty(localName = "isNeedInvoice")
        private Integer isNeedInvoice;

        /**
         * 发票类型.
         * 1：增值 2：普票 3：专票 4：电子发票
         * 如果需要开发票则必填
         * 是否必填：是
         */
        @JacksonXmlProperty(localName = "invoiceType")
        private Integer invoiceType;

        /**
         * 是否开具电子发票.
         * 0 否，1 是
         * 如果需要开发票则必填
         * 是否必填：是
         */
        @JacksonXmlProperty(localName = "invoiceStatus")
        private String invoiceStatus;

        /**
         * 发票抬头.
         * 如果需要开发票则必填
         * 是否必填：是
         */
        @JacksonXmlProperty(localName = "invoiceNo")
        private String invoiceNo;

        /**
         * 订单编号.
         * 各个平台的订单号
         * 是否必填：是
         */
        @JacksonXmlProperty(localName = "invoiceContent")
        private String invoiceContent;

        /**
         * 发票内容.
         * 默认打商品
         * 是否必填：否
         */
        @JacksonXmlProperty(localName = "invoiceCode")
        private String invoiceCode;

        /**
         * 开票人.
         * 开票人姓名/账号
         * 是否必填：否
         */
        @JacksonXmlProperty(localName = "drawer")
        private String drawer;

        /**
         * 发票金额.
         * 格式：小数点后两位的正数
         * 是否必填：是
         */
        @JacksonXmlProperty(localName = "invoiceMoney")
        private BigDecimal invoiceMoney;

    }

    /**
     * 产品信息.
     */
    @Data
    @JacksonXmlRootElement(localName = "productInfo")
    public static class ProductInfo implements Serializable {

        private static final long serialVersionUID = -5672694497350611519L;

        /**
         * 产品名称.
         * 是否必填：是
         */
        @JacksonXmlProperty(localName = "title")
        private String title;

        /**
         * 订货数量.
         * 是否必填：是
         */
        @JacksonXmlProperty(localName = "totalNum")
        private String totalNum;

        /**
         * 条形码.
         * Ecss 线下分配（ECSS与平台商品的唯一标识）
         * 是否必填：是
         */
        @JacksonXmlProperty(localName = "barCode")
        private String barCode;

        /**
         * 销售单价.
         * 格式：小数点后两位的正数
         * 是否必填：是
         */
        @JacksonXmlProperty(localName = "price")
        private BigDecimal price;

        /**
         * 规格.
         * 是否必填：否
         */
        @JacksonXmlProperty(localName = "spec")
        private String spec;

        /**
         * 优惠价.
         * 商品优惠价
         * 格式：小数点后两位的正数
         * 是否必填：否
         */
        @JacksonXmlProperty(localName = "preferPrice")
        private BigDecimal preferPrice;

        /**
         * 原价.
         * 格式：小数点后两位的正数
         * 是否必填：否
         */
        @JacksonXmlProperty(localName = "costPrice")
        private BigDecimal costPrice;

    }

}
