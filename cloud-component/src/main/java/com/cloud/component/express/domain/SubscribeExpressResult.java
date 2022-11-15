package com.cloud.component.express.domain;


import lombok.Data;

/**
 * 订阅快递100响应结果
 *
 * @author zhangshu
 * @link <a href="https://api.kuaidi100.com/document/5f0ffa8f2977d50a94e1023c">https://api.kuaidi100.com/document/5f0ffa8f2977d50a94e1023c</a>
 */
@Data
public class SubscribeExpressResult {

    /**
     * true表示成功，false表示失败
     */
    private Boolean result;

    /**
     * 状态编号
     */
    private String returnCode;

    /**
     * 描述信息
     */
    private String message;
}
