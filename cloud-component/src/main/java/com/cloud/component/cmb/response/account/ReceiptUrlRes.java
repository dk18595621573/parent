package com.cloud.component.cmb.response.account;

import com.cloud.component.cmb.response.BaseResponse;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 回单结果文件路径结果
 */
@Data
@Accessors(chain = true)
public class ReceiptUrlRes extends BaseResponse {
    /**
     * 文件url (生成完成后，获取地址链接，请注意该链接有效期3天，获取到后请立即下载保存，以防过期失效。)
     */
    private String fileurl;

    /**
     * 完成时间
     */
    private String fintim;
}
