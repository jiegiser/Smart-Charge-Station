package org.jiegiser.smartchargestation.demo.netty;
/*
 * @Auther: changjie
 * @Date:2026/6/9
 * @Description: Netty 服务端
 * @Modified By:
 */

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Order(1)
@Slf4j
public class NettyServer implements CommandLineRunner {
    private NioEventLoopGroup bossGroup;
    private NioEventLoopGroup workerGroup;
    private Channel channel;

    @Value("${Netty.server.port}")
    private int port;

    /**
     * Netty 服务器启动
     */
    public void start() {
        /**
         * Netty 对于 NIO（主从 Reactor 模型）的深度封装
         * 1. NioEventLoop：事件循环，负责处理连接、读、写等事件；----> 网络指挥官
         * 2. Channel：通道，负责网络通信                      ----> 快递小哥
         * 3. ChannelPipeline: 通道处理器，负责管理 Handler    ----> 工作流水线
         * 4. ChannelHandler：处理器，负责业务逻辑处理          ----> 流水线上员工
         * 5. ByteBuf：字节缓冲区，负责数据的读写                ----> 运输容器\数据容器
         * 6. ServerBootstrap：引导类，负责初始化 Netty 服务端组件
         */

        // 处理网络请求
        bossGroup = new NioEventLoopGroup();
        // 处理网络 IO
        workerGroup = new NioEventLoopGroup();

        /**
         * ServerBootstrap：引导类，负责初始化 Netty 服务端组件，ServerBootstrap对象起到的作用：
         * Netty 整个程序的组件初始化，启动，服务器连接等等的一个引导作用；SeverBootstrap 相当于一条主线，把 Netty 的主要组件串联起来;
         *
         * 1. group：设置 bossGroup 和 workerGroup，用于处理网络请求和网络 IO
         * 2. channel：设置通道类型为 NioServerSocketChannel，用于处理服务器端通道
         * 3. childHandler：设置通道处理器，用于处理业务逻辑
         */
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                // 配置 NioEventLoopGroup
                .group(bossGroup, workerGroup)
                /**
                 * SocketChannel 表示：
                 * 基于 Socket 通讯的通道，用于处理网络请求
                 * 监听 TCP 连接
                 * NioServerSocketChannel 表示：
                 * 基于 NIO 的服务器端通道，用于处理服务器端网络请求
                 */
                // 配置 Channel
                .channel(NioServerSocketChannel.class)
                /**
                 * option：设置通道选项，用于配置服务器端通道的参数
                 * SO_RCVBUF：设置接收缓冲区大小
                 * 设置指定大小的接收缓冲区（TCP）
                 */
                .option(ChannelOption.SO_RCVBUF, 3)
                /**
                 * 将 ChannelHandler 添加上 ChannelPipeline
                 * childHandler：设置通道处理器，用于处理业务逻辑
                 */
                // 将 ChannelHandler 添加上 ChannelPipeline
                .childHandler(new ChannelInitializer<>() {
                    /**
                     * 初始化通道处理器
                     * @param channel
                     * @throws Exception
                     */
                    @Override
                    protected void initChannel(io.netty.channel.Channel channel) throws Exception {
                        /**
                         * Channel 初始化是伴随 ChannelPipeline 的初始化，ChannelPipeline 中包含多个 ChannelHandler
                         */
                        // 获取 ChannelPipeline
                        ChannelPipeline pipeline = channel.pipeline();
                        /**
                         * 添加 ChannelHandler 到 ChannelPipeline
                         * 真正进行业务逻辑处理
                         *
                         * 对于处理入站事件，处理器的执行顺序是按照添加到 ChannelPipeline 的顺序执行
                         */
                        pipeline
                                // 添加处理器
                                .addLast();
                    }
                });

        /*
         * Netty 所有操作都是异步的
         * 会返回 Future 对象
         * 可以利用这个对象可以实现异步操作的通知
         *
         **/
        ChannelFuture future = null;
        try {
            // 绑定端口，启动服务器；sync() 方法会阻塞，直到绑定成功
            future = serverBootstrap.bind(port).sync();
            log.info(">>>>> Netty 服务器监听的端口：{}", port);
            if (future.isSuccess()) {
                log.info(">>>>> Netty 服务器启动成功");
            }

            /**
             * closeFuture()：返回一个 ChannelFuture 对象，表示通道关闭的 Future
             * closeFuture().sync() 也是阻塞方法，直到通道关闭
             * 这个阻塞方法起到的作用：
             * 1. 将 Netty 服务端的线程设置 wait 状态，那么 SpringBoot 的主线程就不会进入到 finally
             * 2. 执行 Netty 服务端关闭的代码
             */
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            log.info(">>>>>Netty 服务端关闭....");
            // 优雅关闭组件
            // if (bossGroup !=null) {
            //     bossGroup.shutdownGracefully();
            // }
            // if (workerGroup != null) {
            //     workerGroup.shutdownGracefully();
            // }
            // if (channel != null) {
            //     channel.closeFuture();
            // }
            destroy();
        }
    }

    /**
     * PreDestroy 的作用：对象销毁之前会执行 @PreDestroy 所修饰的方法
     * Netty 关闭
     */
    @PreDestroy
    public void destroy() {
        try {
            if (bossGroup != null)
                bossGroup.shutdownGracefully().sync();
            if (workerGroup != null)
                workerGroup.shutdownGracefully().sync();
            if (channel != null)
                channel.closeFuture().syncUninterruptibly();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Netty Server 以另一个线程启动
     * @param args 启动参数
     * @throws Exception
     */
    @Async
    @Override
    public void run(String... args) throws Exception {
        start();
    }
}
