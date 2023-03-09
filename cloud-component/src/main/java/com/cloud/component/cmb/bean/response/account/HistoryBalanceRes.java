package com.cloud.component.cmb.bean.response.account;

import com.cloud.component.cmb.bean.response.base.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 历史余额响应.
 *
 * @author nlsm
 * @date 2023-3-9 13:05:02
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class HistoryBalanceRes extends BaseResponse {

    private static final long serialVersionUID = 6548449540204215982L;

    private List<Balance> ntqabinfz;

    /**
     * 余额.
     */
    @Data
    @Accessors(chain = true)
    public static class Balance implements Serializable {

        private static final long serialVersionUID = -5126960257526986257L;

        /**
         * 账号.
         */
        private String accnbr;

        /**
         * 联机余额.
         */
        private String balamt;

        /**
         *  分行号.
         */
        private String bbknbr;

        /**
         *  保留字段.
         */
        private String rsv30z;

        /**
         *  交易日期.
         */
        private String trsdat;

    }

}
