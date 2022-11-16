package com.cloud.component.express.domain;

import lombok.Data;

import java.util.List;

/**
 * @author nlsm
 */
@Data
public class LastResult {

    /**
     * 消息体 （忽略）
     */
    private String message;
    /**
     *快递单当前状态，默认为0在途，1揽收，2疑难，3签收，4退签，5派件，8清关，14拒签等10个基础物流状态，
     * 如需要返回高级物流状态，请参考 resultv2 传值
     */
    private String state;

    /**
     * 通讯状态，请忽略
     */
    private String status;

    /**
     * 是否签收标记，0未签收，1已签收
     */
    private String ischeck;

    /**
     * 快递单号
     */
    private String nu;

    /**
     * 快递公司编码,一律用小写字母
     */
    private String com;

    /**
     * 快递明细
     */
    private List<ExpressInfo> data;
    /**
     * 无用字段
     */
    private String condition;

    /**
     * 出发地，目的地信息
     */
    private ExpressResult.RouteInfo routeInfo;

    private boolean isLoop;
}
