package com.cloud.common.enums;

/**
 * 统一是否枚举
 * 1:是  2:否
 * @author zhushanshuo
 * @date 2022.12.10 13:01
 */
public enum IsEnum implements BaseEnum {
	
	是(1, "是"),
	否(2, "否"),
	;
	
	Integer code;
	
	String msg;

	IsEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Boolean sameCode(Integer code) {
		return this.code.equals(code);
	}

	public Integer code() {
		return code;
	}

	public String msg() {
		return msg;
	}
	
	@Override
	public Boolean sameMsg(String msg) {
		return this.msg.equals(msg);
	}
	

}
