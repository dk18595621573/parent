package com.cloud.log.context;

import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

public class LogRecordContext {

    private static final InheritableThreadLocal<Stack<Map<String, Object>>> VARIABLE_MAP_STACK = new InheritableThreadLocal<>();

    /**
     * 设置数据.
     *
     * @param key   key
     * @param value value
     */
    public static void putVariable(final String key, final Object value) {
        if (StringUtils.isEmpty(key) || Objects.isNull(value)) {
            throw new IllegalArgumentException(String.format("ThreadLocal argument empty for set , key : {%s}, value : {%s}", key, value));
        }
        Stack<Map<String, Object>> maps = VARIABLE_MAP_STACK.get();
        if (Objects.isNull(maps)) {
            maps = new Stack<Map<String, Object>>();
        }
        maps.push(Collections.singletonMap(key, value));
        VARIABLE_MAP_STACK.set(maps);
    }


    /**
     * 设置数据.
     *
     * @param dataMap dataMap
     */
    public static void putVariable(final Map<String, Object> dataMap) {
        Stack<Map<String, Object>> maps = VARIABLE_MAP_STACK.get();
        maps.push(dataMap);
        VARIABLE_MAP_STACK.set(maps);
    }

    /**
     * 获取数据.
     *
     * @return
     */
    public static Map<String, Object> getVariables() {
        Stack<Map<String, Object>> stack = VARIABLE_MAP_STACK.get();
        if(Objects.isNull(stack)){
            return Collections.emptyMap();
        }
        Map<String, Object> dataMap = new HashMap<>();
        for (Iterator iter = stack.iterator(); iter.hasNext(); ) {
            Map next = (Map<String, Object>) iter.next();
            dataMap.putAll(next);
        }
        return dataMap;
    }

    /**
     * 清空所有数据.
     */
    public static void clear() {
        VARIABLE_MAP_STACK.remove();
    }

    public static void main(String[] args) {
        LogRecordContext.getVariables();
    }
}