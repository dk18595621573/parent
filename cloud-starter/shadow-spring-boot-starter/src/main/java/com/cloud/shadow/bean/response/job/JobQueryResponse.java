package com.cloud.shadow.bean.response.job;

import com.cloud.shadow.bean.model.RobotParam;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 影刀RPA 查询Job 响应参数.
 *
 * @author Luo
 * @date 2023-05-16 14:16
 */
@Data
@Accessors(chain = true)
public class JobQueryResponse implements Serializable {

    private static final long serialVersionUID = -5682667019877295413L;

    /**
     * 启动的任务的uuid.
     * 不为空
     */
    private String jobUuid;

    /**
     * 任务状态.
     * 不为空
     *
     * @see com.xybot.api.sdk.enums.JobStatusEnum
     */
    private String status;

    /**
     * 任务状态名称.
     * 不为空
     *
     * @see com.xybot.api.sdk.enums.JobStatusEnum
     */
    private String statusName;

    /**
     * 运行的应用.
     * 不为空
     */
    private String robotUuid;

    /**
     * 运行的应用名称.
     * 不为空
     */
    private String robotName;

    /**
     * 任务开始运行的时间.
     */
    private String startTime;

    /**
     * 任务结束运行的时间.
     */
    private String endTime;

    /**
     * 备注.
     */
    private String remark;

    /**
     * 应用运行参数.
     */
    private RobotParam robotParams;

    /**
     * 机器人账号.
     * 不为空
     */
    private String robotClientUuid;

    /**
     * 机器人名称.
     * 不为空
     */
    private String robotClientName;

}
