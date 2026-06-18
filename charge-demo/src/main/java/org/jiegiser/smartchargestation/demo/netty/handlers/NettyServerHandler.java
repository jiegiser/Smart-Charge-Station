package org.jiegiser.smartchargestation.demo.netty.handlers;
/*
 * @Auther: jiegiser
 * @Date:2026/6/18
 * @Description:
 * @Modified By:
 */

import io.netty.buffer.ByteBuf;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyServerHandler extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    protected void channelRead0(io.netty.channel.ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        log.info("Received data: {}", msg.toString(io.netty.util.CharsetUtil.UTF_8));
    }
}
