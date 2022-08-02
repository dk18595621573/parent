package com.cloud.component.fadada.request;

import lombok.Data;

/**
 * 合同模板信息
 *
 * @author mft
 */
@Data
public class UploaddocsRequest {

    /**
     * 合同编号
     * <p> 必填
     */
    private String contractId;

    /**
     * 合同标题
     * <p> 必填
     */
    private String docTitle;

    /**
     * 文档类型
     * 文档地址doc_url 和file 两个参数必选一
     * <p> 必填
     */
    private String docType;

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
