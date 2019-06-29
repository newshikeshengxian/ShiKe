package com.sk.eurake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 允许当前项目作为Eureka注册中心启动
 */
@EnableEurekaServer
@SpringBootApplication
public class EurakeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurakeApplication.class, args);
	}

}

