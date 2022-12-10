package com.cloud.common.enums;

/**
 * 统一是否枚举
 * 1:是  2:否
 * @author zhushanshuo
 * @date 2022.12.10 13:01
 */
public enum IsEnum implements BaseEnum {
	
	YES(1, "是"),
	NO(2, "否"),
	;
	
	Integer code;
	
	String msg;

	IsEnum(Integer code, String msg) {
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
