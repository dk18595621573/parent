package com.cloud.common.enums;

/**
 * client
 * @author zhushanshuo
 * @date 2022.12.16 15:01
 */
public enum AfterSaleSolveTypeEnum implements BaseEnum {

	DEFAULT(0, "默认"),
	SOLVED(1, "已解决"),
	;

	Integer code;

	String msg;

	AfterSaleSolveTypeEnum(Integer code, String msg) {
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
