package org.jiegiser.smartchargestation.demo.netty.handlers;
/*
 * @Auther: jiegiser
 * @Date:2026/6/25
 * @Description: 粘包半包场景复现(服务端接收)
 * @Modified By:
 */

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerPkgHandler extends SimpleChannelInboundHandler<String> {

    // 计数器
    private int count = 0;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {

        ++count;
        log.info("粘包半包 >>>>> 已接收第" + count + "个数据包:" + msg);

    }
}
