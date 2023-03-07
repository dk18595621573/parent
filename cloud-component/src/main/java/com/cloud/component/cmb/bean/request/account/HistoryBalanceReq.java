package com.cloud.component.cmb.bean.request.account;

import com.cloud.component.cmb.bean.request.BaseCmbRequest;
import com.cloud.component.cmb.bean.response.account.HistoryBalanceRes;
import com.cloud.component.cmb.consts.FunCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 历史余额参数.
 *
 * @author nlsm
 * @date 2023-3-6 20:38:34
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class HistoryBalanceReq extends BaseCmbRequest<HistoryBalanceRes> implements Serializable {

    private static final long serialVersionUID = -7666188102220144814L;

    /**
     * .
     */
    private List<HistoryBalance> ntqabinfy;

    @Override
    public String getFuncode() {
        return FunCodeEnum.AccountQuery.NTQABINF.name();
    }

    /**
     * 历史余额查询.
     */
    @Data
    @Accessors(chain = true)
    public static class HistoryBalance implements Serializable {

        private static final long serialVersionUID = -7493506976270091308L;

        /**
         * 账号.
         */
        private String accnbr;

        /**
         * 分行号.
         */
        private String bbknbr;

        /**
         * 起始日期 格式：YYYYMMDD,开始日期与结束日期的间隔不能超过31天.
         */
        private String bgndat;

        /**
         * 结束日期 格式：YYYYMMDD.
         */
        private String enddat;

    }

}
