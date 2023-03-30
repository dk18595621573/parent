package com.cloud.common.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 排序.
 *
 * @author Luo
 * @date 2021-9-23 10:59:37
 */
@Data
public class SortBy implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Field> orders = new ArrayList<>();

    public boolean isEmpty() {
        return this.orders.isEmpty();
    }

    /**
     * 加入排序字段.
     *
     * @param direction 排序方向
     * @param property  字段
     * @return SortInfo
     */
    public SortBy addField(final Direction direction, final String property) {
        Field field = new Field(direction, property);
        if (!orders.contains(field)) {
            this.orders.add(field);
        }
        return this;
    }

    /**
     * 获取方向字段.
     *
     * @param direction 排序方向
     * @return List
     */
    public List<String> get(final Direction direction) {
        return orders.stream().filter(o -> o.direction == direction).map(Field::getProperty).collect(Collectors.toList());
    }

    /**
     * 字段定义类.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(of = {"direction", "property"})
    public static class Field implements Serializable {

        private Direction direction;

        private String property;
    }

    /**
     * 排序反向.
     *
     * @author Luo
     * @version 1.0
     * @date 2021-9-23 10:59:37
     */
    public enum Direction {
        ASC, DESC;
    }

}
