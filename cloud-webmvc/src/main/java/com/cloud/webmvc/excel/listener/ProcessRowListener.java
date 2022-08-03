package com.cloud.webmvc.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 逐行读取excel.
 *
 * @author zenghao
 * @date 2022/7/28
 */
@Slf4j
public class ProcessRowListener<T> extends AnalysisEventListener<T> {

    private final Consumer<T> consumer;

    public ProcessRowListener(final Consumer<T> consumer) {
        this.consumer = consumer;
    }

    private final List<Integer> errors = new ArrayList<>();

    @Override
    public void onException(final Exception exception, final AnalysisContext context) {
        Integer rowIndex = context.readRowHolder().getRowIndex();
        log.warn("读取Excel第{}行出现异常", rowIndex);
        errors.add(rowIndex);
    }

    @Override
    public void invoke(final T data, final AnalysisContext context) {
        consumer.accept(data);
    }

    @Override
    public void doAfterAllAnalysed(final AnalysisContext context) {
        log.info("读取Excel完成，共{}行数据", context.readRowHolder().getRowIndex());
    }

    public List<Integer> getErrors() {
        return errors;
    }
}
