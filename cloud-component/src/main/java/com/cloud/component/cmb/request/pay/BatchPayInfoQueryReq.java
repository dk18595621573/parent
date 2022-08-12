package com.cloud.component.cmb.request.pay;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 批量支付明细查询参数
 */
@Data
@Accessors(chain = true)
public class BatchPayInfoQueryReq implements Serializable {

    /**
     * 查询条件（单记录）
     */
    private List<BatchPayInfoQuery> bb1qrybdy1;

    /**
     * 查询条件
     */
    @Data
    @Accessors(chain = true)
    public class BatchPayInfoQuery {
        /**
         * 批次编号
         */
        private String bthNbr;

        /**
         * 请求状态(为空时不控制，查全部； OPR 接收中 NTE 待处理 FIN 经办受理完成)
         */
        private String autStr;

        /**
         * 处理结果(为空时不控制，查全部； F 失败 S 成功)
         */
        private String rtnStr;

        /**
         * 续传键值
         */
        private String ctnKey;
    }
}
