package com.cloud.component.fadada.request;

import lombok.Data;

/**
 * 模板填充
 *
 * @author mft
 */
@Data
public class GenerateContractRequest {

    /**
     * 文档标题
     * <p> 非必填
     */
    private String docTitle;

    /**
     * 模板编号
     * <p> 必填
     */
    private String templateId;

    /**
     * 合同编号
     * <p> 必填
     */
    private String contractId;

    /**
     * 字体大小
     * 如传入该参数，则按照该参数值设置字体大小；参
     * 考word字体设置，例如：10,12,12.5,14；
     * 若未传入该参数，则文本域字体大小按照文本域设
     * 置时的大小（文本域设置为自动，则由系统计算字
     * 体大小）
     * <p> 非必填
     */
    private String fontSize;

    /**
     * 字体类型
     * 默认0-宋体；
     * 0-宋体；1-仿宋；2-黑体；3-楷体；4-微软雅黑
     * <p> 非必填
     */
    private String fontType;

    /**
     * 填充内容
     * JsonObject字符串
     * key为文本域，value为要填充的值，value值传字符
     * <p> 必填
     */
    private String parameterMap;

    /**
     * 动态表格
     * JsonArray[dynamic_table]字符串
     * 每个dynamic_table 元素表示一个动态表单，可以
     * 生成多个表格。
     * <p> 非必填
     */
    private String dynamicTables;

}
