package com.cloud.component.cmb.response.account;

import com.cloud.component.cmb.response.BaseResponse;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 账户详细信息结果
 */
@Data
@Accessors(chain = true)
public class AccountInfoRes extends BaseResponse {
    private List<AccountInfo> ntqacinfz;

}
