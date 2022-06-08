package com.cloud.framework.log;

import com.cloud.common.constant.Constants;
import com.cloud.framework.log.model.LoginLog;
import com.cloud.framework.log.model.OperateLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 简单实现.
 *
 * @author zenghao
 * @date 2022/5/6
 */
public class SimpleLogServiceImpl implements LogService {

    private final Logger logger = LoggerFactory.getLogger(Constants.SYS_USER_LOG);

    @Override
    public void saveLoginLog(final LoginLog loginLog) {
        logger.info("当前用户登录:{}", loginLog);
    }

    @Override
    public void saveOperateLog(final OperateLog operateLog) {
        logger.info("当前用户操作:{}", operateLog);
    }
}
