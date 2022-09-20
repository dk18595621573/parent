package com.cloud.component.bot.request;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 分页搜索.
 *
 * @author zenghao
 * @date 2022/8/30
 */
@Data
public class PageRequest implements BaseRequest {

    /**
     * 可选，默认为0，即第一页
     */
    private Integer current;

    /**
     * 可选，默认为10，最大为100
     */
    private Integer pageSize;

    public PageRequest() {
    }

    public PageRequest(final int current, final int pageSize) {
        this.current = Math.max(current, 0);
        this.pageSize = (pageSize < 0) ? 10 : Math.min(pageSize, 100);
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        if (Objects.nonNull(current)) {
            map.put("current", current);
        }
        if (Objects.nonNull(pageSize)) {
            map.put("pageSize", pageSize);
        }
        return map;
    }
}
