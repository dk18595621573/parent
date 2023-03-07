package com.cloud.component.cmb.bean.response.account;

import com.cloud.component.cmb.bean.response.base.BaseResponse;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 历史余额响应
 */
@Data
@Accessors(chain = true)
public class HistoryBalanceRes extends BaseResponse {
    private List<Balance> ntqabinfz;

    @Data
    public class Balance{
        /**
         * 账号
         */
        private String accnbr;

        /**
         * 联机余额
         */
        private String balamt;

        /**
         *  分行号
         */
        private String bbknbr;

        /**
         *  保留字段
         */
        private String rsv30z;

        /**
         *  交易日期
         */
        private String trsdat;


    }
}
