package com.cloud.component.express.domain;

import cn.hutool.core.util.ArrayUtil;
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

    /** 签收状态 */
    interface State{
        /** 快件已签收 */
        String SIGN = "3";
        /** 本人签收 */
        String SELF_SIGN = "301";
        /** 派件异常后签收 */
        String ERROR_SIGN = "302";
        /** 代签 */
        String HOLOGRAPH_SIGN = "303";
        /** 投柜或站签收 */
        String CAST_ARK_SIGN = "304";

        /** 退签 */
        String BACK_TO_SIGN = "4";

        /** 已销单 */
        String ALREADY_PIN_SINGLE = "401";

        /** 拒签 */
        String VISA_REJECTED = "14";

    }

    /** 签收状态集合 */
    public final static String[] SIGN_SIGN_ARRAY = {State.SIGN, State.SELF_SIGN, State.ERROR_SIGN, State.HOLOGRAPH_SIGN, State.CAST_ARK_SIGN};
    /** 拒签状态集合 */
    public final static String[] VISA_SIGN_ARRAY = {State.ALREADY_PIN_SINGLE, State.BACK_TO_SIGN, State.VISA_REJECTED};

    /**
     * 单号
     */
    private String nu;

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
    private RouteInfo routeInfo;

    /**
     * 是否已签收
     * @return true:已签收 false:未签收
     */
    public boolean signed() {
        return ArrayUtil.contains(SIGN_SIGN_ARRAY, getState());
    }

    /**
     * 是否拒签
     * @return true:拒签 false:不是拒签
     */
    public boolean rejected(){
        return ArrayUtil.contains(VISA_SIGN_ARRAY, getState());
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

    }

    @Data
    public static class RouteInfo implements Serializable{

        /** 出发地城市信息 */
        private Info from;

        /** 当前城市信息 */
        private Info cur;

        /** 目的地城市信息 */
        private Info to;
    }

    @Data
    public static class Info{
        /** 行政区域编码 */
        private String number;
        /** 省市区名称 */
        private String name;
    }
}