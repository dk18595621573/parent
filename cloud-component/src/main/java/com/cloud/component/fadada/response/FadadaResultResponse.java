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
     * 必填
     */
    private String result;
}
