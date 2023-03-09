package com.cloud.component.cmb.bean.response.account;

import com.cloud.component.cmb.bean.response.base.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 回单结果文件路径结果.
 *
 * @author nlsm
 * @date 2023-3-9 13:06:29
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ReceiptUrlRes extends BaseResponse {

    private static final long serialVersionUID = -3143391868408889667L;

    /**
     * 文件url (生成完成后，获取地址链接，请注意该链接有效期3天，获取到后请立即下载保存，以防过期失效。).
     */
    private String fileurl;

    /**
     * 完成时间.
     */
    private String fintim;

}
