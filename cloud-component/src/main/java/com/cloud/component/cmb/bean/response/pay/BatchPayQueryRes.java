package com.cloud.component.cmb.bean.response.pay;

import com.cloud.component.cmb.bean.response.base.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 批量支付批次查询结果.
 *
 * @author nlsm
 * @date 2023-3-9 13:11:34
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class BatchPayQueryRes extends BaseResponse {

    private static final long serialVersionUID = 5378951055862041107L;

    /**
     * 结果.
     */
    private List<BatchPayQuery> bb1qrybtz1;

    /**
     * 批量支付查询.
     */
    @Data
    @Accessors(chain = true)
    public static class BatchPayQuery implements Serializable {

        private static final long serialVersionUID = 8641066351908005953L;

        /**
         * 失败描述.
         */
        private String errTxt;

        /**
         * 批次编号.
         */
        private String bthNbr;

        /**
         * 业务类型.
         */
        private String busCod;

        /**
         * 业务模式.
         */
        private String busMod;

        /**
         * 批次总金额.
         */
        private String dtlAmt;

        /**
         * 批次总笔数.
         */
        private String dtlNum;

        /**
         * 提交成功总金额.
         */
        private String sucAmt;

        /**
         * 提交成功总笔数.
         */
        private String sucNum;

        /**
         * 经办日期.
         */
        private String trsDat;

        /**
         * 经办时间.
         */
        private String trsTim;

        /**
         * 请求状态(NTE:待处理   FIN:完成   OPR:数据接收中).
         */
        private String reqSts;

        /**
         * 业务处理结果（F 失败 S 成功）.
         */
        private String rtnFlg;

    }

}
