package com.jdrx.aaa.control.center;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.jdrx")
@MapperScan("com.jdrx.aaa.control.center.*.dao")
@EnableFeignClients(basePackages = "com.jdrx")
@EnableHystrix
@SpringCloudApplication
public class ControlCenterCloudApplication {
	public static void main(String[] args) {
		SpringApplication.run(ControlCenterCloudApplication.class, args);
	}
}