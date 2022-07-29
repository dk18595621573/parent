package com.cloud.webmvc.excel;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.cloud.common.exception.ServiceException;
import com.cloud.common.utils.StringUtils;
import com.cloud.webmvc.excel.listener.ProcessAllListener;
import com.cloud.webmvc.excel.listener.ProcessBatchListener;
import com.cloud.webmvc.excel.listener.ProcessRowListener;
import com.cloud.webmvc.excel.model.RowRange;
import com.google.common.collect.Maps;
import lombok.experimental.UtilityClass;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * excel合并工具类.
 *
 * @author zenghao
 * @date 2021/12/26
 */
@UtilityClass
public class ExcelUtil {

    /**
     * 获取合并策略.
     * @param dataList 数据列表.
     * @param fieldList 需要合并的字段列表.
     * @param predicate 合并条件.
     * @param <T> 数据列表的数据类型
     * @return 行合并策略
     */
    public static <T> RowMergeStrategy rowMerge(final List<T> dataList, final List<String> fieldList, final BiPredicate<T, T> predicate) {
        Map<String, List<RowRange>> strategyMap = Maps.newHashMap();
        T prevData = null;
        for (int row = 0, size = dataList.size(); row < size; row++) {
            T currData = dataList.get(row);
            //从第二行开始判断 符合条件则可合并
            if (Objects.nonNull(prevData) && predicate.test(prevData, currData)) {
                List<RowRange> rowRangeDtoList;
                for (String mergeField : fieldList) {
                    //获取该列所有合并策略
                    rowRangeDtoList = strategyMap.getOrDefault(mergeField, new ArrayList<>());
                    //因为是ArrayList，只需要考虑当前最后一条数据的结束行是否为当前行，如果是当前行说明是同一个订单
                    if (CollUtil.isNotEmpty(rowRangeDtoList) && rowRangeDtoList.get(rowRangeDtoList.size() - 1).getEnd() == row) {
                        rowRangeDtoList.get(rowRangeDtoList.size() - 1).setEnd(row + 1);
                    } else {
                        rowRangeDtoList.add(new RowRange(row, row + 1));
                    }
                    strategyMap.put(mergeField, rowRangeDtoList);
                }
            }
            prevData = currData;
        }
        return new RowMergeStrategy(strategyMap);
    }

    /**
     * 写文件
     * @param outputStream 输出流
     * @param datas 列表数据
     * @param clazz 数据类型
     * @param <T> 列表数据类型
     */
    public static <T> void write(OutputStream outputStream, List<T> datas, Class<T> clazz) {
        EasyExcel.write(outputStream, clazz)
            .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
            .sheet()
            .doWrite(datas);
    }

    /**
     * 写文件
     * @param outputStream 输出流
     * @param sheetName 第一个sheet页名称
     * @param datas 列表数据
     * @param clazz 数据类型
     * @param <T> 列表数据类型
     */
    public static <T> void write(OutputStream outputStream, String sheetName, List<T> datas, Class<T> clazz) {
        EasyExcel.write(outputStream, clazz)
            .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
            .sheet(sheetName)
            .doWrite(datas);
    }

    /**
     * 全部读取 直接读取整个excel
     * @param inputStream excel输入流
     * @param function 读取完成后操作事件
     * @param clazz 读取的数据类型
     * @return 读取结果
     * @param <T> 读取的数据类型
     */
    public static <T> String readAll(InputStream inputStream, Function<List<T>, String> function, Class<T> clazz) {
        ProcessAllListener<T> listener = new ProcessAllListener<>(list -> {
            if (StringUtils.isNull(list) || list.size() == 0) {
                throw new ServiceException("导入数据不能为空！");
            }
            return function.apply(list);
        });

        EasyExcel.read(inputStream, clazz, listener).sheet().doRead();
        return listener.getMessage();
    }

    /**
     * 逐行读取
     * @param inputStream excel输入流
     * @param consumer 读取每行操作事件
     * @param clazz 读取的数据类型
     * @return 读取结果
     * @param <T> 读取的数据类型
     */
    public static <T> List<Integer> readRow(InputStream inputStream, Consumer<T> consumer, Class<T> clazz) {
        ProcessRowListener<T> listener = new ProcessRowListener<>(consumer);
        EasyExcel.read(inputStream, clazz, listener).sheet().doRead();
        return listener.getErrors();
    }

    /**
     * 批量读取
     * @param inputStream excel输入流
     * @param consumer 批量操作事件
     * @param batch 读取达到指定次数后操作事件
     * @param clazz 读取的数据类型
     * @param <T> 读取的数据类型
     */
    public static <T> void readBatch(InputStream inputStream, Consumer<List<T>> consumer, Class<T> clazz, Integer batch) {
        ProcessBatchListener<T> listener = new ProcessBatchListener<>(batch, consumer);
        EasyExcel.read(inputStream, clazz, listener).sheet().doRead();
    }

    /**
     * 批量读取 默认100行进行一次操作
     * @param inputStream excel输入流
     * @param consumer 批量操作事件
     * @param clazz 读取的数据类型
     * @param <T> 读取的数据类型
     */
    public static <T> void readBatch(InputStream inputStream, Consumer<List<T>> consumer, Class<T> clazz) {
        readBatch(inputStream, consumer,clazz, null);
    }
}
