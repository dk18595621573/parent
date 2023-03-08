package com.cloud.component.cmb.bean.response.pay;

import com.cloud.component.cmb.bean.response.base.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 批量支付明细查询结果.
 *
 * @author nlsm
 * @date 2023-3-8 17:02:42
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class BatchPayInfoQueryRes extends BaseResponse {

    private static final long serialVersionUID = -2769489747413035726L;

    /**
     * 查询结果.
     */
    private List<BatchPaymentInfoRes> bb1qrybdz1;

    /**
     * 批次明细.
     */
    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class BatchPaymentInfoRes extends PaymentInfoRes {
        private static final long serialVersionUID = -9256613992060866L;

        /**
         * 批次编号.
         */
        private String bthNbr;

        /**
         * 失败原因.
         */
        private String errTxt;

        /**
         * 提示信息.
         */
        private String msgTxt;

        /**
         * 非居民收支申报预留金额.
         */
        private String rsvAmt;

        /**
         * 非居民收支申报相应金额.
         */
        private String trxAmt;

    }

}
