package com.cloud.component.cmb.bean.response.pay;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 支付业务查询结果.
 *
 * @author nlsm
 * @date 2023-3-9 13:14:53
 */
@Data
@Accessors(chain = true)
public class SinglePayQueryRes implements Serializable {

    private static final long serialVersionUID = -6229480965183472518L;

    /**
     * 查询结果.
     */
    private List<PaymentInfoRes> bb1payqrz1;

}
