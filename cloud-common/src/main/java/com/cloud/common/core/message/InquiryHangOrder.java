package com.cloud.common.core.message;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class InquiryHangOrder implements Serializable {
    /**
     * 订单主键
     */
    private Long id;

    /**
     * 新增出价数量
     */
    private Long addHangQuantity;
}
