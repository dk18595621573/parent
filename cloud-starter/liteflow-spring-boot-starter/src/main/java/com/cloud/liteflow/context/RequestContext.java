package com.cloud.liteflow.context;

import lombok.Data;

/**
 * 用户请求上下文.
 *
 * @author zenghao
 * @date 2023/5/5
 */
@Data
public class RequestContext {

    private Long userId;

    private Long deptId;

}
