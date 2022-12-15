package com.cloud.common.enums;

import cn.hutool.core.util.ObjectUtil;
import com.cloud.common.core.model.SelectVo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 枚举工具类
 *
 * @author zhushanshuo
 * @date 2022.12.10 13:01
 */
public class EnumUtil {

    /**
     * 枚举遍历 MAP 缓存
     */
    private static final ConcurrentHashMap<Class<? extends BaseEnum>, BaseEnum[]> MAP = new ConcurrentHashMap<>();

    /**
     * 返回枚举名称
     *
     * @param clazz 枚举class对象
     * @param msg   枚举msg
     * @return 枚举名称
     */
    public static String returnName(Class<? extends BaseEnum> clazz, String msg) {
        BaseEnum[] baseEnumArray = list(clazz);
        for (BaseEnum baseEnum : baseEnumArray) {
            if (baseEnum.sameMsg(msg)) {
                return baseEnum.name();
            }
        }
        return null;
    }

    /**
     * 枚举封装下拉框
     *
     * @param clazz 枚举class对象
     * @return 枚举下拉框列表
     */
    public static List<SelectVo> listSelect(Class<? extends BaseEnum> clazz) {
        List<SelectVo> selectVoList = new ArrayList<>();
        BaseEnum[] baseEnumArray = list(clazz);
        Arrays.stream(baseEnumArray).forEach(item -> selectVoList.add(new SelectVo(item.getCode(), item.getMsg())));
        return selectVoList;
    }

    /**
     * 枚举是否包含指定code， 包含的话，返回对应的msg
     *
     * @param clazz 枚举class对象
     * @param code  指定code
     * @return 枚举code对应的msg
     */
    public static String containsCode(Class<? extends BaseEnum> clazz, Integer code) {
        BaseEnum[] baseEnumArray = list(clazz);
        for (BaseEnum baseEnum : baseEnumArray) {
            if (baseEnum.sameCode(code)) {
                return baseEnum.getMsg();
            }
        }
        return null;
    }

    /**
     * 枚举是否包含msg， 如果包含的话， 返回对应的code
     *
     * @param clazz 枚举class对象
     * @param msg   枚举msg
     * @return 枚举msg对应的code
     */
    public static Integer containsMsg(Class<? extends BaseEnum> clazz, String msg) {
        BaseEnum[] baseEnumArray = list(clazz);
        for (BaseEnum baseEnum : baseEnumArray) {
            if (baseEnum.getMsg().equals(msg)) {
                return baseEnum.getCode();
            }
        }
        return null;
    }

    /**
     * 遍历枚举
     *
     * @param clazz 枚举class对象
     * @return 枚举数组
     */
    private static BaseEnum[] list(Class<? extends BaseEnum> clazz) {
        BaseEnum[] baseEnumArray = MAP.get(clazz);
        if (ObjectUtil.isNotEmpty(baseEnumArray)) {
            baseEnumArray = MAP.get(clazz);
        } else {
            baseEnumArray = clazz.getEnumConstants();
            MAP.put(clazz, baseEnumArray);
        }
        return baseEnumArray;
    }

}
