package com.cloud.component.cmb.bean.response.pay;

import com.cloud.component.cmb.bean.response.base.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 批量支付结果.
 *
 * @author nlsm
 * @date 2023-3-9 13:12:58
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class BatchPayRes extends BaseResponse {
    private static final long serialVersionUID = -4644999927078649034L;

    /**
     * 结果.
     */
    private List<BatchPay> bb1paybhz1;

    /**
     * 批量支付.
     */
    @Data
    @Accessors(chain = true)
    public static class BatchPay implements Serializable {

        private static final long serialVersionUID = -705909524916545388L;

        /**
         * 返回码（SUC0000：成功）.
         */
        private String errCod;

        /**
         * 错误文本.
         */
        private String errTxt;

        /**
         * 提示文本.
         */
        private String msgTxt;

        /**
         * 批次编号.
         */
        private String bthNbr;

        /**
         * 请求状态(AUT:等待审批  NTE:终审完毕  BNK，WRF:银行处理中   FIN:完成   OPR:数据接收中).
         */
        private String reqSts;

        /**
         * 业务处理结果.
         */
        private String rtnFlg;

    }

}
