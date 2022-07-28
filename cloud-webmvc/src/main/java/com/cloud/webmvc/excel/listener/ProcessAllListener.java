package com.cloud.webmvc.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;
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
        dataList.add(data);
    }

    @Override
    public void doAfterAllAnalysed(final AnalysisContext context) {
        message = function.apply(dataList);
    }

    public String getMessage(){
        return message;
    }
}