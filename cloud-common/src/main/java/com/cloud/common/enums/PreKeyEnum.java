package com.cloud.common.enums;

/**
 * lock 前缀
 * 枚举
 * 10000:invoice
 * @author zhushanshuo
 * @date 2023.02.01 13:01
 */
public enum PreKeyEnum implements BaseEnum {

	/***** FMS  10000 ~ 10900 （10100， 10200， 10300，...共计 10个）******/
	INVOICE(10000, "invoice"),

	/***** OMS 20000 ~ 20900 （20100， 20200， 20300，...共计 10个）******/
	ORDER(20000, "order"),

	/***** PMS 30000 ~ 30900 （30100， 30200， 30300，...共计 10个）******/
	PRODUCT(30000, "product"),

	/***** USER 40000 ~ 40900 （40100， 40200， 40300，...共计 10个）******/
	USER(40000, "user"),

	/***** SYSTEM 50000 ~ 50900 （50100， 50200， 50300，...共计 10个）******/
	SYSTEM(50000, "system"),

	/***** ERP 60000 ~ 60900 （60100， 60200， 60300，...共计 10个）******/
	ERP(60000, "erp"),
	;

	Integer code;

	String msg;

	PreKeyEnum(Integer code, String msg) {
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
