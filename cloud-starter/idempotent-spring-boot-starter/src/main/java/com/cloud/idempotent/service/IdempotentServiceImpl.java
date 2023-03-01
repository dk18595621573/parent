package com.cloud.idempotent.service;

import com.cloud.common.utils.RedisKeyUtil;
import com.cloud.common.utils.StringUtils;
import com.cloud.common.utils.json.JsonUtil;
import com.cloud.core.redis.RedisCache;
import com.cloud.idempotent.model.IdempotentResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 默认的幂等数据处理.
 *
 * @author zenghao
 * @date 2023/2/27
 */
@Slf4j
public class IdempotentServiceImpl implements IdempotentService {

    private static final String DATA_KEY_PREFIX = "IDEMPOTENT_DATA";

    @Autowired
    private RedisCache redisCache;

    @Override
    public void save(final String module, final String id, final Object result) {
        redisCache.setCacheObject(genCacheKey(module, id), JsonUtil.toJson(result), 7, TimeUnit.DAYS);
    }

    @Override
    public IdempotentResult load(final String module, final String id, final Class clazz) {
        String object = redisCache.getCacheObject(genCacheKey(module, id));
        if (Objects.isNull(object)) {
            return IdempotentResult.error();
        }
        if (StringUtils.equals("null", object)) {
            return IdempotentResult.success(null);
        }
        return IdempotentResult.success(JsonUtil.parse(object, clazz));
    }

    private String genCacheKey(final String module, final String id) {
        return RedisKeyUtil.generate(DATA_KEY_PREFIX, module, id);
    }
}
