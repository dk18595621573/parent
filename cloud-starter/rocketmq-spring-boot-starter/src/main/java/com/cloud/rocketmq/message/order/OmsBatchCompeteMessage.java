package com.cloud.rocketmq.message.order;

import com.cloud.rocketmq.base.BaseEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 小程序一键抢单消息体
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class OmsBatchCompeteMessage extends BaseEvent {

    //埋点类型 1:一键抢单 2:一键抢单关闭 3:推荐页抢单
    private Integer type;

    //订单id集合
    private String[] orderId;

    //公司id
    private Long companyId;

    //用户id
    private Long userId;

    //数量
    private Integer quantity;

    //成功数量
    private Integer successQuantity;

    //订单Id
    private Integer id;
}
