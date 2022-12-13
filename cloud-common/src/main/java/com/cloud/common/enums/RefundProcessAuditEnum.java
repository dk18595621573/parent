package com.cloud.common.enums;

/**
 * 退货处理
 *
 * @author zhushanshuo
 * @date 2022.12.12 19:01
 */
public enum RefundProcessAuditEnum implements BaseEnum {

	SUPPLIER_EXAMINE_GOODS(0, "供应商验货中"),
	GOODS_INTACT(1, "货物完好"),
	GOODS_ANOMALY(2, "货物异常"),
	DEMAND_EXAMINE_GOODS(3, "采购方验货"),
	CRACKING(4, "货物完好-已完结"),
	COME_TO_AN_END(5, "货物异常-已完结"),
	;

	Integer code;

	String msg;

	RefundProcessAuditEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	@Override
	public Integer getCode() {
		return code;
	}

	@Override
	public String getMsg() {
		return msg;
	}

}
