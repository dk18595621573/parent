package com.cloud.common.core.message;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class InquiryHangOrder implements Serializable {
    /**
     * 订单主键（父订单）
     */
    private Long id;

    /**
     * 新增出价数量
     */
    private Long addHangQuantity;

    /**
     * 成交订单ID（子订单）
     */
    private Long orderId;

    /**
     * 成交价格
     */
    private BigDecimal price;

    /**
     * 成交企业名称
     */
    private String tradeCompanyName;


}
