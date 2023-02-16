package com.cloud.component.yabao.consts;

import com.cloud.common.enums.BaseEnum;

/**
 * 品牌
 *
 * @author zhushanshuo
 * @date 2023.02.15 13:01
 */
public enum YbErrorEnum implements BaseEnum {

	KEY_ERROR(5730601, "key required，没有填写key或key不存在或异常"),
	BALANCE_NO(5730603, "余额不足"),
	IP_FORBIDDEN(5730604, "IP白名单限制"),
	WAIT_DEV(5730605, "系统/接口维护，请稍后再试"),
	API_NAME_NOT_EXIST(5730606, "invalid apiname，接口不存在"),
	IMEI_ERROR(5730611, "序列号错误，正则不通过"),
	XIAOMI_YB(5730612, "序列号无效，输入错误或查不到"),
	;

	Integer code;

	String msg;

	YbErrorEnum(Integer code, String msg) {
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
