package com.cloud.generator.config;

import lombok.Data;

/**
 * 读取代码生成相关配置
 *
 * @author author
 */
@Data
public class GenConfig {
    /**
     * 作者
     */
    private String author;

    /**
     * 生成包路径
     */
    private String packageName;

    /**
     * 自动去除表前缀，默认是false
     */
    private boolean autoRemovePre;

    /**
     * 表前缀(类名不会包含表前缀)
     */
    private String tablePrefix;

    /**
     * 代码保存地址
     */
    private String path;

    public static GenConfig defaultConfig() {
        GenConfig config = new GenConfig();
        config.setAuthor("generator");
        config.setPackageName("con.cloud");
        config.setPath(System.getProperty("user.home"));
        return config;
    }

}
