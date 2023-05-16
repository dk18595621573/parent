package com.cloud.component.shadow.consts.job;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * Job枚举.
 *
 * @author Luo
 * @date 2023-05-16 14:16
 */
public class JobEnum {

    /**
     * 任务状态.
     */
    @Getter
    @AllArgsConstructor
    public enum Status {

        /**
         * 任务被创建出来的初始状态.
         */
        CREATED("created", "已创建", false),

        /**
         * 任务没有立即运行，缺少条件，等待被别的消息触发调度，如应用执行完成.
         */
        WAITING("waiting", "等待调度", false),

        /**
         * 任务被客户端机器人运行中.
         */
        RUNNING("running", "运行中", false),

        /**
         * 停止中，向客户端发送 stop 命令后，会改成这个状态.
         */
        STOPPING("stopping", "停止中", false),

        /**
         * 已停止，向客户端发送 stop 命令后的终态.
         */
        STOPPED("stopped", "已停止", true),

        /**
         * 运行过程中发生了异常，异常为终态终态.
         */
        ERROR("error", "异常", true),

        /**
         * 运行正常结束 终态.
         */
        FINISH("finish", "完成", true),

        /**
         * 已跳过.
         */
        SKIPPED("skipped", "已跳过", true),

        /**
         * 运行被用户取消 终态.
         */
        CANCEL("cancel", "取消", true);

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
