package com.cloud.common.enums;

/**
 * 退货处理类型
 *
 * @author zhushanshuo
 * @date 2022.12.12 19:01
 */
public enum RefundProcessTypeEnum implements BaseEnum {

	DEFAULT(0, "默认"),
	HANDLE_IT_YOURSELF(1, "采购自行处理"),
	RETURN_AFTER_RECEIPT(2, "采购收货后退回"),
	;

	Integer code;

	String msg;

	RefundProcessTypeEnum(Integer code, String msg) {
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
