package com.cloud.component.cmb.bean.response.account;

import com.cloud.component.cmb.bean.response.base.BaseResponse;
import lombok.Data;

import java.util.List;

/**
 * 业务模式响应
 */
@Data
public class BusinessModelRes extends BaseResponse {
    /**
     * 结果集
     */
    private List<BusinessModel> ntqmdlstz;

    @Data
    public class BusinessModel{

        /**
         * 业务模式编号
         */
        private String busmod;

        /**
         * 业务模式名称
         */
        private String modals;
    }

}
