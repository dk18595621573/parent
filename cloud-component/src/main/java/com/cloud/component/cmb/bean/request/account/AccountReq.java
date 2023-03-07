package com.cloud.component.cmb.bean.request.account;

import com.cloud.component.cmb.bean.request.BaseCmbRequest;
import com.cloud.component.cmb.bean.response.account.AccountRes;
import com.cloud.component.cmb.consts.FunCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 账户列表参数.
 *
 * @author nlsm
 * @date 2023-3-6 19:01:44
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class AccountReq extends BaseCmbRequest<AccountRes> implements Serializable {

    private static final long serialVersionUID = -1151499418808860108L;

    /**
     * 业务类别.
     */
    private String buscod;

    /**
     * 业务模式.
     */
    private String busmod;

    @Override
    public String getFuncode() {
        return FunCodeEnum.AccountQuery.DCLISACC.name();
    }

}
