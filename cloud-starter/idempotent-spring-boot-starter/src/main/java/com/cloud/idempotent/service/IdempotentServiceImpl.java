package com.cloud.idempotent.service;

import cn.hutool.json.JSONUtil;
import com.cloud.common.utils.RedisKeyUtil;
import com.cloud.common.utils.StringUtils;
import com.cloud.common.utils.json.JsonUtil;
import com.cloud.core.redis.RedisCache;
import com.cloud.idempotent.model.IdempotentResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 默认的幂等数据处理.
 *
 * @author zenghao
 * @date 2023/2/27
 */
@Slf4j
@AllArgsConstructor
public class IdempotentServiceImpl implements IdempotentService {

    private static final String DATA_KEY_PREFIX = "IDEMPOTENT_DATA";

    private RedisCache redisCache;

    /**
     * 结果保存时间，单位（天）
     */
    private Long keepTime;

    /**
     * 默认缓存结果7天
     * @param redisCache redisCache
     */
    public IdempotentServiceImpl(final RedisCache redisCache) {
        this.redisCache = redisCache;
        this.keepTime = 7L;
    }

    @Override
    public void save(final String module, final String id, final Object result) {
        redisCache.setCacheObject(genCacheKey(module, id), result, keepTime, TimeUnit.DAYS);
    }

    @Override
    public <T> IdempotentResult<T> load(final String module, final String id, final Class<T> clazz) {
        T object = redisCache.getCacheObject(genCacheKey(module, id));
        if (Objects.isNull(object)) {
            return IdempotentResult.error();
        }
        if (clazz == Void.class) {
            return IdempotentResult.success(null);
        }
        return IdempotentResult.success(object);
    }

    private String genCacheKey(final String module, final String id) {
        return RedisKeyUtil.generate(DATA_KEY_PREFIX, module, id);
    }
}
