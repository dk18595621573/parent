package com.cloud.common.core.domain.event;

import com.cloud.common.utils.spring.SpringBaseEvent;

import java.util.Map;

/**
 * 简单任务事件.
 *
 * @author zenghao
 * @date 2022/5/18
 */
public class SimpleJobEvent extends SpringBaseEvent<String> {

    private final String group;

    private final Map<String, Object> paramMap;

    public SimpleJobEvent(final Object source, final String data, final String group, final Map<String, Object> paramMap) {
        super(source, data);
        this.group = group;
        this.paramMap = paramMap;
    }

    public String getJobId() {
        return this.data;
    }

    public String getGroup() {
        return this.group;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }
}
