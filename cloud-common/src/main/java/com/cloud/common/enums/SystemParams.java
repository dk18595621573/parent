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
     * 系统初始密码
     */
    INIT_PASSWORD("sys.user.initPassword", "123456"),
    /**
     * 验证码开关
     */
    CAPTCHA_ENABLE("sys.account.captchaOnOff", "true"),
    /**
     * 注册开关
     */
    REGISTER_ENABLE("sys.account.registerUser", "false"),
    /**
     * 模拟支付开关
     */
    SIMULATE_CONFIG("pay.channel.simulate", "false"),
    /**
     * 默认代发仓编码
     */
    DEFAULT_WAREHOUSE("sys.warehouse.code", "xscec"),
    /**
     * 是否可以注册为需求方
     */
    DEMAND_REGISTER("user.company.demand", "false"),
    ;
    
    private final String code;
    
    private final String defaultValue;
    
}
