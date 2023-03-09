package com.cloud.component.cmb.bean.response.account;

import com.cloud.component.cmb.bean.response.base.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 账户详细信息结果.
 *
 * @author nlsm
 * @date 2023-3-9 11:40:59
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class AccountInfoRes extends BaseResponse {

    private static final long serialVersionUID = -413683743135007309L;

    /**
     * 账户参数.
     */
    private List<AccountInfoResponse> ntqacinfz;

}
