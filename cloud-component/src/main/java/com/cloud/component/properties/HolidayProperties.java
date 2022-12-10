package com.cloud.component.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author nlsm
 */
@Data
@ConfigurationProperties(prefix = HolidayProperties.HOLIDAY_PREFIX)
public class HolidayProperties {
    public static final String HOLIDAY_PREFIX = "cloud.holiday";


}
