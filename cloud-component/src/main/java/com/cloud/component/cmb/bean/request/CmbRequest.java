package com.cloud.component.cmb.bean.request;

import cn.hutool.json.JSONObject;
import com.cloud.component.cmb.bean.request.base.RequestHead;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 招商银行 接口 请求报文.
 *
 * @author nlsm
 * @date 2023-3-6 14:06:10
 */
@Data
@Accessors(chain = true)
public class CmbRequest implements Serializable {

    private static final long serialVersionUID = 3604574752124318755L;

    /**
     * 请求数据.
     */
    private Request<?> request;

    /**
     * 请求报文.
     */
    @Data
    @Accessors(chain = true)
    public static class Request<T> implements Serializable {

        private static final long serialVersionUID = 1440050801554610495L;

        /**
         * head节点.
         */
        private RequestHead head;

        /**
         * 报文体.
         */
        private JSONObject body;

    }

}
