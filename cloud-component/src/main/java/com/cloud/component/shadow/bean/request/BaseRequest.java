package com.cloud.component.shadow.bean.request;

import java.io.Serializable;
import java.util.Map;

/**
 * 影刀RPA API 请求.
 *
 * @author Luo
 * @date 2023-05-16 14:16
 */
public interface BaseRequest<T> extends Serializable {

    /**
     * 获取API请求地址.
     *
     * @return API请求地址
     */
    String getApiUrl();

    /**
     * 获取Json格式请求参数.
     *
     * @return Json格式请求参数
     */
    String getJsonParams();

    /**
     * 获取Map格式请求参数.
     *
     * @return map格式请求参数
     */
    Map<String, Object> getParams();

    /**
     * 获取具体响应实现类的定义.
     *
     * @return 具体响应实现类的定义
     */
    Class<T> getResponseClass();

}
