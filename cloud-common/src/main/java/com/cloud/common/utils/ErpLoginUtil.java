package com.cloud.common.utils;


import org.springframework.beans.factory.annotation.Value;

import java.util.Objects;

/**
 *
 * erp注册条件工具
 *
 * @author mft
 */
public class ErpLoginUtil {

    @Value("${spring.profiles.active}")
    private static String active;

    /**
     * 判断配置文件是否为生产环境
     */
    public static boolean isProd() {
        return Objects.equals(active, "prod");
    }


}
