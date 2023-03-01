package com.cloud.swagger;

import cn.hutool.core.collection.CollUtil;
import com.cloud.swagger.properties.SwaggerProperties;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger自动装配.
 *
 * @author zenghao
 * @date 2023/3/1
 */
@Configuration
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerAutoConfiguration {

    @Autowired
    private SwaggerProperties swaggerProperties;

    /**
     * 创建API
     */
    @Bean
    @ConditionalOnMissingBean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
            // 是否启用Swagger
            .enable(swaggerProperties.isEnabled())
            // 用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
            .apiInfo(apiInfo())
            // 设置哪些接口暴露给Swagger展示
            .select()
            // 扫描所有有注解的api，用这种方式更灵活
            .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
            // 扫描指定包中的swagger注解
            // .apis(RequestHandlerSelectors.basePackage("com.cloud.project.tool.swagger"))
            // 扫描所有 .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            /* 设置安全模式，swagger可以设置访问token */
            .securitySchemes(securitySchemes())
            .securityContexts(securityContexts());
    }

    /**
     * 安全模式，这里指定token通过Authorization头请求头传递
     */
    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> apiKeyList = new ArrayList<SecurityScheme>();
        apiKeyList.add(new ApiKey("Authorization", "Authorization", In.HEADER.toValue()));
        if (CollUtil.isNotEmpty(swaggerProperties.getApiKeys())) {
            swaggerProperties.getApiKeys().forEach(o -> {
                apiKeyList.add(new ApiKey(o.getName(), o.getKeyname(), o.getPassAs()));
            });
        }
        return apiKeyList;
    }

    /**
     * 安全上下文
     */
    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(
            SecurityContext.builder()
                .securityReferences(defaultAuth())
                .operationSelector(o -> o.requestMappingPattern().matches("/.*"))
                .build());
        return securityContexts;
    }

    /**
     * 默认的安全上引用
     */
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{new AuthorizationScope("global", "全局设置")};
        List<SecurityReference> securityReferences = new ArrayList<>();

        this.securitySchemes().forEach(s -> {
            securityReferences.add(new SecurityReference(s.getName(), authorizationScopes));
        });
        return securityReferences;
    }

    /**
     * 添加摘要信息
     */
    private ApiInfo apiInfo() {
        // 用ApiInfoBuilder进行定制
        return new ApiInfoBuilder()
            // 设置标题
            .title(swaggerProperties.getTitle())
            // 描述
            .description(swaggerProperties.getDescription())
            // 作者信息
            .contact(new Contact(swaggerProperties.getContactName(), swaggerProperties.getContactUrl(), swaggerProperties.getContactEmail()))
            //许可信息
            .license(swaggerProperties.getLicense())
            .licenseUrl(swaggerProperties.getLicenseUrl())
            // 版本
            .version(swaggerProperties.getVersion())
            .build();
    }
}
