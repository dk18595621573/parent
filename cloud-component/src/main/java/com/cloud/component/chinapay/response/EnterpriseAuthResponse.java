package com.cloud.component.chinapay.response;

import lombok.Data;

/**
 * 企业认证 响应体.
 *
 * @author zenghao
 * @date 2022/6/20
 */
@Data
public class EnterpriseAuthResponse extends BaseResponse {

    /**
     * 企业代码类型
     * 1：统一信用代码 3：工商注册号
     * 必填
     */
    private String keyType;
    /**
     * 企业代码
     * 根据<企业代码类型> 输入不同值 企 业 代 码 类 型 为 1 时，输入统一信用代 码，企业代码类型为 3 时，输入工商注册 号
     * 必填
     */
    private String key;
    /**
     * 敏感域
     * 必填
     */
    private String sensData;
    /**
     * 企业账户开户行
     * 必填
     */
    private String accountBank;
    /**
     * 企业开户行所在省
     * 非必填
     */
    private String accountProv;
    /**
     * 企业开户行所在地 区
     * 非必填
     */
    private String accountCity;
    /**
     * 电子联行号
     * 非必填
     */
    private String subBank;
    /**
     * 商户保留域
     * 非必填
     */
    private String merResv;

    /**
     * 转账金额
     *  整数，以分为单位
     *  非必填
     */
    private Integer transAmt;

    /**
     * 打款备注随机数
     *  6 位随机数字 默认不返回，需要 配置
     *  非必填
     */
    private Integer randomNum;
    /**
     * 是否收费
     *  0000:已扣费 其他为不扣费
     *  非必填
     */
    private String orderStatus;

}
