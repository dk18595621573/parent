package com.cloud.common.enums;

/**
 * 采购方（需求方）ID枚举
 * @author zhushanshuo
 * @date 2022.12.10 17:01
 */
public enum DemandIdEnum implements BaseEnum {

	N_L(3, "上海能良电子科技有限公司"),
	;

	Integer code;

	String msg;

	DemandIdEnum(Integer code, String msg) {
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
