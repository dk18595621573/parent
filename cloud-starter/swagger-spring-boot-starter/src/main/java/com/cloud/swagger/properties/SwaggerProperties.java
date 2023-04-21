//package com.cloud.swagger.properties;
//
//import com.cloud.common.constant.Constants;
//import lombok.Data;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//
//import java.util.List;
//
///**
// * 配置.
// *
// * @author zenghao
// * @date 2023/3/1
// */
//@Data
//@ConfigurationProperties(prefix = SwaggerProperties.SWAGGER_PREFIX)
//public class SwaggerProperties {
//
//    public static final String SWAGGER_PREFIX = Constants.CONFIG_PREFIX + "swagger";
//
//    private boolean enabled;
//    /**
//     * 标题.
//     */
//    private String title;
//
//    /**
//     * 描述.
//     */
//    private String description;
//
//    /**
//     * 版本号.
//     */
//    private String version;
//
//    /**
//     * 许可证.
//     */
//    private String license;
//
//    /**
//     * 许可证URL.
//     */
//    private String licenseUrl;
//
//    /**
//     * 联系人.
//     */
//    private String contactName;
//
//    /**
//     * 联系URL.
//     */
//    private String contactUrl;
//
//    /**
//     * 联系邮箱.
//     */
//    private String contactEmail;
//
//    /**
//     * 全局api key定义.
//     */
//    private List<GlobalKey> apiKeys;
//
//    /**
//     * 文档认证信息.
//     */
//    private Auth auth = new Auth();
//
//    /**
//     * 文档认证信息.
//     */
//    @Data
//    public static class Auth {
//
//        /**
//         * 是否开启.
//         */
//        private boolean enabled;
//
//        /**
//         * 账号.
//         */
//        private String username = "admin";
//
//        /**
//         * 密码.
//         */
//        private String password = "admin";
//    }
//
//    /**
//     * 全局key.
//     */
//    @Data
//    public static class GlobalKey {
//
//        private String name;
//
//        private String keyname;
//
//        private String passAs;
//    }
//}
