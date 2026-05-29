package org.jiegiser.smartchargestation.demo.websocket.servEndPoint.service;
/*
 * @Auther: changjie
 * @Date:2026/5/29
 * @Description: WebSocket (基于 Tomcat @ServerEndPoint) 服务类
 * @Modified By:
 */

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * 基于 Tomcat @ServerEndPoint 注解和基于 Spring 注解的 WebSocket 服务
 * 两者只能运行一个
 * 例如，要运行基于 Tomcat @ServerEndPoint 注解的 WebSocket 服务，把 @ServerEndpoint 注解打开，并且要将 @EnableWebSocket 注解注释掉；
 * @EnableWebSocket 在 org.jiegiser.smartchargestation.demo.WebSocketApplication
 */
@Component
@Slf4j
@ServerEndpoint("/ws/server")
public class WsService {
    /**
     *
     * @ServerEndpoint修饰的类
     * 包含 @Open @Close @OnMessage @OnError 方法
     */

    @OnOpen
    public void onOpen() {
        log.info(">>>> ServerEndPoint 连接建立成功！<<<<");
    }

    @OnClose
    public void onClose()
    {

    }

    @OnMessage
    public void onMessage(String message)
    {
    }

    @OnError
    public void onError(Throwable error)
    {

    }
}
