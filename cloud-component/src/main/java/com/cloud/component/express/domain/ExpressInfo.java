package com.cloud.component.express.domain;

import lombok.Data;

@Data
public class ExpressInfo {

    /**
     * 内容  上海分拨中心/装件入车扫描
     */
    private String context;
    /**
     * 时间，原始格式 2012-08-28 16:33:19
     */
    private String time;
    /**
     * 时间，原始格式 2012-08-28 16:33:19
     */
    private String ftime;

    /**
     * 物流状态名称或者高级状态名称，提交resultv2=1或者resultv2=4标记后才会出现
     */
    private String status;

    /**
     *高级物流状态值，提交resultv2=4标记后才会出现  1002
     */
    private String statusCode;

    /**
     *行政区域的编码，提交resultv2=1或者resultv2=4标记后才会出现 310000000000
     */
    private String areaCode;

    /**
     *行政区域的名称，提交resultv2=1或者resultv2=4标记后才会出现  上海市
     */
    private String areaName;

    /**
     *行政区域经纬度，提交resultv2=4标记后才会出现  	17.200983,39.084158
     */
    private String areaCenter;

    /**
     *快件当前位置，提交resultv2=4标记后才会出现   深圳中心
     */
    private String location;

    /**
     *本行政区域拼音，提交resultv2=4标记后才会出现   tianjin
     */
    private String areaPinYin;

    /**
     *快递单号
     */
    private String expressNo;

    /**
     *快递公司编码
     */
    private String companyCode;

    /**
     *订单编号
     */
    private String orderCode;
}
