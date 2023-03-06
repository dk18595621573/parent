package com.cloud.component.express.domain;

import com.cloud.common.core.model.LogisticsRouteInfo;
import com.cloud.common.core.model.LogisticsStatus;
import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * @author nlsm
 */
@Data
public class LastResult {

    /**
     *  快递已签收
     */
    public static final String CHECK_SIGN = "1";
    /**
     *  快递未签收
     */
    public static final String NO_SIGN = "0";

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

    /** 物流公司编码 */
    private String comName;

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
    private LogisticsRouteInfo routeInfo;

    private boolean isLoop;

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


    /**
     * 是否拒签
     * @return true:拒签 false:不是拒签
     */
    public boolean checkRejected() {
        if (!LogisticsStatus.SubState.BACK_TO_SIGN.getCode().equals(getState())) {
            return Boolean.TRUE;
        }
        LogisticsRouteInfo route = getRouteInfo();
        if (Objects.isNull(route)){
            return Boolean.TRUE;
        }
        LogisticsRouteInfo.Info from = route.getFrom();
        LogisticsRouteInfo.Info to = route.getTo();
        if (!Objects.isNull(from) && !Objects.isNull(to)) {
            // 判断出发地是否只有省市区
            return Objects.equals(from.getAreaCode(true), to.getAreaCode(true));
        }
        return Boolean.TRUE;
    }

}
