package com.cloud.component.ecss.bean.request;

import java.io.Serializable;
import java.util.Map;

/**
 * ECSS API 请求.
 *
 * @author Luo
 * @date 2023-3-6 18:59:27
 */
public interface BaseRequest<T> extends Serializable {

    /**
     * 获取请求方法.
     *
     * @return 请求方法
     */
    String getMethod();

    /**
     * 获取Xml格式请求参数.
     *
     * @return xml格式请求参数
     */
    String getXmlParams();

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
