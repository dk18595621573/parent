package com.cloud.component.cmb.bean.response.account;

import com.cloud.component.cmb.bean.response.base.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 业务模式响应.
 *
 * @author nlsm
 * @date 2023-3-9 13:02:53
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class BusinessModelRes extends BaseResponse {
    private static final long serialVersionUID = -8725494081223211456L;

    /**
     * 结果集.
     */
    private List<BusinessModel> ntqmdlstz;

    /**
     * 业务模式.
     */
    @Data
    @Accessors(chain = true)
    public static class BusinessModel implements Serializable {

        private static final long serialVersionUID = 5052910989640619639L;

        /**
         * 业务模式编号.
         */
        private String busmod;

        /**
         * 业务模式名称.
         */
        private String modals;

    }

}
