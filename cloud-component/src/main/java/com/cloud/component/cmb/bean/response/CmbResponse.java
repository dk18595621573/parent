package com.cloud.component.cmb.bean.response;

import cn.hutool.json.JSONUtil;
import com.cloud.component.cmb.bean.response.base.ResponseHead;
import com.cloud.component.cmb.bean.common.CmbSignature;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 招商银行 接口 应答报文.
 *
 * @author Luo
 * @date 2023-3-6 14:06:10
 */
@Data
@Accessors(chain = true)
public class CmbResponse implements Serializable {

    private static final long serialVersionUID = 3604574752124318755L;

    /**
     * 响应数据.
     */
    private Response response;

    /**
     * 签名信息.
     */
    private CmbSignature signature;

    /**
     * 响应交易报文头转换为对象.
     *
     * @return 对象
     */
    public ResponseHead toHead() {
        return JSONUtil.toBean(getResponse().getHead(), ResponseHead.class);
    }

    /**
     * 将 #{@link #response} 中 {@link Response#getBody()} 转换为对象.
     *
     * @param tClass 对象类型
     * @param <T>    对象类型
     * @return 对象
     */
    public <T> T toBody(final Class<T> tClass) {
        return JSONUtil.toBean(getResponse().getBody(), tClass);
    }

    /**
     * 返回报文.
     */
    @Data
    @Accessors(chain = true)
    public static class Response implements Serializable {

        private static final long serialVersionUID = 1440050801554610495L;

        /**
         * head节点.
         */
        private String head;

        /**
         * 报文体.
         */
        private String body;

    }

}
