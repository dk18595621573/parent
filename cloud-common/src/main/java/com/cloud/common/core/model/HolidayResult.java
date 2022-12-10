package com.cloud.common.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author nlsm
 * 组装返回转换类
 */
@Data
@Accessors(chain = true)
public class HolidayResult implements Serializable {

    /** 年份 */
    private Integer year;

    /** 节假日类型 */
    private Integer holidayType;

    /** 节假日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date holiday;

    /** 节假日说明 */
    private String remark;
}
