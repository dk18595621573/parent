package com.cloud.framework.log;

import com.cloud.framework.log.model.LoginLog;
import com.cloud.framework.log.model.OperateLog;

/**
 * 日志服务接口.
 *
 * @author zenghao
 * @date 2022/5/6
 */
public interface LogService {

    /**
     * 保存登录日志.
     *
     * @param loginLog 登录日志
     */
    void saveLoginLog(LoginLog loginLog);

    /**
     * 保存操作日志.
     *
     * @param operateLog 操作日志
     */
    void saveOperateLog(OperateLog operateLog);
}
