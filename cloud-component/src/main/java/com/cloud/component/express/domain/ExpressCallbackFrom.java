package com.cloud.component.express.domain;

import lombok.Data;

/**
 * @author nlsm
 */
@Data
public class ExpressCallbackFrom {

    /**
     * 快递详情json串
     */
    private String param;
    /**
     * 密令
     */
    private String sign;
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 快递公司编码
     */
    private String expressCode;
    /**
     * 快递单号
     */
    private String expressNo;
    /**
     * 手机号码
     */
    private String cellphone;
}
