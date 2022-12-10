package com.cloud.component.config;

import com.cloud.component.holiday.impl.ApiHubsHoliday;
import com.cloud.component.holiday.impl.TimorHoliday;
import com.cloud.component.properties.HolidayProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author nlsm
 */
@Configuration
@EnableConfigurationProperties(value = HolidayProperties.class)
@ConditionalOnProperty(prefix = HolidayProperties.HOLIDAY_PREFIX, name = "enabled", havingValue = "true")
public class HolidayConfig {

    @Bean
    @RefreshScope
    public ApiHubsHoliday apiHubsHoliday(HolidayProperties holidayProperties){
        return new ApiHubsHoliday(holidayProperties);
    }

    @Bean
    @RefreshScope
    public TimorHoliday timorHoliday(HolidayProperties holidayProperties){
        return new TimorHoliday(holidayProperties);
    }
}
