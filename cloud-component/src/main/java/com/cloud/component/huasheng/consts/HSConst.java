package com.cloud.component.huasheng.consts;


/**
 * 华盛静态类
 */
public interface HSConst {



    /**
     * 接口入参出参格式
     */
    String FORMAT = "json";

    /**
     * 签名方式
     */
    String  SIGN_METHOD = "md5";

    /**
     * 签名编码
     */
    String UTF8 = "utf-8";
    /**
     * 签名key
     */
    String SIGN_KEY = "sign";


    /**
     * 超时时间
     */
    int TIMEOUT = 5000;

    /******************   接口名称    *************************/


    String METHOD_CHANGE_ORDER_STATUS = "aip.vsens.order.status.change";
    String METHOD_CREATE_ORDER_ASY = "aip.vsens.order.asynCreateOrder";
    String METHOD_ORDER_BACK_MONEY = "aip.vsens.order.back.newBackMoney";
    String METHOD_ORDER_BACK_GDS = "aip.vsens.order.back.newBackGds";
    String METHOD_UPDATE_SKU_NOTICE = "aip.vsens.notice.updateSku";
    String METHOD_GET_EXPRESS = "aip.vsens.express.newGetExpress";
    String METHOD_ORDER_STATUS_NOTICE =  "aip.vsens.notice.orderStatusNotice";
    String METHOD_QUERY_SKU_STOCK = "aip.vsens.gds.skuStock";


}
