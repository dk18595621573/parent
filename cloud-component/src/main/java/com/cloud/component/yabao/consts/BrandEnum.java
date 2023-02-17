package com.cloud.component.yabao.consts;

import com.cloud.common.enums.BaseEnum;

/**
 * 品牌
 *
 * @author zhushanshuo
 * @date 2023.02.15 13:01
 */
public enum BrandEnum implements BaseEnum {

	HUAWEI_YB(InterfaceEnum.HUAWEI_YB.getCode(), "华为"),
	HONOR_YB(InterfaceEnum.HONOR_YB.getCode(), "荣耀"),
	APPLE_WARRANTY_YB(InterfaceEnum.APPLE_WARRANTY_YB.getCode(), "苹果"),
	SUMSUNG_YB(InterfaceEnum.SUMSUNG_YB.getCode(), "三星"),
	XIAOMI_YB(InterfaceEnum.XIAOMI_YB.getCode(), "小米"),
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
