package com.cloud.shadow.bean.request.task;

import com.cloud.shadow.bean.request.BaseShadowBotRequest;
import com.cloud.shadow.bean.response.task.TaskStopResponse;
import com.cloud.shadow.consts.ShadowBotEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 影刀RPA 停止任务 请求参数.
 *
 * @author Luo
 * @date 2023-05-16 14:16
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TaskStopRequest extends BaseShadowBotRequest<TaskStopResponse> implements Serializable {

    private static final long serialVersionUID = 956738644734025654L;

    /**
     * 任务运行uuid.
     */
    private String taskUuid;

    /**
     * 获取API请求地址.
     *
     * @return API请求地址
     */
    @Override
    public String getApiUrl() {
        return ShadowBotEnum.Url.TASK_STOP.getCode();
    }

}

