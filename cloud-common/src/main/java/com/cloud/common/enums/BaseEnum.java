package com.cloud.common.enums;

/**
 * 枚举父接口
 * @author zhushanshuo
 * @date 2022.12.10 13:01
 */
public interface BaseEnum {
	
	Boolean sameCode(Integer code);
	
	String name();
	
	Integer code();

	String msg();
	
	Boolean sameMsg(String msg);
}
