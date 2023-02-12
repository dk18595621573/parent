package com.cloud.seata.handler;

import io.seata.tm.api.DefaultFailureHandlerImpl;
import io.seata.tm.api.GlobalTransaction;

/**
 * 机器人错误消息通知.
 *
 * @author zenghao
 * @date 2023/2/3
 */
public class WxWorkFailureHandler extends DefaultFailureHandlerImpl {

    @Override
    public void onBeginFailure(final GlobalTransaction tx, final Throwable cause) {
        super.onBeginFailure(tx, cause);

    }

    @Override
    public void onCommitFailure(final GlobalTransaction tx, final Throwable cause) {
        super.onCommitFailure(tx, cause);
    }

    @Override
    public void onRollbackFailure(final GlobalTransaction tx, final Throwable originalException) {
        super.onRollbackFailure(tx, originalException);
    }
}
