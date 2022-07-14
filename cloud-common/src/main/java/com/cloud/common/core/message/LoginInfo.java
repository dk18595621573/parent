package com.cloud.common.core.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录信息推送.
 *
 * @author zenghao
 * @date 2022/7/14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfo extends SseData {

    /**
     * 用户token
     */
    private String token;

    /**
     * 是否首次登录
     */
    private boolean first;
}
