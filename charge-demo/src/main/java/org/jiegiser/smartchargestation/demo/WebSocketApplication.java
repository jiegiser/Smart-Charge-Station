package org.jiegiser.smartchargestation.demo;
/*
 * @Auther: changjie
 * @Date:2026/5/29
 * @Description: WebSocket 启动类
 * @Modified By:
 */

import org.springframework.boot.SpringApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * 基于 Tomcat @ServerEndPoint 注解和基于 Spring 注解的 WebSocket 服务;
 * 两者只能运行一个：例如，要运行基于 Spring 注解的 WebSocket 服务，把 @EnableWebSocket 注解打开，并且要将 @ServerEndPoint 注解注释掉
 * @ServerEndPoint 在 org.jiegiser.smartchargestation.demo.websocket.servEndPoint.service.WsService
 */

// 启动 Spring 封装注解的 WebSocket
@EnableWebSocket
public class WebSocketApplication {
    public static void main(String[] args) {

        SpringApplication.run(WebSocketApplication.class, args);
    }
}
