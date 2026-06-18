package org.jiegiser.smartchargestation.demo.netty.handlers;
/*
 * @Auther: jiegiser
 * @Date:2026/6/18
 * @Description: 自定义服务端(入站)处理器
 * @Modified By:
 */

import io.netty.buffer.ByteBuf;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import io.netty.channel.*;

/**
 * 自定义入站处理器，是不会直接实现 ChannelInboundHandler
 * 通常有 2 种方法：
 * 1. 继承 SimpleChannelInboundHandler
 * 2. 继承 ChannelInboundHandlerAdapter
 *
 * SimpleChannelInboundHandler 比 ChannelInboundHandlerAdapter：
 * 1. SimpleChannelInboundHandler 提供了泛型, 无需进行类型转换；
 * 2. 消息的释放, SimpleChannelInboundHandler 自动的释放引用计数对象,而 ChannelInboundHandlerAdapter 不会自动释放；
 *
 */
@Slf4j
public class NettyServerHandler extends SimpleChannelInboundHandler<ByteBuf> {
    /**
     * 客户端连接成功触发
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info(">>>>>SimpleChannelInboundHandler有新的客户端连接：" + ctx.channel().id().asLongText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("Handler removed: {}", ctx.channel().id());
    }
    /**
     * 接收到消息触发，用于处理接收到的数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        /**
         * ChannelHandler 由 Context 进行管理
         * Context 提供了 Channel, ChannelPipeline, 用于进行 I/O 操作, 以及获取上下文信息
         */
        String message = msg.toString(CharsetUtil.UTF_8);
        log.info("Received data: {}", message);

        /**
         * fireChannelRead 将消息传递下一个处理器，
         * 如果不调用 fireChannelRead，
         * 则消息不会传递到下一个处理器
         *
         * 对于处理入站事件
         * 如果处理器是最后一个处理器,
         * 就不要调用 fireChannelRead()
         */
        // 将消息传递给下一个处理器
        // ctx.fireChannelRead(message);


        /**
         * ByteBuf 是一个引用计数对象,
         * 这个对象必须手动的释放掉
         *
         * Netty4 开始对于对象的生命周期的管理使用引用计数,
         * 而不是垃圾回收器管理
         * 特别是对于 ByteBuf 对象,
         * ByteBuf 对象使用引用计数, 去提高内存分配和内存释放的性能
         *、
         * 释放 ByteBuf 对象,
         * 1. 可以使用 release();
         * 2. ReferenceCountUtil.release()
         *
         * ReferenceCountUtil.release() 是 release() 的包装
         *
         *
         * 什么时候释放 ByteBuf
         * 1. 原 ByteBuf 没有做任何的处理，只是通过 fireChannelRead() 传递到下一个处理器, 不需要释放
         * 2. 原 ByteBuf 经过处理产生了新的 ByteBuf, 原来的 ByteBuf 要释放
         * 3. 在最后的处理器, ByteBuf 要释放
         */
        msg.release(); // 释放消息，避免内存泄漏
    }
}
