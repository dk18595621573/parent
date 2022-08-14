package com.cloud.component.cmb.response.account;

import com.cloud.component.cmb.response.BaseResponse;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 账户列表响应
 */
@Data
@Accessors(chain = true)
public class AccountRes extends BaseResponse {
    private List<Account> ntqaclstz;

    @Data
    public class Account{
        /**
         * 账号
         */
        private String accnbr;

        /**
         * 币种
         */
        private String ccynbr;

        /**
         *  分行号
         */
        private String bbknbr;

        /**
         *  户名
         */
        private String accnam;

        /**
         *  企业编号
         */
        private String relnbr;


    }
}
