package com.cloud.common.core.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 支付结果推送.
 *
 * @author zenghao
 * @date 2022/7/14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayResult implements Serializable {

    private Boolean success;
}
