package com.cloud.component.honor.bean.request;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * 荣耀 基础 请求参数.
 *
 * @author Luo
 * @version 1.0
 * @date 2021-12-31 10:50:09
 */
@Data
@Accessors(chain = true)
public abstract class BaseHonorRequest<T> implements BaseRequest<T> {

    private static final long serialVersionUID = 6597147723326518672L;

    /**
     * 具体响应实现类.
     */
    @JsonIgnore
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private final Class<T> clazz;

    public BaseHonorRequest() {
        ParameterizedType superclass = (ParameterizedType) getClass().getGenericSuperclass();
        Type actualTypeArgument = superclass.getActualTypeArguments()[0];
        clazz = (Class<T>) actualTypeArgument;
    }

    /**
     * 获取Json格式业务请求参数.
     *
     * @return Json格式业务请求参数
     */
    @Override
    @JsonIgnore
    public String getJsonParams() {
        return JSONUtil.toJsonStr(BeanUtil.beanToMap(this, false, true));
    }

    /**
     * 获取Map格式请求参数.
     *
     * @return map格式请求参数
     */
    @Override
    @JsonIgnore
    public Map<String, Object> getParams() {
        return BeanUtil.beanToMap(this, false, true);
    }

    /**
     * 获取具体响应实现类的定义.
     *
     * @return 具体响应实现类的定义
     */
    @Override
    @JsonIgnore
    public Class<T> getResponseClass() {
        return clazz;
    }

}
