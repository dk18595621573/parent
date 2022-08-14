package com.cloud.component.cmb.request.account;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 批量查询余额参数
 */
@Data
@Accessors(chain = true)
public class BatchBalanceReq implements Serializable {
    /**
     * 集合
     */
    private List<AccountInfo> ntqadinfx;

}
