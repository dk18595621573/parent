package com.cloud.common.enums;

/**
 * 锁状态
 *
 * @author zhushanshuo
 * @date 2023.02.01 13:01
 */
public enum LockStatusEnum implements BaseEnum {

	INIT(10, "初始化"),
	LOCKING(20, "加锁中"),
	UNLOCKED(30, "锁释放"),
	;

	Integer code;

	String msg;

	LockStatusEnum(Integer code, String msg) {
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
