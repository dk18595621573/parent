package com.cloud.component.holiday;

import com.cloud.component.holiday.domain.HolidayResult;

import java.util.List;

/**
 * @author nlsm
 * 节假日拉取接口
 */
public interface Holiday {

    /**
     * 获取节假日数据
     * @param year 年份
     * @param month 月份
     * @return 转换之后的节假日信息
     */
    List<HolidayResult> getDay(Integer year, Integer month);

}
