package com.cloud.dal.mybatis;

import com.cloud.common.core.page.Page;
import com.cloud.common.core.page.PageParam;
import com.cloud.common.utils.sql.SqlUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * 分页辅助.
 *
 * @author zenghao
 * @date 2022/6/15
 */
public class PageUtils {

    /**
     * 设置请求分页数据
     */
    public static void startPage(PageParam pageParam) {
        if (Objects.nonNull(pageParam)) {
            String orderBy = SqlUtil.escapeOrderBySql(pageParam.getOrderBy());
            PageHelper.startPage(pageParam.getPage(), pageParam.getSize(), orderBy).setReasonable(pageParam.getReasonable());
        }
    }

    /**
     * 清理分页的线程变量
     */
    public static void clearPage() {
        PageHelper.clearPage();
    }

    /**
     * PageHelper分页数据对象转换
     * @param page PageHelper分页数据对象
     * @return Page
     */
    public static <T> Page<T> fromList(List<T> page) {
        PageInfo<T> pageInfo = new PageInfo<>(page);
        Page<T> result = new Page<>(page, pageInfo.getTotal());
        clearPage();
        return result;
    }

    /**
     * PageHelper分页数据对象转换
     * @param page PageHelper分页数据对象
     * @return Page
     */
    public static <T, R> Page<R> fromList(List<T> page, Function<List<T>, List<R>> function) {
        PageInfo<T> pageInfo = new PageInfo<>(page);
        Page<R> result = new Page<>(function.apply(page), pageInfo.getTotal());
        clearPage();
        return result;
    }
}
