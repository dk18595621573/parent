package com.cloud.component.chinapay.request;

import com.cloud.common.utils.StringUtils;
import com.cloud.component.chinapay.response.EnterpriseAuthResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 企业认证 请求体.
 *
 * @author zenghao
 * @date 2022/6/20
 */
@Data
public class EnterpriseAuthRequest extends BaseRequest<EnterpriseAuthResponse> {

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
     * 企业账户账号
     * 敏感信息 序列化时忽略
     * 必填
     */
    @JsonIgnore
    private String accountNo;
    /**
     * 企业名称
     * 敏感信息 序列化时忽略
     * 必填
     */
    @JsonIgnore
    private String keyName;
    /**
     * 法人名称
     * 敏感信息 序列化时忽略
     * 必填
     */
    @JsonIgnore
    private String usrName;

    @Override
    public String getBusiType() {
        return "3060";
    }

    @Override
    public String requestUrl() {
        return "/VASAP/vasap/business.htm";
    }

    @Override
    public Class<EnterpriseAuthResponse> responseClass() {
        return EnterpriseAuthResponse.class;
    }

    @Override
    public Map<String, Object> buildSensData() {
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isNotBlank(this.getAccountNo())) {
            map.put("accountNo", this.getAccountNo());
        }
        if (StringUtils.isNotBlank(this.getKeyName())) {
            map.put("keyName", this.getKeyName());
        }
        if (StringUtils.isNotBlank(this.getUsrName())) {
            map.put("usrName", this.getUsrName());
        }
        return map;
    }
}
