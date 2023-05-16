package com.cloud.component.shadow.bean.response.task;

import com.cloud.component.shadow.bean.response.BaseResponse;
import com.cloud.component.shadow.bean.response.job.JobQueryResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 影刀RPA 查询任务 响应参数.
 *
 * @author Luo
 * @date 2023-05-16 14:16
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TaskQueryResponse extends BaseResponse implements Serializable {

    private static final long serialVersionUID = -6971100954626715146L;

    /**
     * 任务运行uuid.
     * 必填
     */
    private String taskUuid;

    /**
     * 任务名称.
     * 必填
     */
    private String taskName;

    /**
     * 任务运行状态.
     * 必填
     */
    private String status;

    /**
     * 任务运行状态描述.
     * 必填
     */
    private String statusName;

    /**
     * 任务开始时间.
     * 非必填
     */
    private String startTime;

    /**
     * 任务结束时间.
     * 非必填
     */
    private String endTime;

    /**
     * 任务所关联的应用运行信息，多个应用有多条.
     * 必填
     */
    private List<JobQueryResponse> jobDataList;

}
