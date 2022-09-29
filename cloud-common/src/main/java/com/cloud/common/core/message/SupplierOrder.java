package com.cloud.common.core.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 供应商订单.
 *
 * @author zenghao
 * @date 2022/7/14
 */
@Data
public class SupplierOrder implements Serializable {

    public static final int STATUS_ADD = 1;
    public static final int STATUS_MODIFY = 2;
    public static final int STATUS_DELETE = 3;

    public static final int STATUS_REFRESH = 4;

    /**
     * 订单id
     */
    private Long hangOrderId;

    /**
     * 记录状态 1.新增 2.修改 3.删除 4.刷新
     */
    private Integer status;

    /**
     * 新增或修改的记录
     */
    private OrderDetail data;

    public static SupplierOrder ofAdd(Long hangOrderId, OrderDetail data) {
        return of(STATUS_ADD, hangOrderId, data);
    }

    public static SupplierOrder ofModify(Long hangOrderId, OrderDetail data) {
        return of(STATUS_MODIFY, hangOrderId, data);
    }

    public static SupplierOrder ofDelete(Long hangOrderId, Long companyId, String province, String brand, Long quantity) {
        OrderDetail detail = new OrderDetail();
        detail.setBuyerCompanyId(companyId);
        detail.setProvince(province);
        detail.setBrand(brand);
        detail.setQuantity(quantity);
        return of(STATUS_DELETE, hangOrderId, detail);
    }

    public static SupplierOrder ofRefresh(Long hangOrderId) {
        return of(STATUS_REFRESH, hangOrderId, null);
    }

    public static SupplierOrder of(Integer status, Long hangOrderId, OrderDetail data) {
        SupplierOrder vo = new SupplierOrder();
        vo.setHangOrderId(hangOrderId);
        vo.setData(data);
        vo.setStatus(status);
        return vo;
    }
    
    @Data
    public static class OrderDetail implements Serializable {

        /**
         * 订单id
         */
        private Long orderId;

        /**
         * 订单挂单id
         */
        private Long hangOrderId;

        /**
         * 订单编号
         */
        private String orderNo;

        /**
         * 采购方企业id
         */
        private Long buyerCompanyId;

        /**
         * 采购方企业名称
         */
        private String buyerCompanyName;

        /**
         * 采购类型（批量采购，一件代发）
         */
        private String purchaseType;

        /**
         * 产品类别
         */
        private String category;

        /**
         * 品牌
         */
        private String brand;

        /**
         * 产品型号
         */
        private String productModel;

        /**
         * 通用名
         */
        private String commonName;

        /**
         * 通用sku
         */
        private String commonSku;

        /**
         * 通用编码
         */
        private String commonCode;

        /**
         * 省 名称
         */
        private String province;

        /**
         * 市 名称
         */
        private String city;

        /**
         * 区 名称
         */
        private String area;

        /**
         * 收货地址
         */
        private String address;

        /**
         * 收货人
         */
        private String receiver;

        /**
         * 手机号
         */
        private String cellphone;

        /**
         * 数量
         */
        private Long quantity;

        /**
         * 是否指定供应商
         */
        private Boolean specifyCompany;

        /**
         * 订单主状态 1: 未报价；2: 待发布；3:报价中；4:待发货；5: 已发货；6:已完成
         */
        private Integer orderStatus;

        /**
         * 订单子状态（20:新建；21:撤销；22:超时发货；23:超时流拍，30:待抢单 ，31:待确认）
         */
        private Integer orderSubStatus;

        /**
         * 挂单状态 （1: 有效；2:无效）
         */
        private Integer hangOrderStatus;

        /**
         * 发货时间
         */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date shipmentsTime;

        /**
         * 账期类型（1:字典；2:自定义）
         */
        private Long accountingPeriodType;

        /**
         * 账期值
         */
        private String accountingPeriodValue;

        /**
         * 挂单价（最高）
         */
        private BigDecimal priceHighest;

        /**
         * 挂单价状态（1: 可出价；2:待确认 3.失效）
         */
        private Integer priceHighestStatus;

        /**
         * 挂单价（高）
         */
        private BigDecimal priceHign;

        /**
         * 挂单价状态（1: 可出价；2:待确认 3.失效）
         */
        private Integer priceHignStatus;

        /**
         * 挂单价（低）
         */
        private BigDecimal priceLow;

        /**
         * 挂单价状态（1: 可出价；2:待确认 3.失效）
         */
        private Integer priceLowStatus;

        /**
         * 挂单价4（最低价）
         */
        private BigDecimal priceLowest;

        /**
         * 挂单价状态（1: 可出价；2:待确认 3.失效）
         */
        private Integer priceLowestStatus;

        /**
         * 每次报价时间（单位分钟）
         */
        private Integer quotationInterval;

        /**
         * 待确认挂单价剩余时间，单位:秒
         */
        private Integer remainTime;

        /**
         * 订单成交价
         */
        private BigDecimal tradePrice;

        /**
         * 当前企业最后一次出价
         */
        private BigDecimal selfPrice;

        /**
         * 最后一次出价时间
         */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date tradeTime;

        /**
         * 企业信誉度 1:已合作，无固定额度 2:已合作，固定额度内 3:已合作，额度已用完 4:平台担保 5:未合作 6:黑名单
         */
        private Integer companyCredit;

        /**
         * 当日抢单状态码 1.抢单中（已抢单还待确认） 2.再次抢单（已抢单但被人抢走） 3.待发货（已抢单成交待发货） 4.已发货（已抢单成交已发货） 5.已收货（已抢单成交仓库已收货） 6.已取消（已抢单成交但需求方撤销）
         */
        private Integer todayStatus;

        /**
         * 税票要求
         */
        private Long taxRequire;

        /**
         * 快递要求
         */
        private String[] logisticRequire;

        /**
         * 其他要求
         */
        private String otherRequire;

        /**
         * 发货时效（距离当前的时间差 0为今天，1为明天 以此类推）
         */
        private Integer deliveryDeadline;

        /**
         * 发货剩余时间 仅发货时效为今天时有值
         */
        private Long deliveryRemainTime;

        /**
         * 发货时间 仅时分
         */
        private String deliveryTime;

        /**
         * 串码要求
         */
        private String[] codeRequire;

        /**
         * 串码选项
         */
        private Long codeOptions;

        /**
         * 包装要求
         */
        private String[] packingRequire;

        /**
         * 是否无仓地址
         */
        private Long warehouseAddress;

        /**
         * 串码异常
         */
        private Long imeiAbnormalCount;

        /**
         * 发布时间
         */
        private String releaseTime;

    }

}
