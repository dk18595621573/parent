package com.cloud.generator.service;

import com.cloud.generator.config.GenConfig;

import java.io.FileNotFoundException;

/**
 * 业务 服务层
 *
 * @author author
 */
public interface IGenTableService {

    /**
     * 代码生成
     * @param tableNames 表名称组
     * @param genConfig 代码生成配置
     * @throws FileNotFoundException
     */
    void genCode(String[] tableNames, GenConfig genConfig) throws FileNotFoundException;

}
