package com.qis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author qishuo
 * @date 2021/5/30 6:26 下午
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.qis.mapper")
@EnableFeignClients
public class CodeSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(CodeSpringApplication.class, args);
    }
}
