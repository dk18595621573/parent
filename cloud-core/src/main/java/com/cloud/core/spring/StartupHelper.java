package com.cloud.core.spring;

import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 打印启动信息.
 *
 * @author Luo
 * @date 2021-9-7 09:58
 */
public abstract class StartupHelper {

    /**
     * SpringBoot启动打印.
     *
     * @param logger 日志对象
     * @param ctx    spring上下文
     */
    public static void printStartInfo(final Logger logger, final ConfigurableApplicationContext ctx) {
        try {
            Environment env = ctx.getEnvironment();
            String address = InetAddress.getLocalHost().getHostAddress();
            String port = env.getProperty("server.port");
            logger.info("\n----------------------------------------------------------\n\t"
                    + "Application '{}' is running! Access URLs:\n\t"
                    + "Local: \t\thttp://localhost:{}\n\t"
                    + "External: \thttp://{}:{}\n\t"
                    + "Doc: \thttp://{}:{}/doc.html\n"
                    + "----------------------------------------------------------",
                env.getProperty("spring.application.name"), port, address, port, address, port);
        } catch (UnknownHostException e) {
            logger.error(e.getMessage(), e);
        }
    }

}
