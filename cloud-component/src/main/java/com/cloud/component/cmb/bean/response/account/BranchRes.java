package com.cloud.component.cmb.bean.response.account;

import com.cloud.component.cmb.bean.response.base.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 查询分行号信息响应.
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class BranchRes extends BaseResponse {

    private static final long serialVersionUID = -6136817219655868254L;

    /**
     * 分行号.
     */
    private String bbknbr;

    /**
     * 账号.
     */
    private String fctval;

}
