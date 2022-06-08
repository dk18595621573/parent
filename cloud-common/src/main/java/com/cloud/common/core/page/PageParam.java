package com.cloud.common.core.page;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页参数.
 *
 * @author zenghao
 * @date 2022/6/1
 */
@Data
public class PageParam implements Serializable {

    public static final int DEFAULT_PAGE = 1;

    public static final int DEFAULT_SIZE = 10;

    public static final int MAX_SIZE = 500;

    private Integer page;

    private Integer size;

    public PageParam() {
    }

    public PageParam(final Integer page, final Integer size) {
        this.page = page;
        this.size = size;
    }

    public Integer getPage() {
        return page == null || page < 1 ? DEFAULT_PAGE : page;
    }

    public Integer getSize() {
        if (size == null || size < 1) {
            return DEFAULT_SIZE;
        }
        return size > MAX_SIZE ? MAX_SIZE : size;
    }
}
