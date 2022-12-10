package com.cloud.component.config;

import com.cloud.component.holiday.impl.ApiHubsHoliday;
import com.cloud.component.holiday.impl.TimorHoliday;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author nlsm
 */
@Configuration
public class HolidayConfig {

    @Bean
    public ApiHubsHoliday apiHubsHoliday(){
        return new ApiHubsHoliday();
    }

    @Bean
    public TimorHoliday timorHoliday(){
        return new TimorHoliday();
    }
}
