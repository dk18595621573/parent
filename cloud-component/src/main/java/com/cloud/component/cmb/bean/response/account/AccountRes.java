package com.cloud.component.cmb.bean.response.account;

import com.cloud.component.cmb.bean.response.base.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 账户列表响应.
 *
 * @author nlsm
 * @date 2023-3-9 11:40:59
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class AccountRes extends BaseResponse {

    private static final long serialVersionUID = 1667138395778385852L;

    private List<Account> ntqaclstz;

    /**
     * 账户.
     */
    @Data
    @Accessors(chain = true)
    public static class Account implements Serializable {
        private static final long serialVersionUID = -5194158151297403419L;

        /**
         * 账号.
         */
        private String accnbr;

        /**
         * 币种.
         */
        private String ccynbr;

        /**
         * 分行号.
         */
        private String bbknbr;

        /**
         * 户名.
         */
        private String accnam;

        /**
         * 企业编号.
         */
        private String relnbr;

    }

}
