package com.cloud.component.cmb.bean.request.account;

import com.cloud.component.cmb.bean.request.BaseCmbRequest;
import com.cloud.component.cmb.bean.response.account.BusinessModelRes;
import com.cloud.component.cmb.consts.FunCodeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 可经办业务模式查询请求参数.
 *
 * @author nlsm
 * @date 2023-3-6 19:01:44
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class BusinessModelReq extends BaseCmbRequest<BusinessModelRes> implements Serializable {

    private static final long serialVersionUID = 3179874042642376366L;

    /**
     * 业务类别.
     */
    private String buscod;

    @Override
    @JsonIgnore
    public String getFuncode() {
        return FunCodeEnum.AccountQuery.DCLISMOD.name();
    }

}
