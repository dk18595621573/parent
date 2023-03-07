package com.cloud.component.cmb.bean.request.pay;

import com.cloud.component.cmb.bean.request.BaseCmbRequest;
import com.cloud.component.cmb.bean.response.pay.BatchPayQueryRes;
import com.cloud.component.cmb.consts.FunCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 批量支付批次查询参数
 *
 * @author nlsm
 * @date 2023-3-6 19:55:09
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class BatchPayQueryReq extends BaseCmbRequest<BatchPayQueryRes> implements Serializable {

    private static final long serialVersionUID = 5153884885939364587L;

    /**
     * 查询条件（单记录）.
     */
    private List<BatchPayQuery> bb1qrybtx1;

    @Override
    public String getFuncode() {
        return FunCodeEnum.Pay.BB1QRYBT.name();
    }

    /**
     * 查询条件.
     */
    @Data
    @Accessors(chain = true)
    public static class BatchPayQuery implements Serializable {
        private static final long serialVersionUID = 2108286167901223323L;

        /**
         * 起始日期（当前日期-1个月）.
         */
        private String begDat;
        /**
         * 结束日期（默认当前日期）.
         */
        private String endDat;
        /**
         * 请求状态(为空时不控制，查全部； OPR 接收中 NTE 待处理 FIN 经办受理完成).
         */
        private String autStr;
        /**
         * 处理结果(为空时不控制，查全部； F 失败 S 成功).
         */
        private String rtnStr;
    }

}
