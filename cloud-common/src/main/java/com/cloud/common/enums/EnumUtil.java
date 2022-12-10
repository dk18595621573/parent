package com.cloud.common.enums;

import com.cloud.common.core.model.SelectVo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 枚举工具类
 * @author zhushanshuo
 * @date 2022.12.10 13:01
 */
public class EnumUtil {
	/**
	 * 遍历枚举
	 * @param clazz
	 * @return
	 */
	private static BaseEnum[] list(Class<? extends BaseEnum> clazz) {
		BaseEnum[] baseEnumArray = clazz.getEnumConstants();
		return baseEnumArray;
	}

	/**
	 * 枚举封装下拉框
	 * @param clazz
	 * @return
	 */
	public static List<SelectVo> listSelect(Class<? extends BaseEnum> clazz) {
		List<SelectVo> selectVoList = new ArrayList<>();
		BaseEnum[] baseEnumArray = list(clazz);
		Arrays.stream(baseEnumArray).forEach(item -> selectVoList.add(new SelectVo(item.code(), item.msg())));
		return selectVoList;
	}

	/**
	 * 枚举是否包含指定code， 包含的话，返回对应的msg
	 * @param clazz  枚举class对象
	 * @param code 指定code
	 * @return
	 */
	public static String containsCode(Class<? extends BaseEnum> clazz, Integer code) {
		BaseEnum[] baseEnumArray = list(clazz);
		for (BaseEnum baseEnum : baseEnumArray) {
			if (baseEnum.sameCode(code)) {
				return baseEnum.msg();
			}
		}
		return null;
	}

	public static Integer containsValue(Class<? extends BaseEnum> clazz, String msg) {
		BaseEnum[] baseEnumArray = list(clazz);
		for (BaseEnum baseEnum : baseEnumArray) {
			if (baseEnum.msg().equals(msg)) {
				return baseEnum.code();
			}
		}
		return null;
	}
	
	

	public static void main(String[] args) {

	}

}
