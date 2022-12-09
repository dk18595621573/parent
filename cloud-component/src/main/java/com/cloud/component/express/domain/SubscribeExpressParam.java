package com.cloud.component.express.domain;

import lombok.Data;

/**
 * 订阅快递信息from
 * @author nlsm
 */
@Data
public class SubscribeExpressParam {

    /**
     * 订单编码
     */
    private Long orderId;

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

    /**
     * 订阅回调地址
     */
    private String expressCallBackUrl;


}
