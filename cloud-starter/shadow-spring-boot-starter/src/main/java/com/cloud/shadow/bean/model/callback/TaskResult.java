package com.cloud.shadow.bean.model.callback;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 任务回调结果.
 *
 * @author Luo
 * @date 2023-05-16 14:16
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TaskResult extends DataTypeResult {

    private static final long serialVersionUID = -5881353502997043411L;

    /**
     * 任务id.
     */
    private String taskUuid;

    /**
     * 任务状态.
     *
     * @see com.cloud.shadow.consts.task.TaskEnum.Status
     */
    private String status;

    /**
     * 任务运行备注.
     */
    private String msg;

    /**
     * 任务中第一个应用开始执行时间.
     */
    private String startTime;

    /**
     * 任务中最后一个应用执行结束时间.
     */
    private String endTime;

    /**
     * 应用执行结果集合.
     */
    private List<JobResult> jobList;

}
