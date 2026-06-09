package org.jiegiser.smartchargestation.demo.websocket.spring.conf;
/*
 * @Auther: changjie
 * @Date:2026/5/29
 * @Description:
 * @Modified By:
 */

import jakarta.annotation.Resource;
import org.jiegiser.smartchargestation.demo.websocket.spring.handler.WsHandler;
import org.jiegiser.smartchargestation.demo.websocket.spring.interceptor.WsInterceptor;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

public class WebSocketConf implements WebSocketConfigurer {
    // 自定义拦截器
    @Resource
    private WsInterceptor interceptor;

    //自定义处理器
    @Resource
    private WsHandler handler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        /* **********************
         *
         * 处理器：WebSocket握手之后触发
         *
         * 拦截器：WebSocket握手之后，以及握手之前都可以触发
         *

         * *********************/


        registry
                //处理器配置
                .addHandler(handler,"/ws/server")
                //拦截器配置
                .addInterceptors(interceptor)
                //关闭跨域
                .setAllowedOrigins("*");
    }
}
