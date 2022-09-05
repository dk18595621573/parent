package com.cloud.component.huasheng.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 异步下单参数.
 *
 * @author wyk
 * @date 2022/8/22
 */
@Data
public class CreateOrdInfoParam implements Serializable {

    OrdInfo ordInfo;

    @Data
    public class OrdInfo{
        /**
         * 省分编码（com.cloud.api.service.huasheng.ProvinceCodeEnum）	16
         */
        String provinceCode;

        /**
         * 订单人电话	32
         */
        String mobile;

        /**
         * 外平台主订单号	32
         */
        String outOrderId;

        /**
         * 订单实付金额(单位:分)	16
         */
        Long realMoney;

        /**
         * 运费(单位:分)	10
         */
        Long realExpressFee;

        /**
         * 订购总数量	10
         */
        Long orderAmount;

        /**
         * 下单时间 yyyymmdd24hhmiss	14
         */
        String orderTime;

        /**
         * 订单来源类型（06广东存量平台， 08广东沃厅，88阿里天猫）	4
         */
        String sourceType;

        /**
         * 支付类型(0-在线支付, 1-线下支付)	1
         */
        String payType;

        /**
         * 支付方式 （payType=1payWay=9999,
         * 9008	微信（WAP支付）
         * 9999	凭证支付（WAP支付）
         * 8001	联通收款
         * 8002	华盛收款）	4
         */
        String payWay;

        /**
         * 支付流水号（payType=0时必传）	32
         */
        String payTransNo;

        /**
         * 支付状态(0-未支付, 1-已支付)	1
         */
        String payFlag;

        /**
         * 支付时间     yyyymmdd24hhmiss	14
         */
        String payTime;

        /**
         * 收货人姓名	128
         */
        String contactName;

        /**
         * 收货人电话	32
         */
        String contactPhone;

        /**
         * 收货地址	256
         */
        String chnlAddress;

        /**
         * 收货地址邮政编码	6
         */
        String postCode;

        /**
         * 收货人省分名称	16
         */
        String recvProvince;

        /**
         * 收货人地市名称	16
         */
        String recvCity;

        /**
         * 收货人区县名称	16
         */
        String recvCountry;

        /**
         * 多次支付信息集合
         */
        List<PayTran> payTrans;

        /**
         * 子订单信息
         */
        List<OrdSubInfo> ordSubInfos;

        /**
         * String	关联主订单号	32
         */
        String mainOrderId;

        /**
         * Integer	主订单总数量	16
         */
        String mainOrderAmount;

        /**
         * 支付信息
         */
        @Data
        public class PayTran {
            /**
             * 支付方式编码	16
             */
            String payWay;

            /**
             * payTrans 支付方式名称	32
             */
            String payWayName;

            /**
             * payTrans 支付流水号	32
             */
            String payTransNo;

            /**
             * Integer	实付金额(单位:分)	16
             */
            Integer payMoney;

        }


        /**
         * 子订单信息
         */
        @Data
        public class OrdSubInfo {
            /**
             * 商品ID	16
             */
            Long gdsId;
            /**
             * 商品名称	256
             */
            String gdsName;
            /**
             * 单品ID	16
             */
            Long skuId;
            /**
             * 商品实付金额(单位:分)	16
             */
            Long realMoney;
            /**
             * 商品单价(单位:分)	16
             */
            Long baseMoney;
            /**
             * 商品订购数量	10
             */
            Long orderAmount;
        }
    }
}
