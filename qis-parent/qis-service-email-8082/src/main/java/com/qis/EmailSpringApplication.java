package com.qis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author qishuo
 * @date 2021/5/30 6:41 下午
 */

@SpringBootApplication
@EnableDiscoveryClient
public class EmailSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmailSpringApplication.class, args);
    }
}