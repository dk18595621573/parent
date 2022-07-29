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
@ApiModel("批量导出VO")
public class BatchExport implements Serializable {

    @ApiModelProperty("导出批次号")
    private String batchNo;

    @ApiModelProperty("导出文件地址")
    private String filepath;
}
