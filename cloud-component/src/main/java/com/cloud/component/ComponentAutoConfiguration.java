package com.cloud.component;

import com.cloud.component.config.*;
import org.springframework.context.annotation.Import;

/**
 * 组件库自动装载.
 *
 * @author zenghao
 * @date 2022/7/19
 */
@Import(value = {FadadaConfig.class, ChinaPayConfig.class, ExpressConfig.class,
    SerialConfig.class, CMBConfig.class, WxworkBotConfig.class,
    HSConfig.class, HolidayConfig.class, YaBaoConfig.class, SfConfig.class})
public class ComponentAutoConfiguration {
}
