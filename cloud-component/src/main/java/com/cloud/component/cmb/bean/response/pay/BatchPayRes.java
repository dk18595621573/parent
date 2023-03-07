package com.cloud.component.cmb.bean.response.pay;

import com.cloud.component.cmb.bean.response.base.BaseResponse;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 *  批量支付结果
 */
@Data
@Accessors(chain = true)
public class BatchPayRes extends BaseResponse {
    private List<BatchPay> bb1paybhz1;


    @Data
    @Accessors(chain = true)
    public class BatchPay{
        /**
         *  返回码（SUC0000：成功）
         */
        private String errCod;

        /**
         *  错误文本
         */
        private String errTxt;

        /**
         *  提示文本
         */
        private String msgTxt;

        /**
         *  批次编号
         */
        private String bthNbr;

        /**
         *  请求状态(AUT:等待审批  NTE:终审完毕  BNK，WRF:银行处理中   FIN:完成   OPR:数据接收中)
         */
        private String reqSts;

        /**
         *  业务处理结果
         */
        private String rtnFlg;

    }
}
