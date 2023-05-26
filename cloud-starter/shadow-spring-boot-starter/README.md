# 影刀RPA

## 快速开始

1. 引入依赖
    ```xml
    <dependency>
        <groupId>com.cloud</groupId>
        <artifactId>shadow-spring-boot-starter</artifactId>
        <version>${version}</version>
    </dependency>
    ```
2. 添加配置(application.yml)
    ```yaml
    #注入生成流水号
    shadow:
       enabled: true
       url: https://api.winrobot360.com
       accessKeyId: @秘钥Key
       accessKeySecret: @秘钥Secret
    ```






