package com.cloud.component.properties;

import com.cloud.common.constant.Constants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author nlsm
 */
@Data
@ConfigurationProperties(prefix = HolidayProperties.HOLIDAY_PREFIX)
public class HolidayProperties {
    public static final String HOLIDAY_PREFIX = Constants.CONFIG_PREFIX + "holiday";

    private String timorUrl = "https://timor.tech/api/holiday/year/";

    private String hubsUrl = "https://api.apihubs.cn/holiday/get";
}
