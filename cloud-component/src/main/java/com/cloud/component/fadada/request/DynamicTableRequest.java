package com.cloud.component.fadada.request;

import lombok.Data;

/**
 * 模板填充
 *
 * @author mft
 */
@Data
public class DynamicTableRequest {

    /**
     * 动态表格插入方式
     * 0：新建页面添加table（默认） 1：在某个关键字后添加table
     * <p> 非必填
     */
    private Integer insertWay;

    /**
     * 关键字方式插入动态表格
     * #1. 当insertWay为1时，必填 #2. 要求该关键字后（当前页）必 须不包含内容，否则会被覆盖 #3. 若关键字为多个 ，则取第一个 关键字，在此关键字后插入table
     * <p> 非必填
     */
    private String keyword;

    /**
     * 表格需要插入的页数
     * #1. 当insertWay为0时，必填 #2. 表示从第几页开始插入表格， 如要从末尾插入table,则 pageBegin为pdf总页数加1 #3. 多个表格指定相同 pageBegin，则多个表格按顺序插 入，一个表格新起一页 #4. pageBegin为-1时，则从pdf末 尾插入table
     * <p> 非必填
     */
    private Integer pageBegin;

    /**
     * table是否有边框
     * true：有（默认） false：无边框
     * <p> 非必填
     */
    private Boolean borderFlag;

    /**
     * 正文行高（表头不受影响）
     * 单位：pt，即point，等于1/72英寸
     * <p> 非必填
     */
    private String cellHeight;

    /**
     * Table中每个单元的水平对齐方式
     * (0：居左；1：居中；2：居右) 默认为0
     * <p> 非必填
     */
    private Integer cellHorizontalAlignment;

    /**
     * Table中每个单元的垂直对 齐方式
     * (4：居上；5：居中；6：居下) 默认为4
     * <p> 必填
     */
    private Integer cellVerticalAlignment;

    /**
     * 表头上方的一级标题
     * <p> 非必填
     */
    private String theFirstHeader;

    /**
     * 表头信息
     * <p> 必填
     */
    private String[] headers;

    /**
     * 表头对齐方式
     * (0居左；1居中；2居右) 默认0
     * <p> 非必填
     */
    private Integer headersAlignment;

    /**
     * 正文
     * (外层表示行，内层表示列)
     * <p> 非必填
     */
    private String[][] datas;

    /**
     * 各列宽度比例
     * 默认值：各列1:1
     * <p> 非必填
     */
    private Integer[] colWidthPercent;

    /**
     * table的水平对齐方式
     * (0居左，1居中，2居右) 默认1
     * <p> 非必填
     */
    private Integer tableHorizontalAlignment;

    /**
     * table宽度的百分比
     * (0<tableWidthPercentage<=100) 默认为100.0
     * <p> 非必填
     */
    private Float tableWidthPercentage;

    /**
     * 设置table居左居中居右后的水平偏移量
     * (向右偏移值为正数，向左偏移值 为负数)默认为0.0，单位px(像素)
     * <p> 非必填
     */
    private Float tableHorizontalOffset;

}
