package net.arver.mall.cloud.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MallRegistryApplication {

    public static void main(final String[] args) {
        SpringApplication.run(MallRegistryApplication.class, args);
    }

}
