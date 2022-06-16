package com.cloud.xlock.model;

import com.cloud.xlock.lock.Lock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 锁信息.
 *
 * @author breggor
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LockedInfo {

    /**
     * 锁key信息.
     */
    private KeyInfo keyInfo;

    /**
     * 锁操作接口.
     */
    private Lock lockService;

    public LockedInfo(final KeyInfo keyInfo) {
        this.keyInfo = keyInfo;
    }
}
