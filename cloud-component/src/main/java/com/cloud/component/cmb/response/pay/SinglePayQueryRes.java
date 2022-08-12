package com.cloud.component.cmb.response.pay;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 支付业务查询结果
 */
@Data
@Accessors(chain = true)
public class SinglePayQueryRes implements Serializable {

    /**
     *  查询结果
     */
    private List<PaymentInfoRes> bb1payqrz1;

}
