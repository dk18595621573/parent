package com.cloud.xlock.model;

import com.cloud.xlock.config.XLockConsts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 锁key信息.
 *
 * @author breggor
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public final class KeyInfo {
    private String lockId;

    private String cacheKey;

    private List<String> keys;

    private long leaseTime = -1L;

    private long waitTime = -1L;

    private TimeUnit timeUnit = TimeUnit.SECONDS;

    /**
     * 获取key信息.
     *
     * @return key信息
     */
    public String getKey() {
        Assert.notEmpty(keys, "keys不能为空");
        return XLockConsts.KEY_PREFIX + StringUtils.collectionToDelimitedString(keys, "-");
    }

    public boolean isEmpty() {
        return keys.isEmpty();
    }
}
