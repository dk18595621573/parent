package com.cloud.core.log;

import com.cloud.common.constant.Constants;
import com.cloud.core.log.model.LoginLog;
import com.cloud.core.log.model.OperateLog;

/**
 * 简单实现.
 *
 * @author zenghao
 * @date 2022/5/6
 */
public class SimpleLogServiceImpl implements LogService {

    @Override
    public void saveLoginLog(final LoginLog loginLog) {
        Constants.SYS_LOG.info("当前用户登录:{}", loginLog);
    }

    @Override
    public void saveOperateLog(final OperateLog operateLog) {
        Constants.SYS_LOG.info("当前用户操作:{}", operateLog);
    }
}
