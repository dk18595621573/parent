package com.cloud.component.cmb.request.account;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 账户列表参数
 */
@Data
@Accessors(chain = true)
public class AccountInfoReq implements Serializable {
    /**
     * 集合
     */
    private List<AccountInfo> ntqacinfx;
}
