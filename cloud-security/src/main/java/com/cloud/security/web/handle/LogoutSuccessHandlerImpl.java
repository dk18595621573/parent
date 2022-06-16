package com.cloud.security.web.handle;

import com.cloud.common.constant.Constants;
import com.cloud.common.constant.HttpStatus;
import com.cloud.common.core.domain.AjaxResult;
import com.cloud.common.utils.StringUtils;
import com.cloud.common.utils.json.JsonUtil;
import com.cloud.framework.manager.AsyncManager;
import com.cloud.framework.manager.factory.AsyncFactory;
import com.cloud.framework.utils.ServletUtils;
import com.cloud.security.model.LoginUser;
import com.cloud.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义退出处理类 返回成功
 *
 * @author author
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Autowired
    private TokenService tokenService;

    /**
     * 退出处理
     *
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser)) {
            String userName = loginUser.getUsername();
            // 记录用户退出日志
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGOUT, "退出成功"));
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getToken());
        }
        ServletUtils.renderString(response, JsonUtil.toJson(AjaxResult.error(HttpStatus.SUCCESS, "退出成功")));
    }
}
