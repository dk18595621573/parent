package com.cloud.component.cmb.bean.request.pay;

import com.cloud.component.cmb.bean.request.BaseCmbRequest;
import com.cloud.component.cmb.bean.response.pay.PaymentInfoRes;
import com.cloud.component.cmb.consts.FunCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 支付业务查询参数.
 *
 * @author nlsm
 * @date 2023-3-6 20:26:03
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SinglePayQueryReq extends BaseCmbRequest<PaymentInfoRes> implements Serializable {

    private static final long serialVersionUID = -4536982511421671198L;

    /**
     * 查询条件（单记录）.
     */
    private List<SinglePayQuery> bb1payqrx1;

    @Override
    public String getFuncode() {
        return FunCodeEnum.Pay.BB1PAYQR.name();
    }

    /**
     * 支付信息.
     */
    @Data
    @Accessors(chain = true)
    public static class SinglePayQuery implements Serializable{
        private static final long serialVersionUID = 3374239751217918314L;

        /**
         * 业务类型（N02030）
         */
        private String busCod;

        /**
         * 业务参考号
         */
        private String yurRef;

    }

}
