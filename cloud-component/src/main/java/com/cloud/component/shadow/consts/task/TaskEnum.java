package com.cloud.component.shadow.consts.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 任务枚举.
 *
 * @author Luo
 * @date 2023-05-16 14:16
 */
public class TaskEnum {

    /**
     * 任务状态.
     */
    @Getter
    @AllArgsConstructor
    public enum Status {

        /**
         * 等待调度.
         */
        WAITING("waiting", "等待调度", false),

        /**
         * 任务运行中.
         */
        RUNNING("running", "任务运行中", false),

        /**
         * 任务运行结束.
         */
        FINISH("finish", "任务运行结束", true),

        /**
         * 停止中.
         */
        STOPPING("stopping", "停止中", false),

        /**
         * 已结束.
         */
        STOPPED("stopped", "已结束", true),

        /**
         * 异常.
         */
        ERROR("error", "异常", true),

        ;

        /**
         * code.
         */
        private final String code;

        /**
         * desc.
         */
        private final String desc;

        /**
         * 状态是否是已结束状态, 任务终态，调用方需要判断终态，决定不需要在进行轮询.
         */
        private final boolean terminate;

        /**
         * 根据code获取.
         *
         * @param code code
         * @return 结果
         */
        public static Status getByCode(final String code) {
            return Arrays.stream(Status.values()).filter(i -> i.getCode().equals(code)).findFirst().orElse(null);
        }

    }

}
