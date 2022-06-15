package com.cloud.common.core.page;

import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.io.Serializable;
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

    public Page(final List<T> data, final Long total) {
        this.data = data;
        this.total = total;
    }
}
