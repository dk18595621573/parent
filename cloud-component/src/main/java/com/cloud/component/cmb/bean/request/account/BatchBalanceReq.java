package com.cloud.component.cmb.bean.request.account;

import com.cloud.component.cmb.bean.request.BaseCmbRequest;
import com.cloud.component.cmb.bean.response.account.BatchBalanceRes;
import com.cloud.component.cmb.consts.FunCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 批量查询余额参数.
 *
 * @author nlsm
 * @date 2023-3-6 20:37:36
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class BatchBalanceReq extends BaseCmbRequest<BatchBalanceRes> implements Serializable {
    private static final long serialVersionUID = 1717682754339946132L;

    /**
     * 集合.
     */
    private List<AccountInfo> ntqadinfx;

    @Override
    public String getFuncode() {
        return FunCodeEnum.AccountQuery.NTQADINF.name();
    }

}
