package com.cloud.tencent.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 腾讯云即时通讯IM 枚举.
 *
 * @author Luo
 * @date 2023-03-27 14:34
 */
public class ImEnum {

    /**
     * 响应码.
     */
    @Getter
    @AllArgsConstructor
    public enum ErrorCode {

        /**
         * 0：成功.
         */
        CODE_0(0, "成功"),;

        /**
         * code.
         */
        private final Integer code;

        /**
         * 描述.
         */
        private final String desc;

        /**
         * 根据code获取.
         *
         * @param code code
         * @return 结果
         */
        public static ErrorCode getByCode(final Integer code) {
            return Arrays.stream(ErrorCode.values()).filter(i -> i.getCode().equals(code)).findFirst().orElse(null);
        }

    }

    /**
     * 回调通知响应码.
     * 若业务希望拒绝发言的同时，将错误码 ErrorCode 和 ErrorInfo 传递至客户端，请将错误码 ErrorCode 设置在 [120001, 130000] 区间内
     */
    @Getter
    @AllArgsConstructor
    public enum NoticeErrorCode {

        /**
         * 0：允许发言.
         */
        CODE_0(0, "允许发言"),

        /**
         * 1：禁止发言.
         */
        CODE_1(1, "禁止发言"),

        /**
         * 2：静默丢弃.
         */
        CODE_2(2, "静默丢弃"),;

        /**
         * code.
         */
        private final Integer code;

        /**
         * 描述.
         */
        private final String desc;

        /**
         * 根据code获取.
         *
         * @param code code
         * @return 结果
         */
        public static NoticeErrorCode getByCode(final Integer code) {
            return Arrays.stream(NoticeErrorCode.values()).filter(i -> i.getCode().equals(code)).findFirst().orElse(null);
        }

    }

}
