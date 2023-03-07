package com.cloud.component.cmb.bean.request.base;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 请求head节点.
 *
 * @author Luo
 * @date 2023-03-06 15:00
 */
@Data
@Accessors(chain = true)
public class RequestHead implements Serializable {

    private static final long serialVersionUID = -4762381143252023353L;

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

}
