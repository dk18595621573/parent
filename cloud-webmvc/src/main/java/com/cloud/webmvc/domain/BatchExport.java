package com.cloud.webmvc.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 数据导出对象.
 *
 * @author zenghao
 * @date 2022/7/29
 */
@Data
@ApiModel(value = "批量导出VO")
public class BatchExport implements Serializable {

    @ApiModelProperty("导出批次号")
    private String batchNo;

    @ApiModelProperty("导出文件地址")
    private String filepath;

    public static BatchExport of(final String batchNo) {
        return of(batchNo, null);
    }

    public static BatchExport of(final String batchNo, final String filepath) {
        BatchExport export = new BatchExport();
        export.setBatchNo(batchNo);
        export.setFilepath(filepath);
        return export;
    }
}
