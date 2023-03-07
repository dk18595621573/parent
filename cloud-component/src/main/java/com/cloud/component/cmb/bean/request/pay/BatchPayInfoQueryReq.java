package com.cloud.component.cmb.bean.request.pay;

import com.cloud.component.cmb.bean.request.BaseCmbRequest;
import com.cloud.component.cmb.bean.response.pay.BatchPayInfoQueryRes;
import com.cloud.component.cmb.consts.FunCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 批量支付明细查询参数.
 *
 * @author nlsm
 * @date 2023-3-6 19:52:42
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class BatchPayInfoQueryReq extends BaseCmbRequest<BatchPayInfoQueryRes> implements Serializable {

    private static final long serialVersionUID = 6681150074895772548L;

    /**
     * 查询条件（单记录）
     */
    private List<BatchPayInfoQuery> bb1qrybdy1;

    @Override
    public String getFuncode() {
        return FunCodeEnum.Pay.BB1QRYBD.name();
    }

    /**
     * 查询条件.
     */
    @Data
    @Accessors(chain = true)
    public static class BatchPayInfoQuery implements Serializable{

        private static final long serialVersionUID = -2335813700771230370L;

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
