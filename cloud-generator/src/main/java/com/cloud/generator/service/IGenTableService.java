package com.cloud.generator.service;

import com.cloud.generator.domain.GenTable;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * 业务 服务层
 *
 * @author author
 */
public interface IGenTableService {

    /**
     * 查询据库列表
     *
     * @param tableNames 表名称组
     * @return 数据库表集合
     */
    List<GenTable> selectDbTableListByNames(String[] tableNames);

    /**
     * 批量生成代码（下载方式）
     *
     * @param tableList 表数组
     * @return 数据
     */
    void downloadCode(List<GenTable> tableList, String path) throws FileNotFoundException;

}
