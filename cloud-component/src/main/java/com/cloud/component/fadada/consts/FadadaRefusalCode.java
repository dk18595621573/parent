package com.cloud.component.fadada.consts;

import com.cloud.common.utils.StringUtils;
import com.cloud.component.express.consts.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author nlsm
 */

@Getter
@AllArgsConstructor
public enum FadadaRefusalCode {
    /**
     * 未知异常.
     */
    API_EXCEPTION("-1", "API异常"),
    FAILURE("0", "失败"),
    SUCCESS("1", "成功"),
    DUPLICATE_REQUEST("2", "重复请求"),
    PUBLIC_PARAMETER_ILLEGAL("1001", "公共参数非法"),
    DOES_NOT_EXIST("1002", "app_id不存在或未启动"),
    MSG_DIGEST_INVALID("1003", "msg_digest无效"),
    REQUEST_PARAMETER_IS_ILLEGAL("1004", "请求参数非法"),
    UNBOUND_IP_WHITELIST("1005", "未绑定IP白名单"),
    SIGNATURE_RECORD_IS_EMPTY("1201", "签署记录为空"),
    CONTRACT_RECORD_IS_EMPTY("1401", "合同记录为空"),
    CONTRACT_FILED("1402", "合同已归档"),
    CONTRACT_HAS_EXPIRED("1403", "合同已过期"),
    CONTRACT_CANCELED("1404", "合同已撤销"),
    CONTRACT_STATUS_DOES("1406", "合同状态不允许操作"),
    SIGNATURE_RECORD_EMPTY("1407", "签署记录为空");

    private final String code;

    private final String msg;

    public static FadadaRefusalCode fromCode(String code) {
        if (StringUtils.isNotBlank(code)) {
            for (FadadaRefusalCode value : FadadaRefusalCode.values()) {
                if (value.getCode().equals(code)) {
                    return value;
                }
            }
        }
        return API_EXCEPTION;
    }
}
