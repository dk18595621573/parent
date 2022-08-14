package com.cloud.component.cmb.response.account;

import com.cloud.component.cmb.response.BaseResponse;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 批量查询余额结果
 */
@Data
@Accessors(chain = true)
public class BatchBalanceRes extends BaseResponse {
    /**
     * 集合
     */
    private List<AccountInfo> ntqadinfz;

}
