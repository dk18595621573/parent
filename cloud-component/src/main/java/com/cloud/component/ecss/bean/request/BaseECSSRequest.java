package com.cloud.component.ecss.bean.request;

import cn.hutool.core.bean.BeanUtil;
import com.cloud.common.utils.json.JsonUtil;
import com.cloud.component.ecss.exception.ECSSRuntimeException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * ECSS 基础 请求参数.
 *
 * @author Luo
 * @version 1.0
 * @date 2021-12-31 10:50:09
 */
@Data
@Accessors(chain = true)
public abstract class BaseECSSRequest<T> implements BaseRequest<T> {

    private static final long serialVersionUID = 6597147723326518672L;

    /**
     * 具体响应实现类.
     */
    @JsonIgnore
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private final Class<T> clazz;

    public BaseECSSRequest() {
        ParameterizedType superclass = (ParameterizedType) getClass().getGenericSuperclass();
        Type actualTypeArgument = superclass.getActualTypeArguments()[0];
        clazz = (Class<T>) actualTypeArgument;
    }

    /**
     * 获取Xml格式请求参数.
     *
     * @return xml格式请求参数
     */
    @Override
    @JsonIgnore
    public String getXmlParams() {
        String xml;
        try {
            XmlMapper xmlMapper = new XmlMapper();
            xml = xmlMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new ECSSRuntimeException("参数转换异常");
        }
        return xml;
    }

    /**
     * 获取Json格式业务请求参数.
     *
     * @return Json格式业务请求参数
     */
    @Override
    @JsonIgnore
    public String getJsonParams() {
        return JsonUtil.toJson(BeanUtil.beanToMap(this, false, true));
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
