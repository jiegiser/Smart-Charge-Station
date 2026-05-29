package org.jiegiser.smartchargestation.demo.websocket.servEndPoint.conf;
/*
 * @Auther: changjie
 * @Date:2026/5/29
 * @Description: 配置 ServerEndpointExporter
 * @Modified By:
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class ServerEndPointExp {
    /**
     * 需要声明一个 ServerEndPointExporter Bean，
     * ServerEndPointExporter 对象能将 @ServerEndPoint 修饰的类注入到 Spring 容器里,
     * 如果不这样子做，@ServerEndPoint 启动的 WebSocket 服务，客户端无法连接到 WebSocket 服务器
     *
     * 注意
     * 这样子的做法是针对 SpringBoot 框架
     * 若非 SpringBoot 框架，可忽略
     */

    @Bean
    public ServerEndpointExporter serverEndpointExporter()
    {
        return new ServerEndpointExporter();
    }
}
