package net.arver.mall.cloud.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MallAdminApplication {

    public static void main(final String[] args) {
        SpringApplication.run(MallAdminApplication.class, args);
    }
}
