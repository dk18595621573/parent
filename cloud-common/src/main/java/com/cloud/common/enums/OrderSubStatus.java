package com.cloud.common.enums;

/**
 * 订单子状态（20:新建；21:撤销；22:超时发货；23:超时流拍，30:待抢单 ，31:待确认）
 */
public enum OrderSubStatus implements BaseEnum {

    /** 没有子状态的时候需要将子状态调整为默认状态 */
    DEFAULT(0, "默认"),

    /** 新建 */
    NEW(20, "新建"),
    /** 撤销 */
    REVOKE(21, "撤销"),
    /** 超时发货 */
    OVERTIME_DELIVERY(22, "超时发货"),
    /** 超时发货未成交 */
    OVERTIME_DELIVERY_TRADE(25, "超时发货未成交"),
    /** 超时流拍 */
    OVERTIME_STREAMING(23, "超时流拍"),
    /** 售后-取消订单 */
    AFTER_CANCEL(24, "售后-取消订单"),
    /** 串码异常撤销 */
    REVOKE_IMPL(28, "串码异常撤销"),
    /** 物流异常撤销 */
    REVOKE_LOGISTICS(26, "物流异常撤销"),
    /** 毁单 */
    REVOKE_DESTROY(27, "毁单"),
    /** 供应商疫情原因毁单 */
    REVOKE_EPIDEMIC(29, "供应商疫情原因撤销"),
    /** 超时未上传串码撤销 */
    OVERTIME_UPLOAD_IMPL(210, "超时未上传串码撤销"),

    /** 待抢单 */
    PENDING_ORDER(30, "待抢单（默认均在交易市场不显示）"),
    /** 待确认 */
    TO_BE_CONFIRMED(31, "抢单待确认"),
    /** 小程序显示 */
    ONLY_APPLET_DISPLAY(32, "小程序显示"),
    /** 小程序web显示 */
    BOTH_APPLET_AND_WEB_DISPLAY(33, "小程序web显示"),

    /** 目前需要提交串码 */
    TO_BE_COMPLETE(40, "待完善发货信息"),
    /** 已提交串码 */
    COMPLETE_CODE(41, "已完善发货信息"),
    /** 已填写快递信息，还未填写串码 */
    PART_SHIPMENTS(42, "已操作，未填写串码"),
    /** 部分收货 */
    PART_RECEIVING(43, "未发货,部分收货"),
    /** 售后-重新发货 */
    AFTER_SHIPMENTS(44, "售后-重新发货"),
    /** 一件代发收货,待发货 */
    COLLECT_UNDERTAKES_TO(45, "一件代发收货,待发货"),
    /** 预发货状态 */
    TENTATIVE_SHIPPED(46, "预发货"),
    /** 冻结（操作、发货、预发货）无法点击 */
    FREEZE(47, "冻结"),
    /** 已销售 */
    SOLD(48, "已销售"),

    /** 已发货待提交串码 */
    SEND_TO_BE_COMPLETE(50, "已发货待提交串码"),
    /** 已发货已提交串码 */
    SEND_COMPLETE_CODE(51, "已发货已完善发货信息"),
    /** 一件代发无仓发货 */
    SEND_UNDERTAKES_TO(53, "一件代发已发货"),
    /** 一件代发快递发货 */
    SEND_EXPRESS_TO(54, "一件代发已发货"),
    /** 已发货,部分收货 */
    SEND_COLLECT_PORTION(55,"已发货,部分收货"),
    /** 已发货,已拣货 */
    SEND_UNDERTAKES_NO(56,"已发货,已拣货,待发货"),
    SEND_NORMAL_SHIPMENT(57,"物流审核成功，正常发货"),

    /** 采购入库收货 */
    SEND_PURCHASING(60, "采购入库收货，签收状态"),
    /** 物流签收 */
    EXPRESS_SIGNED(61, "物流签收"),
    /** 确认收货 */
    CONFIRM_RECEIPT(62, "确认收货"),
    /** 已支付 */
    PAY_MONEY(63, "已支付"),
    /** 已确认 */
    AFFIRM_PAY(64, "已确认"),

    /** 退货退单 */
    DEMAND_AFFIRM(70, "需求方发起退单"),
    SUPPLIER_NOT_AFFIRM(71, "供应商未确认"),
    /** 供应商已确认:普通快递到这结束、无仓收货需要再次流转 */
    SUPPLIER_THEN_AFFIRM(72, "供应商已确认:普通快递到这结束、无仓收货需要再次流转"),
    /** 无仓退换供应商 */
    WAREHOUSE_NOT_AFFIRM(73, "无仓退换供应商"),
    /** 供应商已收 */
    WAREHOUSE_THEN_AFFIRM(74, "供应商已收"),
    /** 待发布、报价中追单 */
    USER_CHARGEBACK(75, "用户退单"),
    /** 华盛订单退款 */
    REIMBURSE_HS(76,"华盛退款订单"),
    /** 华盛订单退货拦截 */
    RETURN_INTERCEPTION_HS(77,"华盛退货拦截"),

    /** 华盛订单退款 */
    REIMBURSE_GY(78,"广移退款订单"),
    /** 华盛订单退货拦截 */
    RETURN_INTERCEPTION_GY(79,"广移退货拦截"),

    /** 补全地址 */
    ADDRESS_COMPLETED_ERROR(81, "补全地址（未补全）"),

    /** 异常订单 */
    PDD_ALREADY_SOLD(91, "拼多多已销售"),
    ACTIVATE(92, "已激活"),
    IMEI_ERROR(93, "串码错误"),
    IMEI_INPUT_ERROR(94,"序列号错误"),
    IMEI_NOT_EXIST(95,"序列号不存在"),
    ERROR_MODEL_ERROR(96, "型号颜色错误"),
    IMEI_ABNORMAL(97, "串码异常"),

    /** 物流异常 */
    LOGISTICS_DESTINATION_ABNORMAL(121, "物流目的地异常"),
    LOGISTICS_SHIPPING_ABNORMAL(122, "物流发货城市异常"),
    LOGISTICS_NO_FLOW(123, "物流无流转信息"),
    LOGISTICS_NO_SIGN(124, "签收异常"),
    LOGISTICS_KNOTTY(125, "疑难"),
    NO_SIGN_DAYS(126, "7天未签收异常"),
    LOGISTICS_PHONE_SUFFIX(127, "手机号后4位错误"),

    /** 售后 */
    AFTERMARKET_WAIT_INSPECTION_GOODS(100, "售后-待验货(待寄回)"),
    AFTERMARKET_INSPECTION_GOODS(101, "售后-已验货(已寄回)"),

    /** 撤销 */
    REVOKE_NEW(131, "新建采购撤销"),
    REVOKE_PUBLISHED(132, "待发布撤销"),
    REVOKE_ADDRESS(133, "待补全地址撤销"),
    REVOKE_DELIVERED(134, "待发货撤销"),
    REVOKE_INQUIRY(135, "群接龙撤销出价"),
    ACTUAL_OVERTIME_STREAMING(136, "实单采购超时流拍"),
    REVOKE_IN_QUOTATION(137,"报价中撤销"),

    /** 未追回订单 */
    NOT_RECOVERED_WAIT_INSPECTION_GOODS(140, "未追回订单-待验货(待寄回)"),
    NOT_RECOVERED_INSPECTION_GOODS(141, "未追回订单-已验货(已寄回)"),

    /** 竞拍出价 */
    AUCTIONING(150, "竞价中"),
    AUCTION_STREAMING(151, "竞价流拍")
    ;

    private final Integer code;

    private final String msg;

    private OrderSubStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
