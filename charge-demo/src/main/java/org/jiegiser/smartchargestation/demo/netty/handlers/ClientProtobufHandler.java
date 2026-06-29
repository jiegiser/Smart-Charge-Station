package org.jiegiser.smartchargestation.demo.netty.handlers;
/*
 * @Auther: changjie
 * @Date:2026/6/29
 * @Description: Protobuf (客户端) 处理器
 * @Modified By:
 */

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.jiegiser.smartchargestation.demo.protobuf.UserProtobuf;

@Slf4j
public class ClientProtobufHandler extends SimpleChannelInboundHandler<UserProtobuf.User> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, UserProtobuf.User byteBuf) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        // 计数器
        int count = 0;
        for (int i = 0; i < 100; i++) {

            // 数据发送
            UserProtobuf.User user = UserProtobuf.User.newBuilder().setName("this is from Client Protobuf").build();

            ctx.writeAndFlush(user);
            ++count;
            log.info("粘包半包 >>>>> 第" + count + "次的发送数据包:" + user.getName());

        }

    }
}
