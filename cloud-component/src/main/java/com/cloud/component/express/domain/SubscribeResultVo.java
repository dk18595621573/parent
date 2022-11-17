package com.cloud.component.express.domain;

import lombok.Data;

@Data
public class SubscribeResultVo {

    private String orderId;
    private String expressCode;
    private String expressNo;
    private String cellphone;
    private String sign;
//    private String param;
    private CallbackExpressResult param;
}
