package com.cloud.component.holiday.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cloud.common.enums.HolidayType;
import com.cloud.common.exception.ServiceException;
import com.cloud.component.holiday.Holiday;
import com.cloud.component.holiday.domain.HolidayResult;
import com.cloud.component.util.HttpClientUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author nlsm
 * 节假日调用(不是很稳定，更新不及时),详情见官方文档：<a href="http://www.apihubs.cn/#/holiday">www.apihubs.cn/#/holiday</a>
 */
@Slf4j
@Component
public class ApiHubsHoliday implements Holiday {

    /** 调用接口地址 */
    private static final String API_HOLIDAY_URL = "https://api.apihubs.cn/holiday/get";

    @Override
    public List<HolidayResult> getDay(Integer year, Integer month) {
        // 判断传入的参数是否有问题
        if (Objects.isNull(year)){
            throw new ServiceException("传入年份为空数据");
        }
        Map<String, String> params = new HashMap<>(8);
        params.put("year", String.valueOf(year));
        params.put("cn", "1");
        params.put("page", "1");
        params.put("size", "366");
        //调用接口
        String response = HttpClientUtil.doHttpGet(API_HOLIDAY_URL, params);
        log.info("调用接口坞--->调用参数{},返回参数{}", API_HOLIDAY_URL, response);
        List<HolidayResult> holidayResultList = new ArrayList<>();
        try {
            if (StrUtil.isNotBlank(response)){
                //转换json操作
                JSONObject jsonObject = JSONUtil.parseObj(response);
                JSONObject data = jsonObject.getJSONObject("data");
                //转换对象
                List<ApiHubsHoliday.Param> holidayResults = JSONUtil.toList(data.getJSONArray("list"), ApiHubsHoliday.Param.class);
                for (ApiHubsHoliday.Param result : holidayResults) {
                    HolidayResult holidayResult = new HolidayResult().setHoliday(DateUtil.parse(result.getDateCn()))
                            .setRemark(result.getHolidayCn() + "-" + result.getWeekendCn() + "(" + result.getHolidayOvertimeCn() + ")")
                            .setYear(result.getYear());
                    //是否为工作日，不是工作日=节假日（包括周六周日），是工作日=工作日（包括调休）
                    if (result.getWorkday() == 2){
                        holidayResult.setHolidayType(HolidayType.HOLIDAY.getCode());
                        holidayResultList.add(holidayResult);
                    }
                }
            }
        } catch (Exception e){
            log.error("操作失败,失败原因:{}", e.getMessage());
            e.printStackTrace();
        }
        return holidayResultList;
    }

    @Data
    static class Param{
        private Integer year;
        private Integer month;
        private Integer date;
        private Integer yearweek;
        private Integer yearday;
        private Integer lunarYear;
        private Integer lunarMonth;
        private Integer lunarDate;
        private Integer lunarYearday;
        private Integer week;
        private Integer weekend;
        private Integer workday;
        private Integer holiday;
        private Integer holidayOr;
        private Integer holidayOvertime;
        private Integer holidayToday;
        private Integer holidayLegal;
        private Integer holidayRecess;
        private String yearCn;
        private String monthCn;
        private String dateCn;
        private String yearweekCn;
        private String yeardayCn;
        private String lunarYearCn;
        private String lunarMonthCn;
        private String lunarDateCn;
        private String lunarYeardayCn;
        private String weekCn;
        private String weekendCn;
        private String workdayCn;
        private String holidayCn;
        private String holidayOrCn;
        private String holidayOvertimeCn;
        private String holidayTodayCn;
        private String holidayLegalCn;
        private String holidayRecessCn;
    }

    public static void main(String[] args) {
        Holiday holiday = new ApiHubsHoliday();
        List<HolidayResult> resultList = holiday.getDay(2022, null);
        System.out.println("节假日操作---" + JSONUtil.toJsonStr(resultList));
    }
}
