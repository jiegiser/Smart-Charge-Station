package org.jiegiser.smartchargestation.demo.netty.handlers;
/*
 * @Auther: changjie
 * @Date:2026/6/29
 * @Description: Protobuf (服务端) 处理器
 * @Modified By:
 */

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.jiegiser.smartchargestation.demo.protobuf.UserProtobuf;

@Slf4j
public class ServerProtobufHandler extends SimpleChannelInboundHandler<UserProtobuf.User> {

    // 计数器
    private int count = 0;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, UserProtobuf.User user) throws Exception {

        ++count;
        log.info("粘包半包 >>>>> 已接收第" + count + "个数据包:" + user.getName());

    }
}
