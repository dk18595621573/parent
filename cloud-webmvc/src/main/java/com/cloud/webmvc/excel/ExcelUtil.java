package com.cloud.webmvc.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.handler.WriteHandler;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.cloud.common.exception.ServiceException;
import com.cloud.common.utils.StringUtils;
import com.cloud.webmvc.excel.listener.ProcessAllListener;
import com.cloud.webmvc.excel.listener.ProcessBatchListener;
import com.cloud.webmvc.excel.listener.ProcessRowListener;
import com.cloud.webmvc.excel.strategy.MultipleWrite;
import com.cloud.webmvc.excel.strategy.RowMergeStrategy;
import lombok.experimental.UtilityClass;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
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
     * 合并行写文件.
     * @param dataList 数据列表.
     * @param fieldList 需要合并的字段列表.
     * @param <T> 数据列表的数据类型
     */
    public static <T> void writeMerge(final OutputStream outputStream, final List<T> dataList, final Class<T> clazz, final List<String> fieldList) {
        EasyExcel.write(outputStream, clazz)
            .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
            .registerWriteHandler(new RowMergeStrategy(fieldList))
            .sheet()
            .doWrite(dataList);
    }

    /**
     * 合并行写文件.
     * @param dataList 数据列表.
     * @param clazz 写入数据类型.
     * @param fieldList 需要合并的字段列表.
     * @param mergeWithFirstField 是否以第一列数据为基准合并.
     * @param <T> 数据列表的数据类型
     */
    public static <T> void writeMerge(final OutputStream outputStream, final List<T> dataList, final Class<T> clazz, final List<String> fieldList, final boolean mergeWithFirstField) {
        EasyExcel.write(outputStream, clazz)
            .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
            .registerWriteHandler(new RowMergeStrategy(fieldList, mergeWithFirstField))
            .sheet()
            .doWrite(dataList);
    }

    /**
     * 写文件.
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
     * 写文件.
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
     * 写文件.
     * @param outputStream 输出流
     * @param sheetName 第一个sheet页名称
     * @param datas 列表数据
     * @param clazz 数据类型
     * @param <T> 列表数据类型
     * @param writeHandler 拦截器对象
     */
    public static <T> void write(OutputStream outputStream, String sheetName, List<T> datas, Class<T> clazz, WriteHandler writeHandler) {
        EasyExcel.write(outputStream, clazz)
                .registerWriteHandler(writeHandler)
                .sheet(sheetName)
                .doWrite(datas);
    }

    /**
     * 分批次写入文件.
     * @param outputStream 输出流
     * @param sheetName 第一个sheet页名称
     * @param multipleWrite 分次写入方法
     * @param <T> 列表数据类型
     */
    public static <T> void batchWrite(OutputStream outputStream, String sheetName, MultipleWrite<T> multipleWrite) {
        batchWrite(outputStream, sheetName, new LongestMatchColumnWidthStyleStrategy(), multipleWrite);
    }

    /**
     * 分批次写入文件.
     * @param outputStream 输出流
     * @param sheetName 第一个sheet页名称
     * @param multipleWrite 分次写入方法
     * @param <T> 列表数据类型
     * @param writeHandler 拦截器对象
     */
    public static <T> void batchWrite(OutputStream outputStream, String sheetName, WriteHandler writeHandler, MultipleWrite<T> multipleWrite) {
        ExcelWriter write = EasyExcel.write(outputStream, multipleWrite.getDataClass())
            .registerWriteHandler(writeHandler).build();
        WriteSheet sheet = EasyExcel.writerSheet(sheetName).build();

        while (multipleWrite.hasData()) {
            write.write(multipleWrite.datas(), sheet);
        }
        write.finish();
    }

    /**
     * 全部读取 直接读取整个excel.
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
     * 逐行读取.
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
     * 分批次读取.
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
     * 分批次读取 默认 每100行 进行一次操作.
     * @param inputStream excel输入流
     * @param consumer 批量操作事件
     * @param clazz 读取的数据类型
     * @param <T> 读取的数据类型
     */
    public static <T> void readBatch(InputStream inputStream, Consumer<List<T>> consumer, Class<T> clazz) {
        readBatch(inputStream, consumer,clazz, null);
    }
}
