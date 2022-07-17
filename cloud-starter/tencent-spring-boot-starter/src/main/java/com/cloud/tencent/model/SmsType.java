package com.cloud.tencent.model;

/**
 * 短信类型.
 *
 * @author zenghao
 * @date 2022/7/17
 */
public interface SmsType {

    /**
     * 短信签名
     * @return 短信签名
     */
    String getSign();

    /**
     * 短信模板code
     * @return 短信模板code
     */
    String getTemplateCode();
}
