package net.arver.mall.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SuppressWarnings({"checkstyle:FinalClass", "checkstyle:HideUtilityClassConstructor"})
@EnableZuulProxy
@SpringCloudApplication
public class MallGatewayApplication {

    /**
     * 程序入口.
     * @param args 参数
     */
    public static void main(final String[] args) {
        SpringApplication.run(MallGatewayApplication.class, args);
    }

}
