package com.cloud.tencent.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * OCR 配置.
 *
 * @author zenghao
 * @date 2022/8/2
 */
@Data
@ConfigurationProperties(prefix = OcrProperties.OCR_PREFIX)
public class OcrProperties {
    public static final String OCR_PREFIX = TencentProperties.TENCENT_PREFIX + ".ocr";

    /**
     * 设置区域.
     */
    private String region;
}
