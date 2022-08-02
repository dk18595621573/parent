package com.cloud.webmvc.excel.listener;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.cloud.common.utils.StringUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;

/**
 * 全部导入处理 不分批次处理.
 *
 * @author zenghao
 * @date 2021/12/26
 */
public class ProcessAllListener<T> extends AnalysisEventListener<T> {

    private final Function<List<T>, String> function;

    public ProcessAllListener(final Function<List<T>, String> function) {
        this.function = function;
    }

    private final List<T> dataList = new ArrayList<>();

    private String message;

    @Override
    public void invoke(final T data, final AnalysisContext context) {
        // 过滤全是空的数据
        boolean exist = false;
        String[] fieldName = getFieldName(data);
        for (String string : fieldName) {
            Object fieldValue = getFieldValue(data, string);
            if (fieldValue instanceof String) {
                if (StringUtils.isNotBlank((String) fieldValue)) {
                    exist = true;
                }
            }
            if (!Objects.isNull(fieldValue)) {
                exist = true;
            }
        }
        if (!exist) {
            return;
        }
        dataList.add(data);
    }

    @Override
    public void doAfterAllAnalysed(final AnalysisContext context) {
        message = function.apply(dataList);
    }

    public String getMessage(){
        return message;
    }

    /**
     * 获取属性名数组
     */
    public static String[] getFieldName(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        List<String> fieldNames = new LinkedList<>();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].isAnnotationPresent(ExcelProperty.class)) {
                fieldNames.add(fields[i].getName());
            }
        }
        return fieldNames.toArray(String[]::new);
    }

    /**
     * 通过属性名获取属性值  忽略大小写
     *
     * @param o
     * @param name
     * @return
     * @throws Exception
     */

    public static Object getFieldValue(Object o, String name) {
        try {
            Field[] fields = o.getClass().getDeclaredFields();
            Object object = null;
            for (Field field : fields) {
                // 可以获取到私有属性
                field.setAccessible(true);
                if (field.getName().toUpperCase().equals(name.toUpperCase())) {
                    object = field.get(o);
                    break;
                }
            }
            return object;
        } catch (Exception e) {
            return false;
        }
    }
}