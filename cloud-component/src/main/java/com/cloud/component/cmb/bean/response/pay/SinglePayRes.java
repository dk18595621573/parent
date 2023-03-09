package com.cloud.component.cmb.bean.response.pay;

import com.cloud.component.cmb.bean.response.base.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 单笔支付结果.
 *
 * @author nlsm
 * @date 2023-3-9 13:15:27
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SinglePayRes extends BaseResponse {

    private static final long serialVersionUID = -4184741944379643261L;

    /**
     * 结果.
     */
    private List<SinglePay> bb1payopz1;

    /**
     * 单笔支付.
     */
    @Data
    @Accessors(chain = true)
    public static class SinglePay implements Serializable {

        private static final long serialVersionUID = 2959021620713957749L;

        /**
         * 返回码（SUC0000：成功）.
         */
        private String errCod;

        /**
         * 错误文本.
         */
        private String errTxt;

        /**
         * 流程实例号.
         */
        private String reqNbr;

        /**
         * 请求状态(AUT:等待审批  NTE:终审完毕  BNK，WRF:银行处理中   FIN:完成   OPR:数据接收中).
         */
        private String reqSts;

        /**
         * 提示文本.
         */
        private String msgTxt;

        /**
         * 业务处理结果.
         */
        private String rtnFlg;

        /**
         * 事件实例号.
         */
        private String evtIst;

        /**
         * 待处理操作序列.
         */
        private String oprSqn;

    }
    
}
