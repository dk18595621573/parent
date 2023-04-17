package com.cloud.component.express.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 订阅快递信息from
 * @author nlsm
 */
@Data
public class SubscribeExpressParam implements Serializable {

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

    /**
     * 出发地城市，省-市-区，非必填，填了有助于提升签收状态的判断的准确率，请尽量提供
     */
    private String from;
    /**
     * 目的地城市，省-市-区，非必填，填了有助于提升签收状态的判断的准确率，且到达目的地后会加大监控频率，请尽量提供
     */
    private String to;


}
