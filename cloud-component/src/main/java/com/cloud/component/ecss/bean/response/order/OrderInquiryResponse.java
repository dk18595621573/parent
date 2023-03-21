package com.cloud.component.ecss.bean.response.order;

import com.cloud.component.ecss.bean.response.ECSSResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
 * 订单 查询（获取） 接口响应参数.
 *
 * @author Luo
 * @date 2023-03-20 14:25
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "response")
public class OrderInquiryResponse extends ECSSResponse {

    private static final long serialVersionUID = -889303419942088773L;

    /**
     * 总页数.
     */
    @JacksonXmlProperty(localName = "totalPage")
    private Integer totalPage;

    /**
     * 当前页码.
     */
    @JacksonXmlProperty(localName = "pageNo")
    private Integer pageNo;

    /**
     * 每页显示的条数.
     */
    @JacksonXmlProperty(localName = "pageSize")
    private Integer pageSize;

    /**
     * 请求订单.
     */
    @JacksonXmlProperty(localName = "orderList")
    @JacksonXmlElementWrapper(localName = "orderList")
    private List<Order> orderList;

    /**
     * 单张订单信息.
     */
    @Data
    @JacksonXmlRootElement(localName = "order")
    @JsonIgnoreProperties(ignoreUnknown = true)
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
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class OrderInfo implements Serializable {

        private static final long serialVersionUID = 6328493440768713137L;

        /**
         * 订单编号.
         * 各个平台的订单号
         */
        @JacksonXmlProperty(localName = "orderId")
        private String orderId;

        /**
         * ECSS订单号.
         * ECSS 系统生成的订单号（ECSS系统中唯一标识）
         */
        @JacksonXmlProperty(localName = "orderCode")
        private String orderCode;

        /**
         * 订单状态.
         * -1：异常单 0：草稿 1:待审单 2：不用发
         * 货 3：已取消 4：缺货 5：待拣货 6：拣货
         * 中 7：校验不通过 8：校验通过 9：待发运
         * 10：配送中 11：已换货 12：部分退货 13：
         * 全部退货 14：理赔中 15：理赔完成 16：
         * 已签收 17：完成 18：待制单 19：充值
         * 成功 20：充值失败 21：充值中
         */
        @JacksonXmlProperty(localName = "orderStatus")
        private Integer orderStatus;

        /**
         * 订单类型.
         * 1：普通订单 2：促销订单 3：合约机订单
         * 4：分销订单 5：外呼订单 6：换货订单 7：
         * 流量订单 8：话费订单 9：号卡订单 10：
         * 无实物订单 11：代发订单 12：云商盟信
         * 用购 13：待确认订单
         */
        @JacksonXmlProperty(localName = "orderType")
        private Integer orderType;

        /**
         * 订单来源.
         * 1：天猫 2：1 号店 3：亚马逊 4：当当 5：
         * 中国移动手机商城 6：微信商城 7：
         * 10086 商城 8：工商银行 9：CTMS 10: 其他
         * 11：分销 12：手工 13：ECSS 外呼 14：
         * 拍拍 15：集团商城 16：双微 17：惠州订
         * 单 18：人工导入 19：流量订单 20;岭南
         * 优品 APP 21：京东 22：福建移动 23：咪
         * 咕 24：京东租机 25：拼多多 26：江苏移
         * 动 27：浙江移动 28：中移电商 29：京东
         * 自营 30：中移积分商城 31：易店铺 32：
         * 一件代发 33：和助力 34：广东移动 35：
         * 惠购小店 36：集运 37：云店铺平台 39：
         * 在线轻合约 40：中移微厅 41：陕西移动
         * 42：河南移动
         */
        @JacksonXmlProperty(localName = "orderSource")
        private Integer orderSource;

        /**
         * 订单成交价格.
         * 订单总金额
         * 格式：小数点后两位的正数
         */
        @JacksonXmlProperty(localName = "payment")
        private BigDecimal payment;

        /**
         * 销售渠道.
         * 0:未区分 1: PC 2: APP 3: WAP
         * 4: IPAD 5: TV 6:乐芃
         */
        @JacksonXmlProperty(localName = "pcApp")
        private Integer pcApp;

        /**
         * 支付状态.
         * 0：未支付 1：已支付 2：待退款 3：已
         * 退款 4：已取消 5：正在支付
         */
        @JacksonXmlProperty(localName = "payStatus")
        private Integer payStatus;

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
         */
        @JacksonXmlProperty(localName = "payType")
        private Integer payType;

        /**
         * 支付单号.
         */
        @JacksonXmlProperty(localName = "paymentId")
        private String paymentId;

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
         */
        @JacksonXmlProperty(localName = "payInstitution")
        private Integer payInstitution;

        /**
         * 支付时间.
         * 格式 yyyy-MM-dd HH:mm:ss
         * 是否必填：是
         */
        @JacksonXmlProperty(localName = "transactionTime")
        private String transactionTime;

        /**
         * 支付账户.
         * 客户支付宝账号或者其他支付账号
         */
        @JacksonXmlProperty(localName = "payAccount")
        private String payAccount;

        /**
         * 买家支付积分.
         * 买家支付积分金额（单位：元）
         */
        @JacksonXmlProperty(localName = "payScore")
        private BigDecimal payScore;

        /**
         * 返点积分（单位：元）.
         */
        @JacksonXmlProperty(localName = "returnScore")
        private BigDecimal returnScore;

        /**
         * 支付佣金.
         * 支付给平台的佣金
         */
        @JacksonXmlProperty(localName = "payCommission")
        private BigDecimal payCommission;

        /**
         * 服务金额.
         * 联保等服务金额
         */
        @JacksonXmlProperty(localName = "servicePayment")
        private BigDecimal servicePayment;

        /**
         * 订单折扣.
         * 订单的总折扣
         */
        @JacksonXmlProperty(localName = "discount")
        private BigDecimal discount;

        /**
         * 参与的促销方式.
         * 促销名称：如买就送，
         */
        @JacksonXmlProperty(localName = "spCode")
        private Integer spCode;

        /**
         * 买家留言.
         */
        @JacksonXmlProperty(localName = "buyerFeedback")
        private String buyerFeedback;

        /**
         * 卖家留言.
         */
        @JacksonXmlProperty(localName = "sellersMessage")
        private String sellersMessage;

        /**
         * 平台上的状态.
         * key
         */
        @JacksonXmlProperty(localName = "platformOrderKey")
        private String platformOrderKey;

        /**
         * 平台订单值.
         * key 所对应的值
         */
        @JacksonXmlProperty(localName = "platformOrderValue")
        private String platformOrderValue;

        /**
         * 是否为预售订单.
         * 0：否 ；1：是
         */
        @JacksonXmlProperty(localName = "depositOrder")
        private Integer depositOrder;

        /**
         * 预售订单号.
         * 预售已支付订金的订单号（如果预售单支
         * 付了订金，而购买单是另外一张单，需要
         * 填预售单号，以此来关联两张单。
         */
        @JacksonXmlProperty(localName = "preSaleOrder")
        private String preSaleOrder;

        /**
         * 订单订金.
         */
        @JacksonXmlProperty(localName = "orderDeposit")
        private BigDecimal orderDeposit;

        /**
         * 订金支付时间.
         * 格式 yyyy-MM-dd HH:mm:ss
         */
        @JacksonXmlProperty(localName = "depositPaidTime")
        private String depositPaidTime;

        /**
         * 所属地市 code.
         * 请看附录
         */
        @JacksonXmlProperty(localName = "cityCode")
        private Integer cityCode;

        /**
         * 所属地市 value.
         * 对应的名称
         */
        @JacksonXmlProperty(localName = "cityName")
        private String cityName;

        /**
         * ecss 店铺标识.
         */
        @JacksonXmlProperty(localName = "shopId")
        private String shopId;

        /**
         * 店铺描述.
         * Ecss 系统中店铺对应的名称
         */
        @JacksonXmlProperty(localName = "shopDesc")
        private String shopDesc;

        /**
         * 库房编码.
         */
        @JacksonXmlProperty(localName = "storeNo")
        private String storeNo;

        /**
         * 库房名称.
         */
        @JacksonXmlProperty(localName = "storeName")
        private String storeName;

        /**
         * 促销组合编码.
         * Ecss 系统会把组合编码内的商品自动加到订单订单项
         */
        @JacksonXmlProperty(localName = "promotionCombination")
        private String promotionCombination;

        /**
         * 创建时间.
         * 格式 yyyy-MM-dd HH:mm:ss
         */
        @JacksonXmlProperty(localName = "orderDate")
        private String orderDate;

        /**
         * Ecss 获取时间.
         * 格式 yyyy-MM-dd HH:mm:ss
         */
        @JacksonXmlProperty(localName = "getOrderTime")
        private String getOrderTime;

        /**
         * ecss 审单时间.
         * Ecss 审核订单时间
         */
        @JacksonXmlProperty(localName = "examineTime")
        private String examineTime;

        /**
         * 校验时间.
         * Ecss 校验商品时间
         */
        @JacksonXmlProperty(localName = "orderCheckTime")
        private String orderCheckTime;

        /**
         * 确认收货时间.
         * 客户平台上确认收货时间
         */
        @JacksonXmlProperty(localName = "endDate")
        private String endDate;

        /**
         * 拣货单号.
         * Ecss系统生成的拣货单号
         */
        @JacksonXmlProperty(localName = "opCode")
        private String opCode;

        /**
         * 所以产品名称.
         * 多个订单项的名称合并，以分号分割
         */
        @JacksonXmlProperty(localName = "productName")
        private String productName;

        /**
         * 产品重量.
         * 单位 ：kg
         */
        @JacksonXmlProperty(localName = "productWeight")
        private String productWeight;

        /**
         * 订单审核状态.
         * 暂留（1：待审核 2：审核通过 3：审核不通过）
         */
        @JacksonXmlProperty(localName = "orderauditStatus")
        private Integer orderauditStatus;

        /**
         * 审单人.
         * Ecss 审单人 账号
         */
        @JacksonXmlProperty(localName = "examinePerson")
        private Integer examinePerson;

        /**
         * 最后修改账号.
         * Ecss 对该订单操作的最后修改
         */
        @JacksonXmlProperty(localName = "lastUpdAccount")
        private String lastUpdAccount;

        /**
         * 最后修改时间.
         * 格式 yyyy-MM-dd HH:mm:ss
         */
        @JacksonXmlProperty(localName = "lastUpdTime")
        private String lastUpdTime;

        /**
         * 发货方式.
         * 0：线下发货 1：检验发货
         */
        @JacksonXmlProperty(localName = "orderFlowtype")
        private Integer orderFlowtype;

        /**
         * 备注.
         * Ecss 系统人员对订单的备注
         */
        @JacksonXmlProperty(localName = "remark")
        private String remark;

        /**
         * 是否虚拟物品.
         * 0：否 1：是
         */
        @JacksonXmlProperty(localName = "virtualGoods")
        private Integer virtualGoods;

        /**
         * 合并拆分状态.
         */
        @JacksonXmlProperty(localName = "mergeStatus")
        private Integer mergeStatus;

        /**
         * 是否作废.
         * 0：否 1：是
         */
        @JacksonXmlProperty(localName = "isNullify")
        private Integer isNullify;

        /**
         * 是否验货.
         * 0 否， 1 是
         * 是否必填：是
         */
        @JacksonXmlProperty(localName = "isCkeck")
        private Integer isCkeck;

        /**
         * 是否库存订单.
         */
        @JacksonXmlProperty(localName = "isStockOrder")
        private Integer isStockOrder;

        /**
         * 是否开发票.
         * 0：否 1：是
         */
        @JacksonXmlProperty(localName = "isNeedInvoice")
        private Integer isNeedInvoice;

        /**
         * 订单信息扩展字段.
         * 格式：key:value;key:value;
         */
        @JacksonXmlProperty(localName = "orderInfoExpansion")
        private String orderInfoExpansion;

    }

    /**
     * 发货信息.
     */
    @Data
    @JacksonXmlRootElement(localName = "sendGoodsInfo")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SendGoodsInfo implements Serializable {

        private static final long serialVersionUID = 7999274483806056074L;

        /**
         * 运费.
         * 格式：小数点后两位的正数
         * 如果包邮则填写 0
         */
        @JacksonXmlProperty(localName = "freight")
        private BigDecimal freight;

        /**
         * 收货人.
         * 收货人姓名
         */
        @JacksonXmlProperty(localName = "consignee")
        private String consignee;

        /**
         * 收货人电话.
         * 座机
         */
        @JacksonXmlProperty(localName = "telephone")
        private String telephone;

        /**
         * 收货人手机.
         * 手机
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
         */
        @JacksonXmlProperty(localName = "express")
        private Integer express;

        /**
         * 物流单号.
         * 快递单号
         */
        @JacksonXmlProperty(localName = "expressOrderNo")
        private String expressOrderNo;

        /**
         * 买家地址.
         * 包含省市区极详细地址
         */
        @JacksonXmlProperty(localName = "buyerAddress")
        private String buyerAddress;

        /**
         * 省.
         * 如：广东（广东省）
         */
        @JacksonXmlProperty(localName = "province")
        private String province;

        /**
         * 市.
         * 如：广州（广州市）
         */
        @JacksonXmlProperty(localName = "city")
        private String city;

        /**
         * 区/县码.
         * 如：天河（天河区）
         */
        @JacksonXmlProperty(localName = "county")
        private String county;

        /**
         * 地址（除省市区外）.
         * 省市区代码,地址或 buyerAddress 必填一个
         */
        @JacksonXmlProperty(localName = "supplyAddress")
        private String supplyAddress;

        /**
         * 配送方式.
         * 1：送货上门 2：自提
         */
        @JacksonXmlProperty(localName = "supplyType")
        private Integer supplyType;

        /**
         * 配送状态.
         * 1：待配送 2：配送中 3：配送成功 4：
         * 配送失败 5：配送延迟
         */
        @JacksonXmlProperty(localName = "supplyStatus")
        private Integer supplyStatus;

        /**
         * 仓库发货时间.
         * 仓库发货的时间
         */
        @JacksonXmlProperty(localName = "supplyTime")
        private String supplyTime;

        /**
         * 签收时间.
         * Ecss 更新订单为已签收的时间
         */
        @JacksonXmlProperty(localName = "overTime")
        private String overTime;

        /**
         * 配送描述.
         */
        @JacksonXmlProperty(localName = "supplyRemark")
        private String supplyRemark;

        /**
         * 发货回传时间.
         * Ecss 回传发货给平台的时间
         */
        @JacksonXmlProperty(localName = "automaticDeliveryTime")
        private String automaticDeliveryTime;

        /**
         * 门店编码.
         * 门店自提才有
         */
        @JacksonXmlProperty(localName = "mobileStoreCode")
        private String mobileStoreCode;

        /**
         * 配送信息扩展字段.
         * 格式：key:value;key:value;
         */
        @JacksonXmlProperty(localName = "sendGoodsInfoExpansion")
        private String sendGoodsInfoExpansion;

    }

    /**
     * 买家信息.
     */
    @Data
    @JacksonXmlRootElement(localName = "buyerInfo")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BuyerInfo implements Serializable {

        private static final long serialVersionUID = 3735035555351087429L;

        /**
         * 买家昵称.
         */
        @JacksonXmlProperty(localName = "buyerNick")
        private String buyerNick;

        /**
         * 真实姓名.
         */
        @JacksonXmlProperty(localName = "realName")
        private String realName;

        /**
         * 电子邮件.
         */
        @JacksonXmlProperty(localName = "email")
        private String email;

        /**
         * 买家的手机.
         */
        @JacksonXmlProperty(localName = "phone")
        private String phone;

    }

    /**
     * 发票信息.
     */
    @Data
    @JacksonXmlRootElement(localName = "receiptInfo")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ReceiptInfo implements Serializable {

        private static final long serialVersionUID = 5541710782897164376L;

        /**
         * 发票类型.
         * 1：增值 2：普票 3：专票 4：电子发票
         * 如果需要开发票则必填
         */
        @JacksonXmlProperty(localName = "invoiceType")
        private Integer invoiceType;

        /**
         * 开票状态.
         * 0：未开 1：已开
         */
        @JacksonXmlProperty(localName = "invoiceStatus")
        private String invoiceStatus;

        /**
         * 发票抬头.
         * 如果需要开发票则必填
         */
        @JacksonXmlProperty(localName = "invoiceNo")
        private String invoiceNo;

        /**
         * 发票金额.
         * 如果需要开发票则必填
         * 格式：小数点后两位的正数
         */
        @JacksonXmlProperty(localName = "invoiceMoney")
        private BigDecimal invoiceMoney;

        /**
         * 发票内容.
         * 默认打商品
         */
        @JacksonXmlProperty(localName = "invoiceContent")
        private String invoiceContent;

        /**
         * 发票号.
         */
        @JacksonXmlProperty(localName = "invoiceCode")
        private String invoiceCode;

        /**
         * 开票人.
         * 开票人姓名/账号
         */
        @JacksonXmlProperty(localName = "drawer")
        private String drawer;

        /**
         * 是否红冲.
         * 0：否 1：是
         */
        @JacksonXmlProperty(localName = "isRcw")
        private Integer isRcw;

        /**
         * 是否开具电子发票.
         * 0：否 1：是
         */
        @JacksonXmlProperty(localName = "isElectronicInvoice")
        private Integer isElectronicInvoice;

        /**
         * 创建时间.
         */
        @JacksonXmlProperty(localName = "createTime")
        private String createTime;

        /**
         * 纳税人识别号.
         */
        @JacksonXmlProperty(localName = "buyerTaxNo")
        private String buyerTaxNo;

        /**
         * 发票信息扩展字段.
         */
        @JacksonXmlProperty(localName = "receiptInfoExpansion")
        private String receiptInfoExpansion;

    }

    /**
     * 产品信息.
     */
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JacksonXmlRootElement(localName = "productInfo")
    public static class ProductInfo implements Serializable {

        private static final long serialVersionUID = -5672694497350611519L;

        /**
         * 产品名称.
         */
        @JacksonXmlProperty(localName = "title")
        private String title;

        /**
         * 订货数量.
         */
        @JacksonXmlProperty(localName = "totalNum")
        private String totalNum;

        /**
         * 条形码.
         * Ecss 线下分配（ECSS与平台商品的唯一标识）
         */
        @JacksonXmlProperty(localName = "barCode")
        private String barCode;

        /**
         * 商品价格.
         * 格式：小数点后两位的正数
         */
        @JacksonXmlProperty(localName = "price")
        private BigDecimal price;

        /**
         * 商品详细描述.
         */
        @JacksonXmlProperty(localName = "desc")
        private String desc;

        /**
         * 发票价格.
         * 格式：小数点后两位的正数
         */
        @JacksonXmlProperty(localName = "invoicePrice")
        private BigDecimal invoicePrice;

        /**
         * 商品 69 码.
         * 按国家 69 码长度
         */
        @JacksonXmlProperty(localName = "commodityCodes")
        private String commodityCodes;

        /**
         * 检验时间.
         * 仓库校验商品时间
         */
        @JacksonXmlProperty(localName = "checkTime")
        private String checkTime;

        /**
         * 品牌.
         */
        @JacksonXmlProperty(localName = "brand")
        private String brand;

        /**
         * 型号.
         */
        @JacksonXmlProperty(localName = "model")
        private String model;

        /**
         * 规格.
         */
        @JacksonXmlProperty(localName = "specifications")
        private String specifications;

        /**
         * Ecss 订单项.
         */
        @JacksonXmlProperty(localName = "orderItemId")
        private String orderItemId;

        /**
         * 订单项状态.
         */
        @JacksonXmlProperty(localName = "orderitemStatus")
        private String orderitemStatus;

        /**
         * 平台子订单号.
         */
        @JacksonXmlProperty(localName = "suborder")
        private String suborder;

        /**
         * Imei 列表.
         */
        @JacksonXmlProperty(localName = "imeisList")
        @JacksonXmlElementWrapper(localName = "imeisList")
        private List<Imei> imeisList;

        /**
         * 产品扩展字段.
         */
        @JacksonXmlProperty(localName = "productInfoExpansion")
        private String productInfoExpansion;

        /**
         * 扩展字段（产品外部编码）.
         * 请求方式
         * productInfo.productInfoExpansion.proCode
         */
        @JacksonXmlProperty(localName = "proCode")
        private String proCode;

        /**
         * 扩展字段（产品备用条码 2）.
         * productInfo.productInfoExpansion.spareCod
         * eTwo
         */
        @JacksonXmlProperty(localName = "spareCodeTwo")
        private String spareCodeTwo;

        /**
         * Imei对象.
         */
        @Data
        @JacksonXmlRootElement(localName = "imei")
        public static class Imei implements Serializable {

            private static final long serialVersionUID = -5672694497350611519L;

            /**
             * 条码.
             */
            @JacksonXmlProperty(localName = "imeiCode")
            private String imeiCode;

            /**
             * 是否良品.
             * 0：否；1：是
             */
            @JacksonXmlProperty(localName = "qualityStatus")
            private Integer qualityStatus;

            /**
             * 销售状态.
             * 1：已销售；2：退销售；3：已置换
             * 3：以换货 4 合约机校验成功 -1 合约机校
             * 验失败（imei 销售状态）
             */
            @JacksonXmlProperty(localName = "salesStatus")
            private Integer salesStatus;

        }

    }

}
