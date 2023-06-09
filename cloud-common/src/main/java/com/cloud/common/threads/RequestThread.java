package com.cloud.common.threads;

import com.cloud.common.core.model.RequestUser;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求数据.
 *
 * @author zenghao
 * @date 2022/6/6
 */
public class RequestThread {

    public static final String REQUEST_USER = "REQUEST-USER";

    private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL = new InheritableThreadLocal<>();

    public static void setData(Map<String, Object> dataMap) {
        THREAD_LOCAL.set(dataMap);
    }

    public static Map<String, Object> getData() {
        return THREAD_LOCAL.get();
    }

    public static void addParam(String key, Object value) {
        Map<String, Object> map = THREAD_LOCAL.get();
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(key, value);
        THREAD_LOCAL.set(map);
    }

    public static void removeParam(String key) {
        Map<String, Object> map = THREAD_LOCAL.get();
        if (map == null) {
            map = new HashMap<>();
        }
        map.remove(key);
        THREAD_LOCAL.set(map);
    }

    public static Object getParam(String key) {
        Map<String, Object> map = THREAD_LOCAL.get();
        if (map == null) {
            return null;
        }
        return map.get(key);
    }

    public static void setUser(RequestUser user) {
        addParam(REQUEST_USER, user);
    }

    public static RequestUser getUser() {
        Object param = getParam(REQUEST_USER);
        return (param instanceof RequestUser) ? (RequestUser) param : null;
    }

    public static Long getUserId() {
        RequestUser user = getUser();
        return (user == null) ? null : user.getUserId();
    }

    public static Long getDeptId() {
        RequestUser user = getUser();
        return (user == null) ? null : user.getDeptId();
    }

    public static void clear() {
        THREAD_LOCAL.remove();
    }
}
