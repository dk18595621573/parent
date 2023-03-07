package com.cloud.component.cmb.bean.request;

import java.io.Serializable;

/**
 * 招商银行 API 请求.
 *
 * @author Luo
 * @date 2023-3-6 18:59:27
 */
public interface BaseRequest<T> extends Serializable {

    /**
     * 获取请求名.
     *
     * @return 请求名
     */
    String getFuncode();

    /**
     * 获取Json格式请求参数.
     *
     * @return Json格式请求参数
     */
    String getJsonParams();

    /**
     * 获取具体响应实现类的定义.
     *
     * @return 具体响应实现类的定义
     */
    Class<T> getResponseClass();

}
