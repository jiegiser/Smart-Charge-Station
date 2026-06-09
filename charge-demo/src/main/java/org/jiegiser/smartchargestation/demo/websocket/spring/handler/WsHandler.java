package org.jiegiser.smartchargestation.demo.websocket.spring.handler;
/*
 * @Auther: changjie
 * @Date:2026/5/29
 * @Description: 自定义处理器
 * @Modified By:
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
@Slf4j
public class WsHandler implements WebSocketHandler {

    /**
     * 握手之后触发
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info(">>>>> 基于 Spring 注解 WebSocket 连接建立成功，会话 ID: {}, 客户端 IP: {}",
                session.getId(), session.getRemoteAddress());
    }

    /**
     * 消息业务逻辑处理
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        log.info("接收到 WebSocket 消息，会话 ID: {}, 消息大小: {}字节",
                session.getId(), message.getPayloadLength());

        // 取出消息内容
        String payload = message.getPayload().toString();
        Object token = session.getAttributes().get("Token");

        log.info("处理 WebSocket 消息内容: {}, Token: {}", payload, token);


    }

    /**
     * 发送错误触发
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("WebSocket 传输错误，会话 ID: {}, 错误信息: {}",
                session.getId(), exception.getMessage(), exception);
    }

    /**
     * 连接断开触发
     * @param session
     * @param closeStatus
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        log.info("WebSocket 连接关闭，会话ID: {}, 关闭状态: {}, 状态码: {}",
                session.getId(), closeStatus.getReason(), closeStatus.getCode());

    }

    /**
     * 是否支持内容切片处理
     * @return
     */
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}