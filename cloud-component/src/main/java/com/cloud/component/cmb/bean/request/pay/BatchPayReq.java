package com.cloud.component.cmb.bean.request.pay;

import com.cloud.component.cmb.bean.request.BaseCmbRequest;
import com.cloud.component.cmb.bean.request.common.PaymentInfo;
import com.cloud.component.cmb.bean.response.pay.BatchPayRes;
import com.cloud.component.cmb.consts.FunCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 批量支付参数.
 *
 * @author nlsm
 * @date 2023-3-6 20:18:14
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class BatchPayReq extends BaseCmbRequest<BatchPayRes> implements Serializable {

    private static final long serialVersionUID = -44028294741260666L;

    /**
     * 汇总信息.
     */
    private List<BatchPay> bb1bmdbhx1;

    /**
     * 支付信息集合.
     */
    private List<PaymentInfo> bb1paybhx1;

    @Override
    public String getFuncode() {
        return FunCodeEnum.Pay.BB1PAYBH.name();
    }

    /**
     * 批量支付.
     */
    @Data
    @Accessors(chain = true)
    public static class BatchPay implements Serializable{

        private static final long serialVersionUID = 1309505287962758697L;

        /**
         * 批次编号.
         */
        private String bthNbr;

        /**
         * 业务类型（业务代码）.
         */
        private String busCod;

        /**
         * 业务模式（模式编号）.
         */
        private String busMod;

        /**
         * 总明细笔数.
         */
        private String dtlNbr;

        /**
         * 续传标志(大于1000笔需要做续传 Y N).
         */
        private String ctnFlg;

        /**
         * 续传状态(当续传标志 =Y时必输；  1 批次开始 2 续传中 3 批次结束).
         */
        private String ctnSts;

    }
}
