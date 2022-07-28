package com.cloud.webmvc.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 批量读取.
 *
 * @author zenghao
 * @date 2022/7/28
 */
public class ProcessBatchListener<T> extends AnalysisEventListener<T> {

    private static final int DEFAULT_BATCH_COUNT = 100;
    private final List<T> dataList;

    private final int batchCount;
    private final Consumer<List<T>> consumer;

    public ProcessBatchListener(final Consumer<List<T>> consumer) {
        this(DEFAULT_BATCH_COUNT, consumer);
    }

    public ProcessBatchListener(final Integer batchCount, final Consumer<List<T>> consumer) {
        this.batchCount = ObjectUtils.defaultIfNull(batchCount, DEFAULT_BATCH_COUNT);
        this.consumer = consumer;
        this.dataList = new ArrayList<>(this.batchCount);
    }


    @Override
    public void invoke(T data, AnalysisContext context) {
        dataList.add(data);
        if (dataList.size() >= batchCount) {
            consumer.accept(dataList);
            dataList.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        if (CollectionUtils.isNotEmpty(dataList)) {
            consumer.accept(dataList);
            dataList.clear();
        }
    }
}
