package com.cloud.component;

import com.cloud.component.config.ChinaPayConfig;
import com.cloud.component.config.ExpressConfig;
import com.cloud.component.config.FadadaConfig;
import com.cloud.component.config.SerialConfig;
import org.springframework.context.annotation.Import;

/**
 * 组件库自动装载.
 *
 * @author zenghao
 * @date 2022/7/19
 */
@Import(value = {FadadaConfig.class, ChinaPayConfig.class, ExpressConfig.class, SerialConfig.class})
public class ComponentAutoConfiguration {
}
