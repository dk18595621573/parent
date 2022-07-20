package com.cloud.core.log.model;

import com.cloud.common.core.domain.model.BaseRequestInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author author
 */
@Data
public class LoginLog implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 登录状态 0成功 1失败
     */
    private String status;

    /**
     * 提示消息
     */
    private String msg;

    /**
     * 访问时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;

    public void fromRequestInfo(BaseRequestInfo requestInfo) {
        if (Objects.nonNull(requestInfo)) {
            this.setIpaddr(requestInfo.getIpaddr());
            this.setLoginLocation(requestInfo.getLoginLocation());
            this.setBrowser(requestInfo.getBrowser());
            this.setOs(requestInfo.getOs());
        }
    }
}
