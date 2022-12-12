package com.cloud.common.enums;

/**
 * 串码异常备注
 * @author zhushanshuo
 * @date 2022.12.10 17:01
 */
public enum ImeiVerifyExceptionEnum implements BaseEnum {

	PDD_SEARCH_EXCEPTION(1, "拼多多查询异常"),
	;

	Integer code;

	String msg;

	ImeiVerifyExceptionEnum(Integer code, String msg) {
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
