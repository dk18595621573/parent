package com.cloud.component.cmb.response.pay;

import com.cloud.component.cmb.response.BaseResponse;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 批量支付批次查询结果
 */
@Data
@Accessors(chain = true)
public class BatchPayQueryRes extends BaseResponse {
    private List<BatchPayQuery> bb1qrybtz1;


    @Data
    @Accessors(chain = true)
    public class BatchPayQuery {

        /**
         * 失败描述
         */
        private String errTxt;

        /**
         * 批次编号
         */
        private String bthNbr;

        /**
         * 业务类型
         */
        private String busCod;

        /**
         * 业务模式
         */
        private String busMod;

        /**
         * 批次总金额
         */
        private String dtlAmt;

        /**
         * 批次总笔数
         */
        private String dtlNum;

        /**
         * 提交成功总金额
         */
        private String sucAmt;

        /**
         * 提交成功总笔数
         */
        private String sucNum;

        /**
         * 经办日期
         */
        private String trsDat;

        /**
         * 经办时间
         */
        private String trsTim;

        /**
         * 请求状态(NTE:待处理   FIN:完成   OPR:数据接收中)
         */
        private String reqSts;

        /**
         * 业务处理结果（F 失败 S 成功）
         */
        private String rtnFlg;

    }
}
