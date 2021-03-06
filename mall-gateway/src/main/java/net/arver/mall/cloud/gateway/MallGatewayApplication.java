package net.arver.mall.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringCloudApplication
public class MallGatewayApplication {

    public static void main(final String[] args) {
        SpringApplication.run(MallGatewayApplication.class, args);
    }

}
