package com.cloud.component.cmb.bean.response.account;

import com.cloud.component.cmb.bean.response.base.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 批量查询余额结果.
 *
 * @author nlsm
 * @date 2023-3-9 11:40:59
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class BatchBalanceRes extends BaseResponse {

    private static final long serialVersionUID = -8834186935022982589L;

    /**
     * 集合.
     */
    private List<AccountInfoResponse> ntqadinfz;

}
