package com.cloud.component.fadada.request;

import lombok.Data;

/**
 * 企业信息
 *
 * @author mft
 */
@Data
public class CompanyInfoRequest {

    /**
     * 企业名称
     * <p> 非必填
     */
    private String companyName;

    /**
     * 统一社会信用代码
     * <p> 非必填
     */
    private String creditNo;

    /**
     * 统一社会信用代码证件照路径
     * <p> 非必填
     */
    private String creditImagePath;

}
