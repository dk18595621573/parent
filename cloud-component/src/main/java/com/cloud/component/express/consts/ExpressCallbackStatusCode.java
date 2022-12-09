package com.cloud.component.express.consts;

import com.cloud.common.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 监控状态:polling:监控中，shutdown:结束，abort:中止，updateall：重新推送。
 * 其中当快递单为已签收时status=shutdown，当message为“3天查询无记录”或“60天无变化时” status= abort ，
 * @author nlsm
 */
@Getter
@AllArgsConstructor
public enum ExpressCallbackStatusCode {

    /**
     * 监控中
     */
    POLLING_STATUS("polling", "监控中"),

    /**
     * 结束 快递单为已签收时
     */
    SHUTDOWN_STATUS("shutdown", "结束"),

    /**
     * 重新推送
     */
    UPDATEALL_STATUS("updateall", "重新推送"),

    /**
     * 中止 “3天查询无记录”或“60天无变化时”
     */
    ABORT_STATUS("abort", "中止");

    private final String code;

    private final String msg;

    public static ExpressCallbackStatusCode fromCode(String code) {
        if (StringUtils.isNotBlank(code)) {
            for (ExpressCallbackStatusCode value : ExpressCallbackStatusCode.values()) {
                if (value.getCode().equals(code)) {
                    return value;
                }
            }
        }
        return POLLING_STATUS;
    }
}
