package com.cloud.component.huasheng.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 异步下单处理结果.
 *
 * @author wyk
 * @date 2022/8/21
 */
@Data
public class OrderInfoResult implements Serializable {

    private Resp resp;

    @Data
    public static class Resp{

        private String code;

        private String msg;

        private OrdInfo result;
    }

    @Data
    public static class OrdInfo {

        private String code;

        private String msg;
        /**
         * 外平台订单号
         */
        private String outOrderId;
        /**
         * 主订单号
         */
        private String ordId;
        /**
         * 子订单信息
         */
        private List<OrdSubInfo> ordSubInfos;

    }

    @Data
    public static class OrdSubInfo {
        /**
         * 子订单商品编码
         */
        private Long skuId;
        /**
         * 子订单号
         */
        private String ordSubId;
        private String gdsId;
        private String gdsName;
        private Long realMoney;
        private Long baseMoney;
        private Long orderAmount;
        private String mainCatgs;
        private String gdsTypeId;
        private String sapSkuId;
        private String skuInfo;
        private String shareCustId;

    }
}
