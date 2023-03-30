package com.cloud.common.annotation;

/**
 * 查询操作符.
 *
 * @author Luo
 * @version 1.0
 * @date 2021-9-7 09:58
 */
public enum Operator {

    /**
     * 等于 =.
     */
    EQ,

    /**
     * 等于 =.
     */
    ENUM_EQ,

    /**
     * 不等于 <>.
     */
    NE,

    /**
     * 模糊查询 LIKE '%值%'.
     */
    LIKE,

    /**
     * 左模糊查询 LIKE '%值'.
     */
    LIKE_LEFT,

    /**
     * 右模糊查询 LIKE '值%'.
     */
    LIKE_RIGHT,

    /**
     * 大于 >.
     */
    GT,

    /**
     * 小于 <.
     */
    LT,

    /**
     * 大于等于 >=.
     */
    GTE,

    /**
     * 小于等于 <=.
     */
    LTE,

    /**
     * 不为空 IS NOT NULL.
     */
    NOT_NULL,

    /**
     * 为空 IS NULL.
     */
    IS_NULL,

    /**
     * IN查询 IN (v0, v1, ...).
     */
    IN,

    /**
     * NOT IN查询 NOT IN (v0, v1, ...).
     */
    NOT_IN,

    /**
     * 包含查询.
     *
     * <pre>
     * FIND_IN_SET(str,strList)
     * str 要查询的字符串 strList 参数以,分隔的字段名 如 (1,2,6,8,10,22)
     * 查询字段(strList)中包含(str)的结果，返回结果为null或记录
     * </pre>
     */
    FIND_IN_SET,

    /**
     * 分组 GROUP BY 字段1、字段2、、、.
     */
    GROUP;
}
