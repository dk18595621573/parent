package com.cloud.common.core.page;

import cn.hutool.core.collection.CollUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 分页返回对象.
 *
 * @author zenghao
 * @date 2022/6/15
 */
@Data
public class Page<T> implements Serializable {

    private List<T> data;

    private Long total;

    public Page() {
    }

    public Page(final List<T> data, final Long total) {
        this.data = data;
        this.total = total;
    }

    public static <T> Page<T> empty() {
        return new Page<>(Collections.emptyList(), 0L);
    }

    public boolean isEmpty() {
        return CollUtil.isEmpty(data);
    }
}
