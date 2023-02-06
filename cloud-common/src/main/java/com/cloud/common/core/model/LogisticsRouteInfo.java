package com.cloud.common.core.model;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * @author nlsm
 * 快递行政区域
 */
@Data
public class LogisticsRouteInfo implements Serializable {

    /** 出发地城市信息 */
    private Info from;

    /** 当前城市信息 */
    private Info cur;

    /** 目的地城市信息 */
    private Info to;

    @Data
    @Accessors(chain = true)
    public static class Info implements Serializable{
        /** 省市区编码截取位置 */
        private static final int PROVINCE_AREA_CODE_SUB = 8;
        /** 省市编码截取位置 */
        private static final int PROVINCE_CITY_CODE_SUB = 6;
        /** 区域编码为空 */
        private static final String NULL_DISTRICT = "00";

        /** 行政区域编码 */
        private String number;
        /** 省市区名称 */
        private String name;

        /**
         * 获取区域编码：省、市、区。不需要到镇级别
         * @param toArea 是否到区级别(true省市区 false省市)
         * @return 省市区编码
         */
        public String getAreaCode(Boolean toArea) {
            if (Boolean.TRUE.equals(toArea)){
                return StrUtil.subPre(this.number, PROVINCE_AREA_CODE_SUB);
            }else {
                return StrUtil.subPre(this.number, PROVINCE_CITY_CODE_SUB);
            }
        }

        /**
         * 校验地区编码是否到达地区级别
         * @return true到达地区级别 false没有到达地区级别
         */
        public Boolean checkAreaCode(){
            String areaCode = StrUtil.sub(this.number, PROVINCE_AREA_CODE_SUB - 2, PROVINCE_AREA_CODE_SUB);
            return !NULL_DISTRICT.equals(areaCode);
        }
    }
}
