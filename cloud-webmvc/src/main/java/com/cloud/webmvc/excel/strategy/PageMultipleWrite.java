package com.cloud.webmvc.excel.strategy;

import cn.hutool.core.collection.CollUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.function.Function;

/**
 * 分页写入excel处理.
 *
 * @author zenghao
 * @date 2023/5/11
 */
public class PageMultipleWrite<T> implements MultipleWrite<T> {

    public PageMultipleWrite(final Function<Integer, List<T>> function) {
        this.function = function;
        Type superClass = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) superClass;
        Type[] typeArgs = parameterizedType.getActualTypeArguments();
        this.clazz = (Class<T>) typeArgs[0];
    }

    public PageMultipleWrite(final Function<Integer, List<T>> function, final Class<T> clazz) {
        this.function = function;
        this.clazz = clazz;
    }

    /**
     * 获取指定页数据的方法
     */
    private final Function<Integer, List<T>> function;

    /**
     * 泛型数据类型
     */
    private final Class<T> clazz;

    /**
     * 数据集合
     */
    private List<T> datas;
    /**
     * 页码 初始默认第一页
     */
    private int page = 1;

    @Override
    public Class<T> getDataClass()  {
        return clazz;
    }

    @Override
    public List<T> datas() {
        page++;
        return datas;
    }

    @Override
    public boolean hasData() {
        this.datas = function.apply(page);
        return CollUtil.isNotEmpty(this.datas);
    }
}
