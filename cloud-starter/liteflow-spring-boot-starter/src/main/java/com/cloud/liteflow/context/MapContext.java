package com.cloud.liteflow.context;

import cn.hutool.core.map.MapUtil;
import com.cloud.common.threads.RequestThread;
import lombok.Data;

import java.util.concurrent.ConcurrentHashMap;

/**
 * map容器.
 *
 * @author zenghao
 * @date 2023/5/5
 */
@Data
public class MapContext extends RequestContext {

    private final ConcurrentHashMap<String, Object> dataMap = new ConcurrentHashMap<>();

    public MapContext put(final String key, final Object value) {
        dataMap.put(key, value);
        return this;
    }

    public Object get(final String key) {
        return dataMap.get(key);
    }

    public <T> T get(final String key, final Class<T> clazz) {
        return MapUtil.get(dataMap, key, clazz);
    }

    public boolean hasData(String key) {
        return dataMap.containsKey(key);
    }

    public static MapContext newContext() {
        MapContext context = new MapContext();
        context.setUserId(RequestThread.getUserId());
        context.setDeptId(RequestThread.getDeptId());
        return context;
    }

}
