package io.github.haifeng303;

import com.fengzai.feign.annotation.EnableFeignSecurity;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(basePackages = {"io.github.haifeng303.dao"})
@EnableDiscoveryClient
@EnableFeignSecurity
@EnableFeignClients(basePackages = {"com.fengzai.upms.feign"})
@EnableScheduling
public class FengCloudAdminWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(FengCloudAdminWebApplication.class, args);
    }

}
