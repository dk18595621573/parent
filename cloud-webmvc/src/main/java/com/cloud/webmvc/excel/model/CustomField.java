package com.cloud.webmvc.excel.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 自定义导出字段.
 *
 * @author dell
 */
@Data
@ApiModel(value = "自定义导出字段")
public class CustomField implements Serializable {

    private static final long serialVersionUID = -2235572164075251271L;

    @ApiModelProperty("字段英文名")
    private String englishName;

    @ApiModelProperty("字段中文名")
    private String chineseName;

    @ApiModelProperty("是否被选中")
    private Boolean checked;

    @ApiModelProperty("是否合并字段")
    private Boolean merged;

    public CustomField(final String englishName, final String chineseName, final Boolean checked, final Boolean merged) {
        this.englishName = englishName;
        this.chineseName = chineseName;
        this.checked = checked;
        this.merged = merged;
    }
}
