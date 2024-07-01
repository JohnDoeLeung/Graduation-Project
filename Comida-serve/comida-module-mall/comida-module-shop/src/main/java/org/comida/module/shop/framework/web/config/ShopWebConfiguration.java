package org.comida.module.shop.framework.web.config;

import org.comida.framework.swagger.config.ComidaSwaggerAutoConfiguration;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * shop 模块的 web 组件的 Configuration
 *
  */
@Configuration(proxyBeanMethods = false)
public class ShopWebConfiguration {

    /**
     * promotion 模块的 API 分组
     */
    @Bean
    public GroupedOpenApi shopGroupedOpenApi() {
        return ComidaSwaggerAutoConfiguration.buildGroupedOpenApi("shop");
    }

}
