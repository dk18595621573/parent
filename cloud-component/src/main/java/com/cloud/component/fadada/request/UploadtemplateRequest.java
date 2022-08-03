package com.cloud.component.fadada.request;

import lombok.Data;

/**
 * 合同模板信息
 *
 * @author mft
 */
@Data
public class UploadtemplateRequest {

    /**
     * 模板编号
     * <p> 必填
     */
    private String templateId;

    /**
     * 文档地址  docUrl和file 两个参数必选一
     * <p> 必填
     */
    private String docUrl;

    /**
     * PDF模板    docUrl和file 两个参数必选一
     * <p> 必填
     */
    private String file;

}
