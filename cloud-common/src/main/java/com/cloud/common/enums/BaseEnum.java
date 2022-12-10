package com.cloud.common.enums;

/**
 * 枚举父接口
 * @author zhushanshuo
 * @date 2022.12.10 13:01
 */
public interface BaseEnum {

	/**
	 * 是否匹配code
	 * @param code
	 * @return
	 */
	boolean sameCode(Integer code);

	/**
	 * 返回枚举名称
	 * @return
	 */
	String name();

	/**
	 * 获取枚举code
	 * @return
	 */
	Integer code();

	/**
	 * 获取枚举msg
	 * @return
	 */
	String msg();

	/**
	 * 是否匹配msg
	 * @param msg
	 * @return
	 */
	boolean sameMsg(String msg);
}
