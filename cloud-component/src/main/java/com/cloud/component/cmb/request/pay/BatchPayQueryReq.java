package com.cloud.component.cmb.request.pay;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 批量支付批次查询参数
 */
@Data
@Accessors(chain = true)
public class BatchPayQueryReq implements Serializable {

    /**
     * 查询条件（单记录）
     */
    private List<BatchPayQuery> bb1qrybtx1;

    /**
     * 查询条件
     */
    @Data
    @Accessors(chain = true)
    public class BatchPayQuery {
        /**
         * 起始日期（当前日期-1个月）
         */
        private String begDat;
        /**
         * 结束日期（默认当前日期）
         */
        private String endDat;
        /**
         * 请求状态(为空时不控制，查全部； OPR 接收中 NTE 待处理 FIN 经办受理完成)
         */
        private String autStr;
        /**
         * 处理结果(为空时不控制，查全部； F 失败 S 成功)
         */
        private String rtnStr;
    }
}
