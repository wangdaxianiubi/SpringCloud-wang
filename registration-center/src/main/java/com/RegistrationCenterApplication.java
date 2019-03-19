package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class RegistrationCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistrationCenterApplication.class, args);
		System.out.println("【【【恭喜，注册中心启动成功！！！】】】");
	}

}
