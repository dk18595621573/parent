package com.cloud.framework.lock.config;

import com.cloud.framework.lock.exception.StoreIsEmptyException;

import java.util.HashMap;
import java.util.Map;

/**
 * Server Pattern Factory.
 *
 * @author zenghao
 */
public final class ServerPatternFactory {

    private static final Map<String, ServerPattern> SERVER_PATTERN_HASH_MAP = new HashMap<>();

    static {
        SERVER_PATTERN_HASH_MAP.put(ServerPattern.SINGLE.getPattern(), ServerPattern.SINGLE);
        SERVER_PATTERN_HASH_MAP.put(ServerPattern.CLUSTER.getPattern(), ServerPattern.CLUSTER);
        SERVER_PATTERN_HASH_MAP.put(ServerPattern.MASTER_SLAVE.getPattern(), ServerPattern.MASTER_SLAVE);
        SERVER_PATTERN_HASH_MAP.put(ServerPattern.REPLICATED.getPattern(), ServerPattern.REPLICATED);
        SERVER_PATTERN_HASH_MAP.put(ServerPattern.SENTINEL.getPattern(), ServerPattern.SENTINEL);
    }

    private ServerPatternFactory() {
    }

    /**
     * 根据字符串模式标识返回服务器模式枚举类.
     *
     * @param pattern 运行模式
     * @return 服务器模式枚举类
     * @throws StoreIsEmptyException 运行模式为空异常
     */
    public static ServerPattern getServerPattern(final String pattern) throws StoreIsEmptyException {
        ServerPattern serverPattern = SERVER_PATTERN_HASH_MAP.get(pattern);
        if (serverPattern == null) {
            throw new StoreIsEmptyException("没有找到相应的服务器模式,请检测参数是否正常,pattern的值为:" + pattern);
        }
        return serverPattern;
    }
}
