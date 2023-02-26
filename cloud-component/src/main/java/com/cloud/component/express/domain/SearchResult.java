package com.cloud.component.express.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 下单结果查询返回参数
 * @author nlsm
 */
@Data
@Accessors(chain = true)
public class SearchResult implements Serializable {

    /** 订单号 */
    private String orderNumber;

    /** 运单号 */
    private String mailNumber;

    /** 订单状态 */
    private Integer orderStatus;

    /** 订单状态说明 */
    private String remark;

    /** 异常原因 */
    private String abnormalReason;
}
