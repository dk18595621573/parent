package com.cloud.webmvc.excel.listener;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.util.ReflectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
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
        AtomicBoolean hasValue = new AtomicBoolean(false);
        //此处使用 doWithFields() 可读取父类数据，若不想读取父类属性可使用 doWithLocalFields()
        ReflectionUtils.doWithFields(data.getClass(), field -> {
            field.setAccessible(true);
            if (!hasValue.get()) {
                boolean notEmpty = ObjectUtils.isNotEmpty(field.get(data));
                hasValue.set(notEmpty);
            }
        }, field -> field.isAnnotationPresent(ExcelProperty.class));
        if (hasValue.get()) {
            dataList.add(data);
        }
    }

    @Override
    public void doAfterAllAnalysed(final AnalysisContext context) {
        message = function.apply(dataList);
    }

    public String getMessage(){
        return message;
    }
}