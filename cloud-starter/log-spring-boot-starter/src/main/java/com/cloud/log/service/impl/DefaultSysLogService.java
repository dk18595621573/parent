package com.cloud.log.service.impl;

import com.cloud.log.service.ISysLogService;
import com.cloud.log.model.LogStroage;
import lombok.extern.slf4j.Slf4j;

/**
 * 默认日志打印类 .
 *
 * @author xht.
 * @createTime 2021年12月17日 14:44:00
 */
@Slf4j
public class DefaultSysLogService implements ISysLogService {

    @Override
    public void save(final LogStroage logStroage) {
        log.info("审计日志：{}", logStroage);
    }
}
