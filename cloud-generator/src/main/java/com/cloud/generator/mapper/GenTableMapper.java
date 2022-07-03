package com.cloud.generator.mapper;

import com.cloud.generator.domain.GenTable;
import com.cloud.generator.domain.GenTableColumn;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 业务 数据层
 *
 * @author author
 */
public interface GenTableMapper {

    /**
     * 根据表名称查询列信息
     *
     * @param tableName 表名称
     * @return 列信息
     */
    @Select("select column_name, " +
        "(case when (is_nullable = 'no' && column_key != 'PRI') then '1' else null end) as is_required, " +
        "(case when column_key = 'PRI' then '1' else '0' end) as is_pk, ordinal_position as sort," +
        "column_comment, " +
        "(case when extra = 'auto_increment' then '1' else '0' end) as is_increment, " +
        "column_type" +
        "  from information_schema.columns " +
        "where table_schema = (select database())" +
        "  and table_name = (#{tableName})" +
        "order by ordinal_position")
    List<GenTableColumn> selectDbTableColumnsByName(String tableName);

    /**
     * 查询据库列表
     *
     * @param genTable 业务信息
     * @return 数据库表集合
     */
    @Select("<script>" +
        "select table_name, table_comment, create_time, update_time from information_schema.tables" +
        "        where table_schema = (select database())" +
        "        AND table_name NOT LIKE 'qrtz_%' AND table_name NOT LIKE 'gen_%'" +
        "        AND table_name NOT IN (select table_name from gen_table)" +
        "        <if test=\"tableName != null and tableName != ''\">" +
        "            AND lower(table_name) like lower(concat('%', #{tableName}, '%'))" +
        "        </if>" +
        "        <if test=\"tableComment != null and tableComment != ''\">" +
        "            AND lower(table_comment) like lower(concat('%', #{tableComment}, '%'))" +
        "        </if>" +
        "        <if test=\"params.beginTime != null and params.beginTime != ''\">" +
        "            AND date_format(create_time,'%y%m%d') &gt;= date_format(#{params.beginTime},'%y%m%d')" +
        "        </if>" +
        "        <if test=\"params.endTime != null and params.endTime != ''\">" +
        "            AND date_format(create_time,'%y%m%d') &lt;= date_format(#{params.endTime},'%y%m%d')" +
        "        </if>" +
        "        order by create_time desc" +
        "</script>")
    List<GenTable> selectDbTableList(GenTable genTable);

    /**
     * 查询据库列表
     *
     * @param tableNames 表名称组
     * @return 数据库表集合
     */
    @Select("<script>" +
        "select table_name, table_comment, create_time, update_time from information_schema.tables" +
        "        where table_schema = (select database()) and table_name NOT LIKE 'qrtz_%'" +
        "        and table_name in" +
        "        <foreach collection=\"array\" item=\"name\" open=\"(\" separator=\",\" close=\")\">" +
        "            #{name}" +
        "        </foreach>" +
        "</script>")
    List<GenTable> selectDbTableListByNames(String[] tableNames);

}
