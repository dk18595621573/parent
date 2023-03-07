package com.cloud.component.cmb.bean.response.pay;

import com.cloud.component.cmb.bean.response.base.BaseResponse;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 批量支付明细查询结果
 */
@Data
@Accessors(chain = true)
public class BatchPayInfoQueryRes extends BaseResponse {

    /**
     * 查询结果
     */
    private List<BatchPaymentInfoRes> bb1qrybdz1;

    @Data
    @Accessors(chain = true)
    public class BatchPaymentInfoRes extends PaymentInfoRes {
        /**
         * 批次编号
         */
        private String bthNbr;
        /**
         * 失败原因
         */
        private String errTxt;
        /**
         * 提示信息
         */
        private String msgTxt;
        /**
         * 非居民收支申报预留金额
         */
        private String rsvAmt;
        /**
         * 非居民收支申报相应金额
         */
        private String trxAmt;
    }

}
