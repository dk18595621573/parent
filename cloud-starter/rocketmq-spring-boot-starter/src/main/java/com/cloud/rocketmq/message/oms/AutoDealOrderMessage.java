package com.cloud.rocketmq.message.oms;

import cn.hutool.core.util.StrUtil;
import com.cloud.common.utils.StringUtils;
import com.cloud.rocketmq.base.BaseEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 外部订单自动成交消息.
 *
 * @author Luo
 * @date 2023-05-11 13:33
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class AutoDealOrderMessage extends BaseEvent {

    private static final long serialVersionUID = -1258684585578950985L;

    public static final String KEY = "AUTO-DEAL";

    /**
     * 订单Id.
     */
    private Long orderId;

    @Override
    public String keys() {
        String keys = super.keys;
        if (StringUtils.isNotBlank(keys)) {
            return StrUtil.format("{}-{}-{}", KEY, keys, getOrderId());
        }
        return StrUtil.format("{}-{}", KEY, getOrderId());
    }

}
