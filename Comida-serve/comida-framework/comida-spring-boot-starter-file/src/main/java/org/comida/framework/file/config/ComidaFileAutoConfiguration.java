package org.comida.framework.file.config;

import org.comida.framework.file.core.client.FileClientFactory;
import org.comida.framework.file.core.client.FileClientFactoryImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 文件配置类
 *
  */
@AutoConfiguration
public class ComidaFileAutoConfiguration {

    @Bean
    public FileClientFactory fileClientFactory() {
        return new FileClientFactoryImpl();
    }

}
