package org.jiegiser.smartchargestation.demo;
/*
 * @Auther: jiegiser
 * @Date:2026/6/22
 * @Description: Netty启动类
 * @Modified By:
 */

import org.jiegiser.smartchargestation.demo.netty.NettyServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 *
 * Netty 的启动方式：
 *
 * 1. 使用接口 CommandLineRunner
 * 2. 使用 @PostConStruct (Spring 容器管理)
 *
 * CommandLineRunner:
 * SpringBoot 提供的作为数据的预加载，
 * 当 SpringBoot 启动的时候，
 * 这个接口是会跟随 SpringBoot 执行代码逻辑，
 * 这个接口只会执行一次
 */
@SpringBootApplication
// 启动线程池（防止 Netty 阻塞 SpringBoot 主线程）
@EnableAsync
// public class NettyApplication implements CommandLineRunner {
public class NettyApplication {
    public static void main(String[] args) {
        SpringApplication.run(NettyApplication.class,args);
        System.out.println("SpringBoot Start....(验证 Netty 是否阻塞 SpringBoot 主线程序)");
    }

    // /**
    //  * Netty 启动(Netty Server 以另一个线程启动)
    //  * @param args
    //  * @throws Exception
    //  */
    // @Async
    // @Override
    // public void run(String... args) throws Exception {
    //     NettyServer nettyServer = new NettyServer();
    //     nettyServer.startNetty();
    // }
}
