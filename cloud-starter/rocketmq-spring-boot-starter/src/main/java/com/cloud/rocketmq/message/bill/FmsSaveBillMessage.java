package com.cloud.rocketmq.message.bill;

import cn.hutool.core.util.StrUtil;
import com.cloud.rocketmq.base.BaseEvent;
import com.cloud.rocketmq.consts.FmsMQConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Objects;

/**
 * 账单生成消息体
 * @author:zhanll
 * @date:2023-04-21 16:32
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class FmsSaveBillMessage extends BaseEvent {

    /** 订单id */
    private Long orderId;

    /**
     * 签收时间
     */
    private Date signTime;

    /**
     * 账单备注
     */
    private String remark;

    @Override
    public String keys() {
        String keys = super.keys;
        if (Objects.nonNull(keys)) {
            return StrUtil.format("{}-{}-{}", keys, FmsMQConstants.TAGS_SAVE_BILL, this.orderId);
        }
        return StrUtil.format("{}-{}-{}", FmsMQConstants.TAGS_SAVE_BILL, System.currentTimeMillis(), this.orderId);
    }

    @Override
    public String tags() {
        String tags = super.tags;
        if (Objects.nonNull(tags)) {
            StrUtil.format("{}-{}-{}", tags, FmsMQConstants.MODEL_NAME, FmsMQConstants.TAGS_SAVE_BILL);
        }
        return StrUtil.format("{}-{}", FmsMQConstants.MODEL_NAME, FmsMQConstants.TAGS_SAVE_BILL);
    }
}
