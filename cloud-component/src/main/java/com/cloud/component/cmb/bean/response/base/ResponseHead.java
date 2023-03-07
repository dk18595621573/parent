package com.cloud.component.cmb.bean.response.base;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 响应head节点.
 *
 * @author Luo
 * @date 2023-03-06 15:00
 */
@Data
@Accessors(chain = true)
public class ResponseHead implements Serializable {

    /**
     * 成功响应码.
     */
    public static final String SUCCESS = "SUC0000";

    private static final long serialVersionUID = -5582871964118912569L;

    /**
     * 暂无数据.
     */
    private String bizcode;

    /**
     * 接口名称.
     */
    private String funcode;

    /**
     * 用户ID.
     */
    private String userid;

    /**
     * 请求唯一编号.
     */
    private String reqid;

    /**
     * 响应ID.
     */
    private String rspid;

    /**
     * 错误代码.
     * 正常响应为SUC0000
     */
    private String resultcode;

    /**
     * 错误描述.
     */
    private String resultmsg;

    /**
     * 是否请求成功.
     *
     * @return 结果
     */
    public boolean success() {
        return SUCCESS.equals(getResultcode());
    }

}
