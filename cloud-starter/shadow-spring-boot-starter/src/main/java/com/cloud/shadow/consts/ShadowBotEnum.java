package com.cloud.shadow.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 影刀RPA枚举.
 *
 * @author Luo
 * @date 2023-03-07 9:13
 */
public class ShadowBotEnum {

    /**
     * 请求方法枚举.
     */
    @Getter
    @AllArgsConstructor
    public enum Url {

        /**
         * 鉴权.
         */
        TOKEN_CREATE("/oapi/token/v2/token/create", "鉴权"),

        /**
         * 启动任务.
         */
        TASK_CREATE("/oapi/dispatch/v2/task/start", "启动任务"),

        /**
         * 查询任务运行结果.
         */
        TASK_QUERY("/oapi/dispatch/v2/task/query", "查询任务运行结果"),

        /**
         * 停止任务运行.
         */
        TASK_STOP("/oapi/dispatch/v2/task/stop", "停止任务运行"),

        ;

        /**
         * code.
         */
        private final String code;

        /**
         * 说明.
         */
        private final String explain;

        /**
         * 根据code获取.
         *
         * @param code code
         * @return 结果
         */
        public static Url getByCode(final String code) {
            return Arrays.stream(Url.values()).filter(i -> i.getCode().equals(code)).findFirst().orElse(null);
        }

    }

    /**
     * 状态码.
     * 文档地址：<a href="https://www.winrobot360.com/yddoc/language/zh-cn/%E7%AE%A1%E7%90%86%E6%96%87%E6%A1%A3/%E5%BC%80%E6%94%BEapi/%E9%80%9A%E7%94%A8%E8%AF%B4%E6%98%8E/%E7%8A%B6%E6%80%81%E7%A0%81%E8%AF%B4%E6%98%8E.html">...</a>
     */
    @Getter
    @AllArgsConstructor
    public enum ErrCode {

        /**
         * 200：调用成功.
         */
        CODE_200(200, "正常"),

        /**
         * 401：接口未授权.
         */
        CODE_401(401, "接口未授权"),

        /**
         * 400：接口参数校验错误.
         */
        CODE_400(400, "接口参数校验错误"),

        /**
         * 500：服务内部错误.
         */
        CODE_500(500, "服务内部错误"),

        ;

        /**
         * code.
         */
        private final int code;

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
        public static ErrCode getByCode(final Integer code) {
            return Arrays.stream(ErrCode.values()).filter(i -> i.getCode() == code).findFirst().orElse(null);
        }

    }

    /**
     * 应用运行参数类型.
     */
    @Getter
    @AllArgsConstructor
    public enum Type {

        /**
         * 字符串.
         */
        STR("str", "字符串"),

        /**
         * 整型.
         */
        INT("int", "整型"),

        /**
         * 浮点.
         */
        FLOAT("float", "浮点"),

        /**
         * 布尔.
         */
        BOOL("bool", "布尔"),

        /**
         * 文件.
         */
        FILE("file", "文件"),

        ;

        /**
         * code.
         */
        private final String code;

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
        public static DataType getByCode(final String code) {
            return Arrays.stream(DataType.values()).filter(i -> i.getCode() == code).findFirst().orElse(null);
        }

    }

    /**
     * 回调数据类型.
     */
    @Getter
    @AllArgsConstructor
    public enum DataType {

        /**
         * 应用运行数据回调.
         */
        JOB("job", "应用运行数据回调"),

        /**
         * 任务运行数据回调.
         */
        TASK("task", "任务运行数据回调"),

        ;

        /**
         * code.
         */
        private final String code;

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
        public static DataType getByCode(final String code) {
            return Arrays.stream(DataType.values()).filter(i -> i.getCode() == code).findFirst().orElse(null);
        }

    }


}
