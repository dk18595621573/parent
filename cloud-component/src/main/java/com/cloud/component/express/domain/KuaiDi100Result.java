/**
 * Copyright 2022 bejson.com
 */
package com.cloud.component.express.domain;

import lombok.Data;

import java.util.List;

/**
 * 快递100
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class KuaiDi100Result {

    public static final String SUCCESS_CODE = "200";

    public static final String QUERY_ERROR = "500";

    public static final String KEY_ERROR = "601";

    /**
     * 异常状态吗.
     */
    private String returnCode;

    /**
     * 消息体，请忽略
     */
    private String message;

    /**
     * 单号
     */
    private String nu;

    /**
     * 是否签收标记，0未签收，1已签收，请忽略，明细状态请参考state字段
     */
    private String ischeck;

    /**
     * 快递单明细状态标记，暂未实现，请忽略
     */
    private String condition;

    /**
     * 快递公司编码,一律用小写字母
     */
    private String com;

    /**
     * 通讯状态，请忽略
     */
    private String status;

    /**
     * 快递单当前状态，默认为0在途，1揽收，2疑难，3签收，4退签，5派件，8清关，14拒签等10个基础物流状态，如需要返回高级物流状态，请参考 resultv2 传值
     */
    private String state;

    /**
     * 最新查询结果，数组，包含多项，全量，倒序（即时间最新的在最前），每项都是对象，对象包含字段请展开
     */
    private List<KuaiDi100Express> data;


    @Data
    public static class KuaiDi100Express {

        /**
         * 格式化后时间.
         */
        private String ftime;

        /**
         * 内容.
         */
        private String context;


    }
}