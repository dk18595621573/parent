package com.cloud.webmvc.excel.strategy;


import java.util.List;

/**
 * 多次写入.
 *
 * @author zenghao
 * @date 2023/5/10
 */
public interface MultipleWrite<T> {

    Class<T> getDataClass();

    List<T> datas();

    boolean hasData();

}
