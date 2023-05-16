package com.cloud.component.shadow.bean.model.callback;

import com.cloud.component.shadow.bean.model.RobotParam;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Job回调结果.
 *
 * @author Luo
 * @date 2023-05-16 14:16
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class JobResult extends DataTypeResult {

    private static final long serialVersionUID = -5941475989076837963L;

    /**
     * 应用运行uuid.
     */
    private String jobUuid;

    /**
     * 任务状态.
     *
     * @see com.cloud.component.shadow.consts.job.JobEnum.Status
     */
    private String status;

    /**
     * 应用运行信息，当应用运行异常时不为空.
     */
    private String msg;

    /**
     * 任务开始运行的时间.
     */
    private String startTime;

    /**
     * 任务结束运行的时间.
     */
    private String endTime;

    /**
     * 执行的机器人uuid.
     */
    private String robotClientUuid;

    /**
     * 机器人名称.
     */
    private String robotClientName;

    /**
     * 应用名.
     */
    private String robotName;

    /**
     * 应用运行输出参数.
     */
    private List<RobotParam> result;

}
