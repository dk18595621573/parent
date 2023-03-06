package com.cloud.component.express.domain;

import com.cloud.common.core.model.LogisticsRouteInfo;
import com.cloud.common.core.model.LogisticsStatus;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 快递响应结果
 *
 * @author bejson.com (i@bejson.com)
 * @link https://api.kuaidi100.com/document/5f0ffb5ebc8da837cbd8aefc
 */
@Data
public class ExpressResult implements Serializable {

    /**
     * 单号
     */
    private String nu;

    /** 快递公司名称 */
    private String company;

    /**
     * 快递公司编码,一律用小写字母
     */
    private String com;

    /**
     * 快递单当前状态，默认为0在途，1揽收，2疑难，3签收，4退签，5派件，8清关，14拒签等10个基础物流状态，如需要返回高级物流状态，请参考 resultv2 传值
     */
    private String state;

    /**
     * 最新查询结果，数组，包含多项，全量，倒序（即时间最新的在最前），每项都是对象，对象包含字段请展开
     */
    private List<ExpressItem> data;

    /** 行政区域解析 */
    private LogisticsRouteInfo routeInfo;

    /** 快递是否订阅 */
    private Boolean subscribed;

    /** 物流跟踪状态 */
    private String status;

    /**
     * 是否已签收
     * @return true:已签收 false:未签收
     */
    public boolean signed() {
        return LogisticsStatus.signed(this.getState());
    }

    /**
     * 是否拒签
     * @return true:拒签 false:不是拒签
     */
    public boolean rejected(){
        return LogisticsStatus.rejected(this.getState());
    }


    @Data
    public static class ExpressItem implements Serializable {

        /**
         * 格式化后时间.
         */
        private String ftime;

        /**
         * 内容.
         */
        private String context;

        /**
         * 时间，原始格式 2012-08-28 16:33:19
         */
        private String time;

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
}