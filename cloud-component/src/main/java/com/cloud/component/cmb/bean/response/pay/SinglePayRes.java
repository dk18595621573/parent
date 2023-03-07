package com.cloud.component.cmb.bean.response.pay;

import com.cloud.component.cmb.bean.response.base.BaseResponse;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 *  单笔支付结果
 */
@Data
@Accessors(chain = true)
public class SinglePayRes extends BaseResponse {
    private List<SinglePay> bb1payopz1;


    @Data
    @Accessors(chain = true)
    public class SinglePay{
        /**
         *  返回码（SUC0000：成功）
         */
        private String errCod;

        /**
         *  错误文本
         */
        private String errTxt;

        /**
         *  流程实例号
         */
        private String reqNbr;

        /**
         *  请求状态(AUT:等待审批  NTE:终审完毕  BNK，WRF:银行处理中   FIN:完成   OPR:数据接收中)
         */
        private String reqSts;

        /**
         *  提示文本
         */
        private String msgTxt;

        /**
         *  业务处理结果
         */
        private String rtnFlg;

        /**
         *  事件实例号
         */
        private String evtIst;

        /**
         *  待处理操作序列
         */
        private String oprSqn;
    }
}
