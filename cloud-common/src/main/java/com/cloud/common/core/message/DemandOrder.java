package com.cloud.common.core.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 需求方订单.
 *
 * @author zenghao
 * @date 2022/7/14
 */
@Data
public class DemandOrder implements Serializable {

    /**
     * 原状态（0：已删除；1: 未报价；2: 待发布；3:报价中；4:待发货；5: 已发货；6:已完成）
     */
    private Integer beforeStatus;

    /**
     * 现状态（0：已删除；1: 未报价；2: 待发布；3:报价中；4:待发货；5: 已发货；6:已完成）
     */
    private Integer afterStatus;

    /**
     * 订单创建企业
     */
    private Long enterpriseId;

    /**
     * 订单对象
     */
    private List<OrderItem> orderDemandVOs;
    
    @Data
    public static class OrderItem implements Serializable {
        /**
         * 主键
         */
        private Long id;

        /**
         * 订单来源
         */
        private String orderSource;

        /**
         * 标准商品库id
         */
        private Long skuId;

        /**
         * 类别
         */
        private String category;

        /**
         * 通用商品名
         */
        private String commonName;

        /**
         * 产品型号
         */
        private String productModel;

        /**
         * 收货地址
         */
        private String receiverAddress;

        /**
         * 省
         */
        private String province;

        /**
         * 省id
         */
        private Long provinceId;

        /**
         * 市
         */
        private String city;

        /**
         * 市id
         */
        private Long cityId;

        /**
         * 区(县)
         */
        private String area;

        /**
         * 区(县)id
         */
        private Long areaId;

        /**
         * 数量
         */
        private Long quantity;

        /**
         * 创建时间
         */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date createTime;

        /**
         * 账期(T+后面是天数|0代表先款)
         */
        private String accountingPeriodValue;

        /**
         * 发布时间
         */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date releaseTime;

        /**
         * 报价
         */
        private String price;

        /**
         * 前置状态(20:新建；21:撤销；22:超时发货；23:超时流拍，30:待抢单 ，31:待确认)
         */
        private Integer subStatus;

        /**
         * 成交价格
         */
        private BigDecimal tradePrice;

        /**
         * 快递公司
         */
        private String companyCode;

        /**
         * 挂单价（最高）
         */
        private BigDecimal priceHighest;

        /**
         * 挂单价（高）
         */
        private BigDecimal priceHign;

        /**
         * 挂单价（低）
         */
        private BigDecimal priceLow;

        /**
         * 挂单价4（最低价）
         */
        private BigDecimal priceLowest;

        /**
         * 收货人姓名
         */
        private String addressee;

        /**
         * 收货人手机号
         */
        private String phone;

        /**
         * 详细地址
         */
        private String receivingAddress;

        /**
         * 挂单id
         */
        private Long hid;

        /**
         * 品牌
         */
        private String brand;

        /**
         * 采购类型（批量采购，一件代发）
         */
        private String purchaseType;

        /**
         * 采购人
         */
        private String createName;

        /**
         * 挂单价状态（1: 可出价；2:待确认 3.失效）
         */
        private Integer priceHighestStatus;

        /**
         * 挂单价状态（1: 可出价；2:待确认 3.失效）
         */
        private Integer priceHignStatus;

        /**
         * 挂单价状态（1: 可出价；2:待确认 3.失效）
         */
        private Integer priceLowStatus;

        /**
         * 挂单价状态（1: 可出价；2:待确认 3.失效）
         */
        private Integer priceLowestStatus;

        /**
         * 待确认挂单价剩余时间，单位:秒
         */
        private Integer remainTime;

        /**
         * 最后一次抢单时间
         */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date lastCompeteTime;

        /**
         * 每次报价时间（单位分钟）
         */
        private Integer quotationInterval;

        /**
         * 发布时间
         */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date releaseTimes;

        /**
         * 串码编码
         */
        private String shopImelNumber;

        /**
         * 快递单号
         */
        private String no;

        /**
         * 供应商名称
         */
        private String nickName;

        /**
         * 仓库名称
         */
        private String entrepotName;

        /**
         * 是否无仓地址（true 是；false 不是）
         */
        private Boolean warehouseAddress;

        /**
         * 串码要求
         */
        private String[] codeRequire;

        /**
         * 包装要求
         */
        private String[] packingRequire;

        /**
         * 其他要求
         */
        private String otherRequire;

        /**
         * 原始单号
         */
        private String originalOrderId;

        /**
         * 管家订单号
         */
        private String gjOrderId;

        /**
         * 发货时效（0：当天；1：明天：2：后天；n：n天后）
         */
        private Long deliveryTime;

        /**
         * 发货截止时间
         */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date deliveryDeadline;

        /**
         * 发货时间
         */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date shipmentsTime;

        /**
         *  发货地点
         */
        private List<String> shipmentAddress;

        /**
         * 订单编码
         */
        private String orderCode;

        /**
         * 指定供应商名称（为空未指定）
         */
        private String merchantCompanyName;

        /**
         * 是否需要补全地址 true: 需要 ； false ： 不需要
         */
        private Boolean addressCompleted;

        /**
         * 物流审核按钮（0：不显示； 1：显示）
         */
        private Integer expressApplyButton = 0;

        /**
         * 处理申请按钮（0：不显示； 1：显示处理申请； 2：显示查看申请）
         */
        private Integer applyButton = 0;

        /**
         * 申诉按钮（0：不显示； 1：显示处理申诉； 2：显示查看申诉）
         */
        private Integer explainedButton = 0;
    }
}
