package com.cloud.component.cmb.request.account;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 账户参数
 */
@Data
@Accessors(chain = true)
public class AccountInfo implements Serializable {

    /**
     * 账号
     */
    private String accnbr;

    /**
     *  分行号
     */
    private String bbknbr;

}
