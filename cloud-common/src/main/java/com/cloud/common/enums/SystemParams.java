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
     * 北京华盛
     */
    HUASHENG_BEIJING_SUPPLIER_NAME("huasheng.beijing.supplier.name", "北京华盛"),

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
    /**
     * 供应商列表tag统计缓存时间，缓存时间单位分钟,0表示不缓存
     */
    SUPPLIER_TAG_COUNT_CACHE("supplier.tag.count.cache","0"),
    /**
     * 需求方列表tag统计缓存时间，缓存时间单位分钟,0表示不缓存
     */
    DEMAND_TAG_COUNT_CACHE("demand.tag.count.cache","0"),
    /**
     * 一件代发屏蔽指定品牌和品类(0,不屏蔽; 1,屏蔽)
     */
    NO_SHOW_BRAND_CATEGORY("no.show.brand.category","0"),
    /**
     * web端交易市场展示的同省同sku的订单数
     */
    WEB_SHOW_NUM("web.show.num","2"),
    /**
     * 小程序端交易市场展示的同省同sku的订单数
     */
    WECHAT_SHOW_NUM("wechat.show.num","5"),
    /**
     * 交易市场展示数限制的品牌
     */
    DISPLAY_LIMITED_BRAND("display.limited.brand",""),
    /**
     * 小程序推荐列表随机数最小值.
     */
    APPLET_RECOMMEND_MIN_COUNT("applet.recommend.min.count", "5"),
    /**
     * 小程序推荐列表随机数最大值.
     */
    APPLET_RECOMMEND_MAX_COUNT("applet.recommend.max.count", "10")
    ;


    
    private final String code;
    
    private final String defaultValue;
    
}
