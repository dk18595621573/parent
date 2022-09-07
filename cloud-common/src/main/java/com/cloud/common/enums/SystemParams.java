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
     * 华盛代发仓编码 默认：hswc
     */
    HUASHENG_WAREHOUSE("huasheng.warehouse.code", "hswc"),
    /**
     * 华盛供应商名称 默认：华盛
     */
    HUASHENG_SUPPLIER_NAME("huasheng.supplier.name", "华盛"),
    ;
    
    private final String code;
    
    private final String defaultValue;
    
}
