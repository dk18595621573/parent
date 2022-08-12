package com.cloud.component.cmb.request.pay;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 批量支付参数
 */
@Data
@Accessors(chain = true)
public class BatchPayReq implements Serializable {

    /**
     * 汇总信息
     */
    private List<BatchPay> bb1bmdbhx1;

    /**
     * 支付信息集合
     */
    private List<PaymentInfoReq> bb1paybhx1;

    @Data
    @Accessors(chain = true)
    public class BatchPay {
        /**
         * 批次编号
         */
        private String bthNbr;
        /**
         * 业务类型（业务代码）
         */
        private String busCod;
        /**
         * 业务模式（模式编号）
         */
        private String busMod;
        /**
         * 总明细笔数
         */
        private String dtlNbr;
        /**
         * 续传标志(大于1000笔需要做续传 Y N)
         */
        private String ctnFlg;
        /**
         * 续传状态(当续传标志 =Y时必输；  1 批次开始 2 续传中 3 批次结束)
         */
        private String ctnSts;
    }
}
