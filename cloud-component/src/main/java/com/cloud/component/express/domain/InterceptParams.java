package com.cloud.component.express.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 顺丰拦截接口入参
 * @author nlsm
 */
@Data
public class InterceptParams implements Serializable {

    /** 发起方 */
    public interface Initiator{
        /** 寄方 */
        String SENDER = "1";
        /** 收方 */
        String RECEIVE = "2";
        /** 第三方 */
        String THIRD_PARTY = "3";
    }

    /** 运单号，必输 */
    private String wayBillNO;

    /** 快递公司编码，必输 */
    private String shipperCode;

    /** 1 转寄 2 退回 3 优派 4 再派 5 改自取（改派-其他自取点取件） 6 改派送（上门派送）
     *  7 更改派送时间 8 修改收件人信息 9 更改付款方式 10 修改代收货款 12 作废，必输
     *  {@link com.cloud.component.express.consts.InterceptType}  */
    private String serviceCode;

    /** 发起方 1（寄方）2（收方）3（第三方），必输 */
    private String role;

    /** 付款方式 1 （寄付） 2 （到付） 3（转第三方月结）4（寄付月结），必输
     * {@link com.cloud.component.express.consts.LogisticsPayType}*/
    private String payMode;

    /** 月结卡号 如果是月结账户，必填字段。 付款方式为月结时，必填字段。 修改代收货款，必填字段 */
    private String monthlyCardNo;

    /** 产品类型 */
    private String productType;

    /** 代收货款金额 */
    private BigDecimal codAmount;

    /** 派送日期，形如“2018-05-02” */
    private String deliverDate;

    /** 派送最早时间，形如“09:00” */
    private String deliverTimeMin;

    /** 派送最晚时间，形如“12:00” */
    private String deliverTimeMax;

    /** 自取点 1-合作点 2-快递柜 3-网点 4 中转场 */
    private String selfPickPoint;

    /** 新目的地 改联系人业务请携带姓名及手机号 转寄、退回业务必须携带，撤销转寄、退回不携带 改自取必须携带 */
    private NewDestAddress newDestAddress;

    /**
     * 新目的地
     */
    @Data
    public static class NewDestAddress{
        /** 省，如广东省，必输 */
        private String province;

        /** 市，如深圳市，必输 */
        private String city;

        /** 区，如南山区，必输 */
        private String county;

        /** 详细地址，不包含省市区，如粤海街道高新区 1C 栋二楼，必输 */
        private String address;

        /** 联系人姓名，如陈新学 */
        private String contact;

        /** 电话，如 18372266052 */
        private String phone;
    }
}
