package com.cloud.rocketmq.message.oms;

import cn.hutool.core.util.StrUtil;
import com.cloud.common.utils.StringUtils;
import com.cloud.rocketmq.base.BaseEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 外部订单发货消息.
 *
 * @author Luo
 * @date 2023-05-11 13:33
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class DeliverGoodsMessage extends BaseEvent {

    private static final long serialVersionUID = -1258684585578950985L;

    public static final String KEY = "DELIVER-GOODS";

    /**
     * 订单类型.
     */
    private String belongType;

    /**
     * 数据.
     */
    private String data;

    @Override
    public String keys() {
        String keys = super.keys;
        if (StringUtils.isNotBlank(keys)) {
            return StrUtil.format("{}-{}-{}", KEY, keys);
        }
        return StrUtil.format("{}-{}", KEY, System.currentTimeMillis());
    }

}
