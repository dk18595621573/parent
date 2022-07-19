package com.cloud.component.chinapay.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 银联返回数据.
 *
 * @author zenghao
 * @date 2022/6/20
 */
@Data
public class Response implements Serializable {

    /**
     * ChinaPay 分配的入网商户号
     * 必填
     */
    private String merNo;

    /**
     * ChinaPay 分配的入网机构号 15 位数字
     * 当为“机构”时，必填。 其它情况时，不填。
     */
    private String instId;

    /**
     * Base64 后的响应 报文体
     * 必填
     */
    private String respData;

    /**
     * 签名值
     * 必填
     */
    private String signature;

    /**
     * 请求ip
     */
    private String accessIp;
}
