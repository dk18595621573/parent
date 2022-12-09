package com.cloud.component.express.consts;

import com.cloud.common.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *  1	揽收	快件揽件
 * 101	已下单	已经下快件单
 * 102	待揽收	待快递公司揽收
 * 103	已揽收	快递公司已经揽收
 * 0	在途	快件在途中
 * 1001	到达派件城市	快件到达收件人城市
 * 1002	干线	快件处于运输过程中
 * 1003	转递	快件发往到新的收件地址
 * 5	派件	快件正在派件
 * 501	投柜或驿站	快件已经投递到快递柜或者快递驿站
 * 3	签收	快件已签收
 * 301	本人签收	收件人正常签收
 * 302	派件异常后签收	快件显示派件异常，但后续正常签收
 * 303	代签	快件已被代签
 * 304	投柜或站签收	快件已从快递柜或者驿站取出签收
 * 6	退回	快件正处于返回发货人的途中
 * 4	退签	此快件单已退签
 * 401	已销单	此快件单已撤销
 * 14	拒签	收件人拒签快件
 * 7	转投	快件转给其他快递公司邮寄
 * 2	疑难	快件存在疑难
 * 201	超时未签收	快件长时间派件后未签收
 * 202	超时未更新	快件长时间没有派件或签收
 * 203	拒收	收件人发起拒收快递,待发货方确认
 * 204	派件异常	快件派件时遇到异常情况
 * 205	柜或驿站超时未取	快件在快递柜或者驿站长时间未取
 * 206	无法联系	无法联系到收件人
 * 207	超区	超出快递公司的服务区范围
 * 208	滞留	快件滞留在网点，没有派送
 * 209	破损	快件破损
 * 8	清关	快件清关
 * 10	待清关	快件等待清关
 * 11	清关中	快件正在清关流程中
 * 12	已清关	快件已完成清关流程
 * 13	清关异常	货物在清关过程中出现异常
 * 	\	\	收件人拒签快件
 * @author nlsm
 */
@Getter
@AllArgsConstructor
public enum ExpressSignCode {

    /**
     * 监控中
     */
    COLLECT("1", "揽收"),
    ORDERED("101", "已经下快递单"),
    TO_BE_COLLECTED("102", "待快递公司揽收"),
    COLLECTED("103", "快递公司已经揽收"),
    ON_THE_WAY("0", "快递在途中"),
    ARRIVE_AT_THE_DISPATCH_CITY("1001", "快递到达收件人城市"),
    IN_TRANSIT("1002", "快递处于运输过程中"),
    TRANSMITTING("1003", "快递发往到新的收件地址"),
    DISPATCH("5", "快递正在派件"),
    A_COUNTER_OR_POST_STATION("501", "快件已经投递到快递柜或者快递驿站"),
    SIGN("3", "快件已签收"),
    I_SIGN("301", "收件人正常签收"),
    EXCEPTION_SIGN("302", "快件显示派件异常，但后续正常签收"),
    SIGN_ON_BEHALF("303", "快件已被代签"),
    STATIONS_SIGN("304", "快件已从快递柜或者驿站取出签收"),
    ON_THE_WAY_BACK("6", "快件正处于返回发货人的途中"),
    RETURNED("4", "此快件单已退签"),
    REVOKED("401", "此快件单已撤销"),
    REFUSE_EXPRESS("14", "收件人拒签快件"),
    TRANSFER("7", "快件转给其他快递公司邮寄"),
    DIFFICULT("2", "快件存在疑难"),
    TIMEOUT_NOT_SIGNED("201", "快件长时间派件后未签收"),
    TIMEOUT_NOT_UPDATED("202", "快件长时间没有派件或签收"),
    REJECTED("203", "收件人发起拒收快递,待发货方确认"),
    ABNORMAL_DISPATCH("204", "快件派件时遇到异常情况"),
    POST_OVERTIME_DID_NOT_TAKE("205", "快件在快递柜或者驿站长时间未取"),
    UNABLE_TO_CONTACT("206", "无法联系到收件人"),
    OUT_OF_SERVICE_AREA("207", "超出快递公司的服务区范围"),
    DETENTION("208", "快件滞留在网点，没有派送"),
    DAMAGE("209", "快件破损"),
    CUSTOMS_CLEARANCE("8", "快件清关"),
    PENDING_CUSTOMS_CLEARANCE("10", "快件等待清关"),
    IN_CUSTOMS_CLEARANCE("11", "快件正在清关流程中"),
    CUSTOMS_CLEARED("12", "快件已完成清关流程"),
    CUSTOMS_CLEARANCE_ANOMALY("13", "货物在清关过程中出现异常");

    private final String code;

    private final String msg;

    public static ExpressSignCode fromCode(String code) {
        if (StringUtils.isNotBlank(code)) {
            for (ExpressSignCode value : ExpressSignCode.values()) {
                if (value.getCode().equals(code)) {
                    return value;
                }
            }
        }
        return COLLECT;
    }
}
