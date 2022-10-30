package com.cloud.common.core.page;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

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

    private String orderBy;

    private Boolean reasonable;

    public PageParam() {
    }

    public PageParam(final Integer page, final Integer size) {
        this(page, size, null);
    }

    public PageParam(final Integer page, final Integer size, final String orderBy) {
        this(page, size, orderBy, false);
    }

    public PageParam(final Integer page, final Integer size, final String orderBy, final Boolean reasonable) {
        this.page = Objects.isNull(page) || page < DEFAULT_PAGE ? DEFAULT_PAGE : page;
        if (size == null || size < 1) {
            this.size = DEFAULT_SIZE;
        } else {
            this.size = size > MAX_SIZE ? MAX_SIZE : size;
        }
        this.orderBy = orderBy;
        this.reasonable = reasonable;
    }
}
