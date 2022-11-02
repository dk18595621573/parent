package com.cloud.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统配置参数.
 *
 * @author zenghao
 * @date 2022/6/28
 */
@Getter
@AllArgsConstructor
public enum SystemParams {
    /**
     * 系统初始密码 默认：123456
     */
    INIT_PASSWORD("sys.user.initPassword", "123456"),
    /**
     * 验证码开关 默认:打开验证码
     */
    CAPTCHA_ENABLE("sys.account.captchaOnOff", "true"),
    /**
     * 注册开关 默认：不允许注册
     */
    REGISTER_ENABLE("sys.account.registerUser", "false"),
    /**
     * 模拟支付开关 默认：不允许模拟支付
     */
    SIMULATE_CONFIG("pay.channel.simulate", "false"),
    /**
     * 默认代发仓编码 默认：醒市测试仓
     */
    DEFAULT_WAREHOUSE("sys.warehouse.code", "xscec"),
    /**
     * 是否可以注册为需求方 默认：不支持注册需求方
     */
    DEMAND_REGISTER("user.company.demand", "false"),
    /**
     * 华盛代发仓编码 默认：HSDFCCC
     */
    HUASHENG_WAREHOUSE("huasheng.warehouse.code", "HSDFCCC"),
    /**
     * 华盛供应商名称 默认：联通华盛通信有限公司北京电子商务分公司
     */
    HUASHENG_SUPPLIER_NAME("huasheng.supplier.name", "联通华盛通信有限公司北京电子商务分公司"),
    /**
     *  智能报价成本价范围 默认：已开通智能报价企业自身的成交价
     */
    INTELLIGENCE_QUOTE_TRADEPRICE_RANGE("intelligence.quote.tradeprice.range", "false"),
    /**
     * 补全地址自动报价的开始时间
     */
    ADDRESS_COMPLETED_STARTHOUR("address.completed.starthour", "9"),
    /**
     * 补全地址自动报价的结束时间
     */
    ADDRESS_COMPLETED_ENDHOUR("address.completed.endhour", "18"),
    /**
     * 发货时效自动报价的时间单位-前
     * 0:当天  1:明天  2:后天
     */
    DELIVERY_TIME_FRONT("delivery.time.front", "1"),
    /**
     * 发货时效自动报价的时间单位-后
     * 0:当天  1:明天  2:后天
     */
    DELIVERY_TIME_AFTER("delivery.time.after", "2"),

    ;
    
    private final String code;
    
    private final String defaultValue;
    
}
