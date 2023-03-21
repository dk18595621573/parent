package com.cloud.component.ecss.utils;

import com.cloud.common.utils.StringUtils;
import com.cloud.component.ecss.exception.ECSSRuntimeException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.experimental.UtilityClass;

import java.util.Objects;

/**
 * Xml工具类.
 *
 * @author Luo
 * @date 2023-03-21 14:09
 */
@UtilityClass
public class XmlUtil {

    /**
     * 创建XmlMapper对象，用于实体与Json和xml之间的相互转换.
     */
    private static final XmlMapper XML_MAPPER = new XmlMapper();

    /**
     * 将bean转换为xml.
     *
     * @param cls bean对应的Class
     * @return bean转换为xml
     */
    public static String toXml(final Object cls) {
        if (Objects.isNull(cls)) {
            return null;
        }
        try {
            return XML_MAPPER.writeValueAsString(cls);
        } catch (JsonProcessingException e) {
            throw new ECSSRuntimeException(e);
        }
    }

    /**
     * 将xml转换为bean.
     *
     * @param <T> 泛型
     * @param xml 要转换为bean的xml
     * @param cls bean对应的Class
     * @return xml转换为bean
     */
    public static <T> T toBean(final String xml, final Class<T> cls) {
        if (StringUtils.isBlank(xml)) {
            return null;
        }
        try {
            return XML_MAPPER.readValue(xml, cls);
        } catch (JsonProcessingException e) {
            throw new ECSSRuntimeException(e);
        }
    }

}
