package com.cloud.component.yabao.consts;

import com.cloud.common.enums.BaseEnum;

/**
 * 品牌
 *
 * @author zhushanshuo
 * @date 2023.02.15 13:01
 */
public enum BrandEnum implements BaseEnum {

	HUAWEI_YB(10, "华为"),
	HONOR_YB(20, "荣耀"),
	APPLE_WARRANTY_YB(30, "苹果"),
	SUMSUNG_YB(40, "三星"),
	XIAOMI_YB(50, "小米"),
	;

	Integer code;

	String msg;

	BrandEnum(Integer code, String msg) {
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
