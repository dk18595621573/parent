package com.cloud.common.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * redis key 工具类.
 *
 * @author zenghao
 * @date 2022/5/17
 */
@Slf4j
@UtilityClass
public class RedisKeyUtil {

    public static final String DELIMITER = ":";

    /**
     * 生成redis key.
     *
     * @param fields 字段
     * @return String
     */
    public static String generate(final String... fields) {
        if (fields == null || fields.length < 1) {
            throw new IllegalArgumentException("fields can not be empty");
        }
        String key = "";
        try {
            key = String.join(DELIMITER, fields);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
        return key;
    }

    /**
     * 分解redis key
     *
     * @param key redis key
     * @return
     */
    public static String[] split(String key) {
        return StringUtils.split(key, DELIMITER);
    }

}
