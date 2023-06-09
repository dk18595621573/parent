package com.cloud.component.holiday.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cloud.common.core.model.HolidayResult;
import com.cloud.common.enums.HolidayType;
import com.cloud.common.exception.ServiceException;
import com.cloud.component.holiday.Holiday;
import com.cloud.component.properties.HolidayProperties;
import com.cloud.component.util.HttpClientUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author nlsm
 * 提莫的神秘小店免费节假日API，官网：<a href="http://timor.tech/api/holiday/">http://timor.tech/api/holiday/</a>
 */
@Slf4j
@AllArgsConstructor
public class TimorHoliday implements Holiday {

    private HolidayProperties properties;
    /**
     * 请求成功
     */
    private static final int SUCCEED = 0;

    @Override
    public List<HolidayResult> getDay(Integer year, Integer month) {
        // 判断传入的参数是否有问题
        if (Objects.isNull(year)) {
            throw new ServiceException("查询年份不能为空");
        }
        String yearMonth = Objects.nonNull(month) ? StrUtil.format("{}-{}", year, month) : String.valueOf(year);
        String sendUrl = properties.getTimorUrl() + yearMonth;
        Map<String, String> params = new HashMap<>(4);
        params.put("type", "Y");
        params.put("week", "Y");
        String httpGet = HttpClientUtil.doHttpGet(sendUrl, params);
        log.info("提莫的神秘小店-->调用地址:{}，接口参数:{}，返回结果:{}", sendUrl, params, httpGet);
        if (StrUtil.isBlank(httpGet)) {
            return null;
        }
        // 将调用结果转换
        JSONObject jsonObject = JSONUtil.parseObj(httpGet);
        Integer code = jsonObject.getInt("code");
        if (SUCCEED != code) {
            // 查询失败
            return null;
        }
        List<HolidayResult> holidayResultList = new ArrayList<>();
        try {
            Map<String, JSONObject> holiday = jsonObject.get("holiday", Map.class);
            Map<String, JSONObject> type = jsonObject.get("type", Map.class);
            holiday.forEach((day, data) -> {
                TimorHoliday.Param timor = JSONUtil.toBean(data, TimorHoliday.Param.class);
                // 获取节假日类型
                JSONObject typeJsonObject = type.get(timor.date);
                HolidayResult result = new HolidayResult().setYear(year).setHoliday(DateUtil.parse(timor.getDate())).setRemark(timor.getName());
                if (Objects.isNull(typeJsonObject)) {
                    result.setHolidayType(HolidayType.HOLIDAY.getCode());
                } else {
                    result.setHolidayType(typeJsonObject.getInt("type"));
                }
                holidayResultList.add(result);
            });
        } catch (Exception e) {
            log.error("提莫的神秘小店-->转换出现异常", e);
        }
        return holidayResultList;
    }

    /**
     * 节假日返回参数转换接收
     */
    @Data
    static class Param {
        /**
         * 放假为true 调休上班false
         */
        private Boolean holiday;

        /**
         * 节假日名称
         */
        private String name;

        /**
         * 节假日期
         */
        private String date;
    }

}
