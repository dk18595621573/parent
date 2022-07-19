package com.cloud.log.model;

import lombok.Builder;
import lombok.Data;

/**
 * 日志保存实体对象 .
 *
 * @author xht.
 * @createTime 2021年12月17日 14:43:00
 */
@Data
@Builder
public class LogStroage {

    /**
     * 操作日志绑定的业务对象标识
     */
    private String bizNo;

    /**
     * 操作日志的执行人
     */
    private String operator;

    /**
     * 操作日志的文本.
     */
    private String success;

    /**
     * 操作失败的日志,.
     */
    private String fail;

    /**
     * 操作日志的种类.
     */
    private String category;

    /**
     * 执行时间.
     */
    private Long time;
}
