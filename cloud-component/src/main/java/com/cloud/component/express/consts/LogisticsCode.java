package com.cloud.component.express.consts;

/**
 * 物流公司编码
 * @author nlsm
 */
public enum LogisticsCode {
    /** 顺丰 */
    SHUNFENG("shunfeng", "顺丰"),
    /** 申通 */
    SHENTONG("shentong", "申通"),
     /** 圆通 */
    YUANTONG("yuantong", "圆通"),
     /** 邮政 */
    EMS("ems", "邮政"),
     /** 韵达 */
    YUNDA("yunda", "韵达"),
     /** 天天 */
    TIANTIAN("tiantian", "天天"),
     /** 京东 */
    JD("jd", "京东"),
     /** 中通 */
    ZHONGTONG("zhongtong", "中通"),
    ;
    private final String code;
    private final String msg;

    LogisticsCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
