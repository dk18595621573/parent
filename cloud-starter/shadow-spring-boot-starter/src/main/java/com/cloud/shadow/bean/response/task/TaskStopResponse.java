package com.cloud.shadow.bean.response.task;

import com.cloud.shadow.bean.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 影刀RPA 停止任务 响应参数.
 *
 * @author Luo
 * @date 2023-05-16 14:16
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TaskStopResponse extends BaseResponse implements Serializable {

    private static final long serialVersionUID = 2812778613524018744L;


}
