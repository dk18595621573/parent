package com.cloud.common.enums;

import java.util.Objects;

/**
 * @author nlsm
 * 节假日枚举
 */

public enum HolidayType {

    /** 工作日 */
    WORKDAY(0, "工作日"),
    /** 节假日 */
    WEEKEND(1, "周末"),
    /** 周末 */
    HOLIDAY(2, "节假日"),
    /** 调休(上班) */
    TAKE_WORKING_DAYS_OFF(3, "调休");

    private final Integer code;
    private final String remark;
    HolidayType(int code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    public Integer getCode() {
        return code;
    }

    public String getRemark() {
        return remark;
    }

    public static HolidayType fromCode(Integer code) {
        if (Objects.nonNull(code)) {
            for (HolidayType status : HolidayType.values()) {
                if (status.getCode().equals(code)) {
                    return status;
                }
            }
        }
        return HolidayType.HOLIDAY;
    }
}
