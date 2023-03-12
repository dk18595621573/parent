package com.cloud.component.cmb.bean.request.pay;

import com.cloud.component.cmb.bean.request.BaseCmbRequest;
import com.cloud.component.cmb.bean.request.common.PaymentInfo;
import com.cloud.component.cmb.bean.response.pay.SinglePayRes;
import com.cloud.component.cmb.consts.FunCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 单笔支付参数.
 *
 * @author nlsm
 * @date 2023-3-6 20:28:38
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SinglePayReq extends BaseCmbRequest<SinglePayRes> implements Serializable {

    private static final long serialVersionUID = 1557480866534483200L;

    /**
     * 调用企业信息.
     */
    private List<ModeInfo> bb1paybmx1;

    /**
     * 支付信息.
     */
    private List<PaymentInfo> bb1payopx1;

    @Override
    public String getFuncode() {
        return FunCodeEnum.Pay.BB1PAYOP.name();
    }

    /**
     * 模式信息.
     */
    @Data
    @Accessors(chain = true)
    public static class ModeInfo implements Serializable {

        private static final long serialVersionUID = -562606559508672463L;

        /**
         * 业务模式（模式编号）.
         */
        private String busMod;

        /**
         * 业务类型（业务代码）.
         */
        private String busCod;

    }

}
