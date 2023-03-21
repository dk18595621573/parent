package com.cloud.component;

import com.cloud.component.config.CMBConfig;
import com.cloud.component.config.ChinaPayConfig;
import com.cloud.component.config.ECSSConfig;
import com.cloud.component.config.ExpressConfig;
import com.cloud.component.config.FadadaConfig;
import com.cloud.component.config.HSConfig;
import com.cloud.component.config.HolidayConfig;
import com.cloud.component.config.SerialConfig;
import com.cloud.component.config.SfConfig;
import com.cloud.component.config.WxworkBotConfig;
import com.cloud.component.config.YaBaoConfig;
import org.springframework.context.annotation.Import;

/**
 * 组件库自动装载.
 *
 * @author zenghao
 * @date 2022/7/19
 */
@Import(value = {FadadaConfig.class, ChinaPayConfig.class, ExpressConfig.class,
    SerialConfig.class, CMBConfig.class, WxworkBotConfig.class,
    HSConfig.class, HolidayConfig.class, YaBaoConfig.class, SfConfig.class, ECSSConfig.class})
public class ComponentAutoConfiguration {
}
