package com.cloud.common.core.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 接龙订单.
 *
 * @author m
 */
@Data
public class AgentJointOrder implements Serializable {

    /**
     * 接龙订单id
     */
    private Long marketingId;

    /**
     * 记录状态 1.新增 2.修改 3.删除 4.刷新
     */
    private Integer status;

    /**
     * 新增、修改、删除的记录
     */
    private OrderMarketing data;

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class OrderMarketing implements Serializable {
        /**
         * 接龙订单id
         */
        private Long marketingId;

        /**
         * 订单编号.
         */
        private String orderCode;

        /**
         * 采购方企业id
         */
        private Long buyerCompanyId;

        /**
         * 采购方企业名称
         */
        private String buyerCompanyName;

        /**
         * 采购类型（3,接龙抢单 4,询价采购）
         */
        private Integer purchaseType;

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
         * 收货地址
         */
        private String receivingAddress;

        /**
         * 数量
         */
        private Long quantity;

        /**
         * 订单状态
         */
        private Integer orderStatus;

        /**
         * 订单子状态
         */
        private Integer orderSubStatus;

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
         * 最高成交数量
         */
        private Integer priceHighestCount;

        /**
         * 挂单价（高）
         */
        private BigDecimal priceHign;

        /**
         * 高成交数量
         */
        private Integer priceHignCount;

        /**
         * 挂单价（低）
         */
        private BigDecimal priceLow;

        /**
         * 低成交数量
         */
        private BigDecimal priceLowCount;

        /**
         * 税票要求
         */
        private Long taxRequire;

        /**
         * 串码要求
         */
        private List<String> codeRequire;

        /**
         * 串码选项
         */
        private Long codeOptions;

        /**
         * 包装要求
         */
        private List<String> packingRequire;

        /**
         * 其他要求
         */
        private String otherRequire;

        /**
         * 发货时效（0：当天；1：明天：2：后天；n：n天后）
         */
        private Long deliveryDeadline;
        /**
         * 到货时效
         */
        private String deliveryDeadlineValue;

        /**
         * 快递要求
         */
        private String logisticRequire;
    }


}
