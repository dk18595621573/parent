package com.cloud.component.cmb.bean.request.account;

import com.cloud.component.cmb.bean.request.BaseCmbRequest;
import com.cloud.component.cmb.bean.response.account.AccountInfoRes;
import com.cloud.component.cmb.consts.FunCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 账户列表参数.
 *
 * @author nlsm
 * @date 2023-3-6 19:01:44
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class AccountInfoReq extends BaseCmbRequest<AccountInfoRes> implements Serializable {

    private static final long serialVersionUID = -7328364470568894052L;

    /**
     * 集合.
     */
    private List<AccountInfo> ntqacinfx;

    @Override
    public String getFuncode() {
        return FunCodeEnum.AccountQuery.NTQACINF.name();
    }

}

