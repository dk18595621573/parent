package com.cloud.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 企微机器人类型.
 *  必须和企微机器人配置中的key对应，具体见【cloud.bot.configs】配置项
 *
 * @author zenghao
 * @date 2023/3/5
 */
@Getter
@AllArgsConstructor
public enum BotTypeEnums {

    /**
     * 普通订阅
     */
    SUBSCRIBE("subscribe"),

    /**
     * 品牌商特殊处理
     */
    BRAND("brand");

    private final String type;
}
