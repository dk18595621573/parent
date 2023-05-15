package com.cloud.component.config;

import com.cloud.component.properties.QichachaProperties;
import com.cloud.component.qichacha.QichachaClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 企查查配置
 *
 * @author m
 */
@Configuration
@EnableConfigurationProperties(value = QichachaProperties.class)
@ConditionalOnProperty(prefix = QichachaProperties.QICHACHA_PREFIX, name = "enabled", havingValue = "true")
public class QichachaConfig {

    @Bean
    public QichachaClient qichachaClient(QichachaProperties qichachaProperties) {
        return new QichachaClient(qichachaProperties);
    }


}
