package com.cloud.shadow.bean.response;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 影刀RPA 接口 响应体.
 *
 * @author Luo
 * @date 2023-3-20 10:13:45
 */
@Data
@Accessors(chain = true)
public class ShadowBotResponse implements Serializable {

    private static final long serialVersionUID = -6811550517417623460L;

    /**
     * 状态码 200表示成功，非200表示失败.
     */
    private Integer code;

    /**
     * 调用是否成功，可以根据该字段判断接口调用是否成功.
     */
    private Boolean success;

    /**
     * 状态码描述.
     */
    private String msg;

    /**
     * 响应数据.
     */
    private JSONObject data;

    /**
     * 将 #{@link #data} 转换为对象.
     *
     * @param tClass 对象类型
     * @param <T>    对象类型
     * @return 对象
     */
    public <T> T toData(final Class<T> tClass) {
        return JSONUtil.toBean(getData(), tClass);
    }

    /**
     * 是否请求成功.
     *
     * @return 结果
     */
    public boolean success() {
        return Boolean.TRUE.equals(getSuccess()) && 200 == getCode();
    }

}
