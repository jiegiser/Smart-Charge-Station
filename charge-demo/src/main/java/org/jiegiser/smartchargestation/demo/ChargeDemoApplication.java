package org.jiegiser.smartchargestation.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//========
// 基于Tomcat @ServerEndPoint 注解和基于 Spring 注解的 WebSocket 服务
// 两者只能运行一个
// 例如，要运行基于Spring注解的WebSocket服务
// 把 @EnableWebSocket 注解打开，
// 并且要将 @ServerEndPoint 注解注释掉
//=======

// 启动 Spring 封装注解的W ebSocket
// @EnableWebSocket
public class ChargeDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChargeDemoApplication.class, args);
	}

}
