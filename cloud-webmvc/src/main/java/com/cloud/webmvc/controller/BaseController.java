package com.cloud.webmvc.controller;

import com.cloud.common.constant.Constants;
import com.cloud.common.constant.HttpStatus;
import com.cloud.common.core.model.AjaxResult;
import com.cloud.common.core.model.RequestUser;
import com.cloud.common.core.page.Page;
import com.cloud.common.enums.ClientType;
import com.cloud.common.utils.DateUtils;
import com.cloud.common.utils.StringUtils;
import com.cloud.webmvc.domain.TableDataInfo;
import com.cloud.webmvc.utils.SecurityUtils;
import com.cloud.webmvc.utils.ServletUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.Objects;

/**
 * web层通用数据处理
 *
 * @author author
 */
public class BaseController {

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * 响应请求分页数据
     */
    protected TableDataInfo getDataTable(Page<?> page) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(page.getData());
        rspData.setTotal(page.getTotal());
        return rspData;
    }

    /**
     * 返回成功
     */
    public AjaxResult success() {
        return AjaxResult.success();
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error() {
        return AjaxResult.error();
    }

    /**
     * 返回成功消息
     */
    public AjaxResult success(String message) {
        return AjaxResult.success(message);
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error(String message) {
        return AjaxResult.error(message);
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected AjaxResult toAjax(int rows) {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 响应返回结果
     *
     * @param result 结果
     * @return 操作结果
     */
    protected AjaxResult toAjax(boolean result) {
        return result ? success() : error();
    }

    /**
     * 页面跳转
     */
    public String redirect(String url) {
        return StringUtils.format("redirect:{}", url);
    }

    /**
     * 获取用户缓存信息
     */
    public RequestUser getLoginUser() {
        return SecurityUtils.getLoginUser();
    }

    /**
     * 获取登录用户id
     */
    public Long getUserId() {
        return SecurityUtils.getUserId();
    }

    /**
     * 获取登录部门id
     */
    public Long getDeptId() {
        return SecurityUtils.getDeptId();
    }

    /**
     * 获取登录用户名
     */
    public String getUsername() {
        return SecurityUtils.getUsername();
    }

    /**
     * 获取请求平台.
     *
     * @return 请求平台
     */
    public ClientType getClientType() {
        String platform = ServletUtils.getParameter(Constants.PLATFORM);
        return ClientType.getByValue(platform);
    }

    /**
     * 获取请求平台code.
     *
     * @return 平台code
     */
    public Integer getPlatformCode() {
        ClientType clientType = getClientType();
        if (Objects.nonNull(clientType)) {
            return clientType.getCode();
        }
        return null;
    }

    /**
     * 获取请求平台value.
     *
     * @return 平台value
     */
    public String getPlatformValue() {
        ClientType clientType = getClientType();
        if (Objects.nonNull(clientType)) {
            return clientType.getValue();
        }
        return null;
    }

}
