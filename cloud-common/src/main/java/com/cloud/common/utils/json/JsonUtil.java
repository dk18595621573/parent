package com.cloud.common.utils.json;

import com.cloud.common.utils.ExceptionUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * Jackson工具类.
 *
 * @author xht.
 */
@Slf4j
public class JsonUtil {

    /**
     * 将对象序列化成json字符串.
     *
     * @param value javaBean
     * @param <T>   T 泛型标记
     * @return jsonString json字符串
     */
    public static <T> String toJson(final T value) {
        try {
            return getInstance().writeValueAsString(value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 将对象序列化成 json byte 数组.
     *
     * @param object javaBean
     * @return jsonString json字符串
     */
    public static byte[] toJsonAsBytes(final Object object) {
        try {
            return getInstance().writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            throw ExceptionUtil.unchecked(e);
        }
    }

    /**
     * 将json反序列化成ArrayList集合.
     *
     * @param content   content
     * @param <T>       T 泛型标记
     * @return Bean
     */
    public static <T> List<T> parseList(final String content, final Class<T> clazz) {
        try {
            CollectionType collectionType = getInstance().getTypeFactory().constructCollectionType(ArrayList.class, clazz);
            return getInstance().readValue(content, collectionType);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 将json反序列化带泛型参数对象.
     *
     * @param content   content
     * @param <T>       T 泛型标记
     * @param <C>       T 类的泛型标记
     * @return Bean
     */
    public static <T, C> T parseGeneric(final String content, final Class<T> superClass, final Class<C> clazz) {
        try {
            JavaType javaType = getInstance().constructType(clazz);
            JavaType generalizedType = getInstance().getTypeFactory().constructParametricType(superClass, javaType);
            return getInstance().readValue(content, generalizedType);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 将json反序列化成对象.
     *
     * @param content   content
     * @param valueType class
     * @param <T>       T 泛型标记
     * @return Bean
     */
    public static <T> T parse(final String content, final Class<T> valueType) {
        try {
            return getInstance().readValue(content, valueType);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 将json反序列化成对象.
     *
     * @param content       content
     * @param typeReference 泛型类型
     * @param <T>           T 泛型标记
     * @return Bean
     */
    public static <T> T parse(final String content, final TypeReference<T> typeReference) {
        try {
            return getInstance().readValue(content, typeReference);
        } catch (IOException e) {
            throw ExceptionUtil.unchecked(e);
        }
    }

    /**
     * 将json byte 数组反序列化成对象.
     *
     * @param bytes     json bytes
     * @param valueType class
     * @param <T>       T 泛型标记
     * @return Bean
     */
    public static <T> T parse(final byte[] bytes, final Class<T> valueType) {
        try {
            return getInstance().readValue(bytes, valueType);
        } catch (IOException e) {
            throw ExceptionUtil.unchecked(e);
        }
    }

    /**
     * 将json反序列化成对象.
     *
     * @param bytes         bytes
     * @param typeReference 泛型类型
     * @param <T>           T 泛型标记
     * @return Bean
     */
    public static <T> T parse(final byte[] bytes, final TypeReference<T> typeReference) {
        try {
            return getInstance().readValue(bytes, typeReference);
        } catch (IOException e) {
            throw ExceptionUtil.unchecked(e);
        }
    }

    /**
     * 将json反序列化成对象.
     *
     * @param in        InputStream
     * @param valueType class
     * @param <T>       T 泛型标记
     * @return Bean
     */
    public static <T> T parse(final InputStream in, final Class<T> valueType) {
        try {
            return getInstance().readValue(in, valueType);
        } catch (IOException e) {
            throw ExceptionUtil.unchecked(e);
        }
    }

    /**
     * 将json反序列化成对象.
     *
     * @param in            InputStream
     * @param typeReference 泛型类型
     * @param <T>           T 泛型标记
     * @return Bean
     */
    public static <T> T parse(final InputStream in, final TypeReference<T> typeReference) {
        try {
            return getInstance().readValue(in, typeReference);
        } catch (IOException e) {
            throw ExceptionUtil.unchecked(e);
        }
    }

    /**
     * 转Map.
     *
     * @param content 内容
     * @return Map
     */
    public static Map<String, Object> toMap(final String content) {
        try {
            return getInstance().readValue(content, Map.class);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 转对象.
     *
     * @param fromValue   map
     * @param toValueType 类实例
     * @param <T>         类型
     * @return T
     */
    public static <T> T toPojo(final Map fromValue, final Class<T> toValueType) {
        return getInstance().convertValue(fromValue, toValueType);
    }

    /**
     * 将json字符串转成 JsonNode.
     *
     * @param jsonString jsonString
     * @return jsonString json字符串
     */
    public static JsonNode readTree(final String jsonString) {
        try {
            return getInstance().readTree(jsonString);
        } catch (IOException e) {
            throw ExceptionUtil.unchecked(e);
        }
    }

    /**
     * 将json字符串转成 JsonNode.
     *
     * @param in InputStream
     * @return jsonString json字符串
     */
    public static JsonNode readTree(final InputStream in) {
        try {
            return getInstance().readTree(in);
        } catch (IOException e) {
            throw ExceptionUtil.unchecked(e);
        }
    }

    /**
     * 将json字符串转成 JsonNode.
     *
     * @param content content
     * @return jsonString json字符串
     */
    public static JsonNode readTree(final byte[] content) {
        try {
            return getInstance().readTree(content);
        } catch (IOException e) {
            throw ExceptionUtil.unchecked(e);
        }
    }

    /**
     * 将json字符串转成 JsonNode.
     *
     * @param jsonParser JsonParser
     * @return jsonString json字符串
     */
    public static JsonNode readTree(final JsonParser jsonParser) {
        try {
            return getInstance().readTree(jsonParser);
        } catch (IOException e) {
            throw ExceptionUtil.unchecked(e);
        }
    }

    public static ObjectMapper getInstance() {
        return JacksonHolder.INSTANCE;
    }

    /**
     * JacksonHolder.
     */
    private static class JacksonHolder {
        private static final ObjectMapper INSTANCE = new JacksonObjectMapper();
    }

    /**
     * JacksonObjectMapper.
     */
    public static class JacksonObjectMapper extends ObjectMapper {
        private static final long serialVersionUID = 4288193147502386170L;

        public JacksonObjectMapper() {
            super();
            //设置地点为中国
            super.setLocale(Locale.CHINA);
            //去掉默认的时间戳格式
            super.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            //设置为中国上海时区
            super.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
            //序列化时，日期的统一格式
            super.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA));
            //失败处理
            super.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            super.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            //单引号处理
            super.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
            //反序列化时，属性不存在的兼容处理
            super.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            //日期格式化
            super.registerModule(new JavaTimeModule());
            super.findAndRegisterModules();
        }

    }

}
