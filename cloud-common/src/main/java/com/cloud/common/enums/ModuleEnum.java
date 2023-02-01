package com.cloud.common.enums;

/**
 * 模块服务枚举
 * 10000:fms
 * @author zhushanshuo
 * @date 2023.02.01 13:01
 */
public enum ModuleEnum implements BaseEnum {

	FMS(10000, "fms"),
	OMS(20000, "oms"),
	PMS(30000, "pms"),
	USER(40000, "user"),
	SYSTEM(50000, "system"),
	ERP(60000, "erp"),
	;

	Integer code;

	String msg;

	ModuleEnum(Integer code, String msg) {
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
