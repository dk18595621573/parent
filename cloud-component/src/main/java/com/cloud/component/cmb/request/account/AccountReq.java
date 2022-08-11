package com.cloud.component.cmb.request.account;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 账户列表参数
 */
@Data
@Accessors(chain = true)
public class AccountReq implements Serializable {
    /**
     * 业务类别
     */
    private String buscod;

    /**
     * 业务模式
     */
    private String busmod;
}
