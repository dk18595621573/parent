package com.cloud.webmvc.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 数据导入对象.
 *
 * @author zhouyong
 * @date 2023/03/07
 */
@Data
@ApiModel(value = "批量导入VO")
public class BatchImport implements Serializable {

    @ApiModelProperty("导入批次号")
    private String batchNo;

    @ApiModelProperty("是否导入成功（true：成功；false：失败）")
    private boolean success;

    @ApiModelProperty("失败原因")
    private String errorMsg;

    @ApiModelProperty("导入失败文件地址")
    private String filepath;

    public static BatchImport of(final String batchNo) {
        return of(batchNo, null);
    }

    public static BatchImport of(final String batchNo, final String filepath) {
        BatchImport export = new BatchImport();
        export.setBatchNo(batchNo);
        export.setFilepath(filepath);
        return export;
    }
}
