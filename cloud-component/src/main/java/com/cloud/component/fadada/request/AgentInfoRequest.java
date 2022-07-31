package com.cloud.component.fadada.request;

import lombok.Data;

/**
 * 代理人信息
 *
 * @author mft
 */
@Data
public class AgentInfoRequest {

    /**
     * 代理人姓名
     * <p> 非必填
     */
    private String agentName;

    /**
     * 代理人证件号
     * <p> 非必填
     */
    private String agentId;

    /**
     * 代理人手机号
     * <p> 非必填
     */
    private String agentMobile;

    /**
     * 代理人证件正面照下载地址
     * <p> 非必填
     */
    private String agentIdFrontPath;

    /**
     * 代理人银行卡号
     * <p> 非必填
     */
    private String bankCardNo;

}
