package com.cloud.component.shadow.bean.request.task;

import com.cloud.component.shadow.bean.model.RobotParam;
import com.cloud.component.shadow.bean.request.BaseShadowBotRequest;
import com.cloud.component.shadow.bean.response.task.TaskStartResponse;
import com.cloud.component.shadow.consts.ShadowBotEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 影刀RPA 启动任务 请求参数.
 *
 * @author Luo
 * @date 2023-05-16 14:16
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TaskStartRequest extends BaseShadowBotRequest<TaskStartResponse> implements Serializable {

    private static final long serialVersionUID = -5191353816956462227L;

    /**
     * 启动任务uuid，可在控制台-任务管理-右键获取.
     * 必填
     */
    private String scheduleUuid;

    /**
     * 任务关联的应用运行参数.
     * 非必填
     */
    private List<RobotRelaParam> scheduleRelaParams;

    /**
     * 获取API请求地址.
     *
     * @return API请求地址
     */
    @Override
    public String getApiUrl() {
        return ShadowBotEnum.Url.TASK_CREATE.getCode();
    }

    /**
     * 任务关联的应用参数.
     */
    @Data
    @Accessors(chain = true)
    public static class RobotRelaParam implements Serializable {

        private static final long serialVersionUID = -3045121307600494873L;

        /**
         * 应用uuid.
         * 带运行参数的应用uuid
         */
        private String robotUuid;

        /**
         * 应用运行参数.
         * 关联该应用的应用运行参数
         */
        private List<RobotParam> params;

    }

}
