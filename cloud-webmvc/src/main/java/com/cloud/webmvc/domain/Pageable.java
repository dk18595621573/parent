package com.cloud.webmvc.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 分页数据vo.
 *
 * @author zenghao
 * @date 2022/5/17
 */
@Data
@ApiModel("分页数据")
public class Pageable<T> implements Serializable {

    private static final Pageable EMPTY_PAGE = new Pageable<>(0, Collections.emptyList(), null);

    @ApiModelProperty("总记录数")
    private long total;

    @ApiModelProperty("列表数据")
    private List<T> rows;

    @ApiModelProperty("每页记录条数")
    private Integer size;

    public Pageable() {
    }

    public Pageable(final long total, final List<T> rows, final Integer size) {
        this.total = total;
        this.rows = rows;
        this.size = size;
    }

    public static <T> Pageable<T> of(final long total, final List<T> rows) {
        return of(total, rows, null);
    }

    public static <T> Pageable<T> of(final long total, final List<T> rows, final Integer size) {
        return new Pageable<>(total, rows, size);
    }

    public static <T> Pageable<T> empty() {
        return EMPTY_PAGE;
    }
}
