package org.comida.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目的启动类
  */
@SuppressWarnings("SpringComponentScan") // 忽略 IDEA 无法识别 ${comida.info.base-package}
@SpringBootApplication(scanBasePackages = {"${comida.info.base-package}.server", "${comida.info.base-package}.module"})
public class ComidaServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(ComidaServerApplication.class, args);

    }

}
