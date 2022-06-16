package com.cloud.common.core.excel;

/**
 * Excel数据格式处理适配器
 *
 * @author author
 */
public interface ExcelHandlerAdapter {
    /**
     * 格式化
     *
     * @param value 单元格数据值
     * @param args  excel注解args参数组
     * @return 处理后的值
     */
    Object format(Object value, String[] args);
}
