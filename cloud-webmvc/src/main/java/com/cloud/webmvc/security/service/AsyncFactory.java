package com.cloud.webmvc.security.service;

import com.cloud.common.constant.Constants;
import com.cloud.common.core.model.BaseRequestInfo;
import com.cloud.common.utils.StringUtils;
import com.cloud.core.utils.SpringUtils;
import com.cloud.core.log.LogService;
import com.cloud.core.log.model.LoginLog;
import com.cloud.core.log.model.OperateLog;
import com.cloud.webmvc.utils.ip.AddressUtils;

import java.util.Date;
import java.util.TimerTask;

/**
 * 异步工厂（产生任务用）
 *
 * @author author
 */
public class AsyncFactory {

    /**
     * 记录登录信息
     *
     * @param username 用户名
     * @param status   状态
     * @param message  消息
     * @param args     列表
     * @return 任务task
     */
    public static TimerTask recordLogininfor(final String username, final String status, final String message, final BaseRequestInfo requestInfo,
                                             final Object... args) {
        return new TimerTask() {
            @Override
            public void run() {
                // 封装对象
                LoginLog logininfor = new LoginLog();
                logininfor.setUserName(username);
                logininfor.fromRequestInfo(requestInfo);
                logininfor.setMsg(message);
                // 日志状态
                if (StringUtils.equalsAny(status, Constants.LOGIN_SUCCESS, Constants.LOGOUT, Constants.REGISTER)) {
                    logininfor.setStatus(Constants.SUCCESS);
                } else if (Constants.LOGIN_FAIL.equals(status)) {
                    logininfor.setStatus(Constants.FAIL);
                }
                logininfor.setLoginTime(new Date());
                // 插入数据
                SpringUtils.getBean(LogService.class).saveLoginLog(logininfor);
            }
        };
    }

    /**
     * 操作日志记录
     *
     * @param operLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOper(final OperateLog operLog) {
        return new TimerTask() {
            @Override
            public void run() {
                // 远程查询操作地点
                operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));

                SpringUtils.getBean(LogService.class).saveOperateLog(operLog);
            }
        };
    }
}
