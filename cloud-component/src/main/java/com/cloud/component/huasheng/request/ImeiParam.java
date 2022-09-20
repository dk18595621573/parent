package com.cloud.component.huasheng.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class ImeiParam implements Serializable {

    //串码
    private String imei;
    //子订单号
    private String subOrder;
    //Sap单品编码
    private String sapSkuId;

}
