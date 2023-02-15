package com.cloud.component.yabao.consts;

import com.cloud.common.enums.BaseEnum;

/**
 * 接口
 *
 * @author zhushanshuo
 * @date 2023.02.15 13:01
 */
public enum InterfaceEnum implements BaseEnum {

	HUAWEI_YB(10, "huawei_yb"),
	HONOR_YB(20, "honor_yb"),
	APPLE_WARRANTY_YB(30, "apple_warranty_yb"),
	;

	Integer code;

	String msg;

	InterfaceEnum(Integer code, String msg) {
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
