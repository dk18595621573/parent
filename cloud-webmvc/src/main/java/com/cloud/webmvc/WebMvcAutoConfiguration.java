package com.cloud.webmvc;

import com.cloud.webmvc.properties.SystemProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * webmvc自动装配.
 *
 * @author zenghao
 * @date 2022/7/5
 */
@Configuration
@EnableConfigurationProperties(SystemProperties.class)
public class WebMvcAutoConfiguration {

}
