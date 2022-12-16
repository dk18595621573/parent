package com.cloud.common.enums;

/**
 * client
 * @author zhushanshuo
 * @date 2022.12.16 15:01
 */
public enum ClientEnum implements BaseEnum {

	SUPPLY(1, "供应商"),
	PURCHASE(2, "采购方"),
	;

	Integer code;

	String msg;

	ClientEnum(Integer code, String msg) {
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
