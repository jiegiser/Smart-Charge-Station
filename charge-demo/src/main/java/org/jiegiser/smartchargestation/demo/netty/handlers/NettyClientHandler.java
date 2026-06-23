package org.jiegiser.smartchargestation.demo.netty.handlers;
/*
 * @Auther: jiegiser
 * @Date:2026/6/18
 * @Description:
 * @Modified By:
 */

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    protected void channelRead0(io.netty.channel.ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        log.info("Received data: {}", msg.toString(io.netty.util.CharsetUtil.UTF_8));
    }

    /**
     * 通道准备就绪的时候触发
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String str = "你好服务端, 这条信息发自客户端";

        /**
         * Unpooled 创建非池化 ByteBuf 对象,
         * 适用快速的创建 ByteBuf 对象并快速销毁缓冲区的场景
         *
         * writeAndFlush() 包含了两个方法：
         * 1. write：将 ByteBuf 对象写入到 ChannelOutboundBuffer (缓冲区)
         * 2. flush：将 ChannelOutboundBuffer 的数据写入到 Channel, 然后 Channel 才把数据发送出去
         */
        ctx.writeAndFlush(Unpooled.copiedBuffer(str.getBytes()));

    }
}
