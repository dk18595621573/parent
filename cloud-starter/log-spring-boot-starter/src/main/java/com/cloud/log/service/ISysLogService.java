package com.cloud.log.service;

import com.cloud.log.model.LogStroage;

/**
 * .
 *
 * @author xht.
 * @createTime 2021年12月17日 14:42:00
 */
public interface ISysLogService {

    void save(LogStroage logStroage);

}
