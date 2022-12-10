package com.cloud.common.enums;

/**
 * 枚举父接口
 * @author zhushanshuo
 * @date 2022.12.10 13:01
 */
public interface BaseEnum {

	/**
	 * 返回枚举名称
	 * @return
	 */
	String name();

	/**
	 * 获取枚举code
	 * @return
	 */
	Integer getCode();

	/**
	 * 获取枚举msg
	 * @return
	 */
	String getMsg();

	/**
	 * 抽象接口方法，不用子类实现
	 * 是否匹配msg
	 * @param msg
	 * @return
	 */
	default boolean sameMsg(String msg) {
		return getMsg().equals(msg);
	}

	/**
	 * 抽象接口方法，不用子类实现
	 * 是否匹配code
	 * @param code
	 * @return
	 */
	default boolean sameCode(Integer code) {
		return getCode().equals(code);
	}
}
