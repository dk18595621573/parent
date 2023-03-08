package com.cloud.component.cmb.bean.request.account;

import com.cloud.component.cmb.bean.request.BaseCmbRequest;
import com.cloud.component.cmb.bean.response.account.BranchRes;
import com.cloud.component.cmb.consts.FunCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 查询分行号信息参数.
 *
 * @author nlsm
 * @date 2023-3-6 20:38:34
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class BranchReq extends BaseCmbRequest<BranchRes> implements Serializable {

    private static final long serialVersionUID = -7666188102220144814L;

    /**
     * 账号.
     */
    private String fctval;

    @Override
    public String getFuncode() {
        return FunCodeEnum.AccountQuery.NTACCBBK.name();
    }


}
