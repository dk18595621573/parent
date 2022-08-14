package com.cloud.component.cmb.request.account;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 历史余额参数
 */
@Data
@Accessors(chain = true)
public class HistoryBalanceReq implements Serializable {
    private List<HistoryBalance> ntqabinfy;

    @Data
    @Accessors(chain = true)
    public class HistoryBalance{
        /**
         * 账号
         */
        private String accnbr;

        /**
         *  分行号
         */
        private String bbknbr;

        /**
         * 起始日期 格式：YYYYMMDD,开始日期与结束日期的间隔不能超过31天
         */
        private String bgndat;

        /**
         * 结束日期 格式：YYYYMMDD
         */
        private String enddat;
    }
}
