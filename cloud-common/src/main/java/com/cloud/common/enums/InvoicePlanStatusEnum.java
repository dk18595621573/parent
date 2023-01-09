package com.cloud.common.enums;

/**
 * 发票汇总表 状态
 * @author zhushanshuo
 * @date 2023.01.09 13:01
 */
public enum InvoicePlanStatusEnum implements BaseEnum {

	UN_REACH(10, "未到票"),
	SOME_REACH(20, "部分到票"),
	WAIT_CHECK(30, "待核验"),
	CHECK_SUCCESS(40, "核验成功"),
	CHECK_FAIL(50, "核验失败"),
	;

	Integer code;

	String msg;

	InvoicePlanStatusEnum(Integer code, String msg) {
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
