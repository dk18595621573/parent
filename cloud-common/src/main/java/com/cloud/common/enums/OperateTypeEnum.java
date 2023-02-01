package com.cloud.common.enums;

/**
 * lock 操作互斥类型组
 * 枚举
 * 10000:invoice
 * @author zhushanshuo
 * @date 2023.02.01 13:01
 */
public enum OperateTypeEnum implements BaseEnum {

	/***** FMS INVOICE  10000 ~ 10099 （10001， 10002， 10003，...共计 100个）******/
	INVOICE_UPDATE(10000, "invoice_update"),

	/***** FMS xxxxx  10100 ~ 10199 （10101， 10102， 10103，...共计 100个）******/

	/***** OMS ORDER  20000 ~ 20099 （20001， 20002， 20003，...共计 100个）******/
	ORDER(20000, "order"),

	/***** OMS xxxxx  20100 ~ 20199 （20101， 20102， 20103，...共计 100个）******/

	;

	Integer code;

	String msg;

	OperateTypeEnum(Integer code, String msg) {
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
