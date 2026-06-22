package org.jiegiser.smartchargestation.demo.netty.handlers;
/*
 * @Auther: jiegiser
 * @Date:2026/6/22
 * @Description:
 * @Modified By:
 */

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

@Slf4j
public class ServerHandlerAdapter extends ChannelInboundHandlerAdapter {
    /**
     * 客户端连接进来触发
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info(">>>>> ChannelInboundHandlerAdapter 有新的客户端连接：" + ctx.channel().id().asLongText());
    }

    /**
     * 接收到消息触发
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // super.channelRead(ctx, msg);

        // 强制转换为 ByteBuf
        ByteBuf byteBuf = (ByteBuf) msg;
        String message = byteBuf.toString(StandardCharsets.UTF_8);

        log.info(">>>>> ChannelInboundHandlerAdapter 收到的消息：" + message);

        // 将消息传递给下一个处理器
        ctx.fireChannelRead(msg);
    }
}
