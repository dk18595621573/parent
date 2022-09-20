package com.cloud.component.bot.request;

import java.io.Serializable;
import java.util.Map;

/**
 * 基础请求对象.
 *
 * @author zenghao
 * @date 2022/8/30
 */
public interface BaseRequest extends Serializable {

    Map<String, Object> toMap();
}
