package com.cloud.component.cmb.request.pay;

import com.cloud.component.cmb.request.account.AccountReq;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 单笔支付参数
 */
@Data
@Accessors(chain = true)
public class SinglePayReq implements Serializable {

    /**
     * 调用企业信息
     */
    private List<AccountReq> bb1paybmx1;

    /**
     * 支付信息
     */
    private List<PaymentInfoReq> bb1payopx1;
}
