package com.cloud.rocketmq.message;

import cn.hutool.core.util.StrUtil;
import com.cloud.common.utils.StringUtils;
import com.cloud.rocketmq.base.BaseEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author nlsm
 * 添加消息param
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class NewsMessage extends BaseEvent implements Serializable {

    /** 订单主键 */
    private Long orderId;

    /** 产品型号+通用名 */
    private String productModel;

    /** 无仓单号 */
    private String orderCode;

    /** 成交企业 */
    private Long companyId;

    /** 成交用户 */
    private Long userId;

    /** 异常类型，暂时需求方不使用小程序处理申请 */
    private Integer type;

    /** 异常类型 status状态：7退货追单 12物流异常 9串码异常 {@link com.cloud.common.enums.OrderStatus} */
    private Integer status;

    /** 物流单号 */
    private String logisticsNo;

    /** 串码集合 */
    private String[] imei;

    /** 异常时间 */
    private Date unusualTime;

    /** 异常说明 */
    private String remark;

    @Override
    public String keys() {
        String keys = super.keys();
        if (StringUtils.isBlank(keys)) {
            return StrUtil.format("{}-{}-{}-{}", this.orderId, this.companyId, this.userId, status);
        }
        return keys;
    }
}
