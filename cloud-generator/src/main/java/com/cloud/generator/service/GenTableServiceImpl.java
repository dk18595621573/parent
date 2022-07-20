package com.cloud.generator.service;

import com.cloud.common.constant.Constants;
import com.cloud.common.utils.StringUtils;
import com.cloud.generator.config.GenConfig;
import com.cloud.generator.domain.GenTable;
import com.cloud.generator.domain.GenTableColumn;
import com.cloud.generator.mapper.GenTableMapper;
import com.cloud.generator.util.GenUtils;
import com.cloud.generator.util.VelocityInitializer;
import com.cloud.generator.util.VelocityUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 业务 服务层实现
 *
 * @author author
 */
public class GenTableServiceImpl implements IGenTableService {
    private static final Logger log = LoggerFactory.getLogger(GenTableServiceImpl.class);

    @Autowired
    private GenTableMapper genTableMapper;

    @Override
    public void genCode(final String[] tableNames, final GenConfig genConfig) throws FileNotFoundException {
        List<GenTable> tableList = genTableMapper.selectDbTableListByNames(tableNames);
        GenConfig config = ObjectUtils.defaultIfNull(genConfig, GenConfig.defaultConfig());
        for (GenTable table : tableList) {
            String tableName = table.getTableName();
            GenUtils.initTable(table, config);
            // 保存列信息
            List<GenTableColumn> genTableColumns = genTableMapper.selectDbTableColumnsByName(tableName);
            for (GenTableColumn column : genTableColumns) {
                GenUtils.initColumnField(column, table);
            }
            table.setColumns(genTableColumns);
        }
        OutputStream outputStream = new FileOutputStream(config.getPath());
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (GenTable genTable : tableList) {
            generatorCode(genTable, zip);
        }
        IOUtils.closeQuietly(zip);
    }

    /**
     * 查询表信息并生成代码
     */
    private void generatorCode(GenTable table, ZipOutputStream zip) {
        // 设置主键列信息
        setPkColumn(table);

        VelocityInitializer.initVelocity();

        VelocityContext context = VelocityUtils.prepareContext(table);

        // 获取模板列表
        List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory());
        for (String template : templates) {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, Constants.UTF8);
            tpl.merge(context, sw);
            try {
                // 添加到zip
                zip.putNextEntry(new ZipEntry(VelocityUtils.getFileName(template, table)));
                IOUtils.write(sw.toString(), zip, Constants.UTF8);
                IOUtils.closeQuietly(sw);
                zip.flush();
                zip.closeEntry();
            } catch (IOException e) {
                log.error("渲染模板失败，表名：" + table.getTableName(), e);
            }
        }
    }

    /**
     * 设置主键列信息
     *
     * @param table 业务表信息
     */
    public void setPkColumn(GenTable table) {
        for (GenTableColumn column : table.getColumns()) {
            if (column.isPk()) {
                table.setPkColumn(column);
                break;
            }
        }
        if (StringUtils.isNull(table.getPkColumn())) {
            table.setPkColumn(table.getColumns().get(0));
        }
    }

}