package com.cloud.webmvc;

import com.cloud.webmvc.aspect.LogAspect;
import com.cloud.webmvc.aspect.RateLimiterAspect;
import com.cloud.webmvc.config.ApplicationConfig;
import com.cloud.webmvc.config.CaptchaConfig;
import com.cloud.webmvc.config.ResourcesConfig;
import com.cloud.webmvc.config.SecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * webmvc自动装配.
 *
 * @author zenghao
 * @date 2022/7/5
 */
@Configuration
@Import({ApplicationConfig.class, CaptchaConfig.class, ResourcesConfig.class, SecurityConfig.class, LogAspect.class, RateLimiterAspect.class})
public class WebMvcAutoConfiguration {
}
