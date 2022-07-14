package com.cloud.common.core.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 支付结果推送.
 *
 * @author zenghao
 * @date 2022/7/14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayResult extends SseData {

    private Boolean success;
}
