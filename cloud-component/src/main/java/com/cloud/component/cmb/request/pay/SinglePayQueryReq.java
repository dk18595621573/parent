package com.cloud.component.cmb.request.pay;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 支付业务查询参数
 */
@Data
@Accessors(chain = true)
public class SinglePayQueryReq implements Serializable {

    /**
     *  查询条件（单记录）
     */
    private List<SinglePayQuery> bb1payqrx1;

    /**
     * 支付信息
     */
    @Data
    @Accessors(chain = true)
    public class SinglePayQuery{
        /**
         * 业务类型（N02030）
         */
        private String busCod;
        /**
         * 业务参考号
         */
        private String yurRef;
    }
}
