package com.jdrx.aaa.control.center;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.jdrx")
@SpringBootApplication
@MapperScan(basePackages = "com.jdrx")
public class ControlCenterApplication {
	public static void main(String[] args) {
		SpringApplication.run(ControlCenterApplication.class, args);
	}
}