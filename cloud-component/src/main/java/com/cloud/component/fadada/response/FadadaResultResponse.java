package com.cloud.component.fadada.response;

import lombok.Data;

/**
 * 响应信息
 *
 * @author mft
 */
@Data
public class FadadaResultResponse extends FadadaBaseResponse {

    /**
     * 处理结果(success：成功 error：失败)
     */
    private String result;

    /**
     * 下载地址
     */
    private String download_url;

    /**
     * 预览地址
     */
    private String viewpdf_url;

}
