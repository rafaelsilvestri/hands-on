package com.ilegra.netty.training.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class Server {

    private int port;

    public Server(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        int port = (args.length == 0) ? 7001 : Integer.valueOf(args[0]);
        new Server(port).start();
    }


    private void start() throws InterruptedException {
        EchoHandler echoHandler = new EchoHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap server = new ServerBootstrap();
            server.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(7001))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(echoHandler);
                        }
                    });

            ChannelFuture future = server.bind().sync();

            System.out.println("Server initialized");

            future.channel().closeFuture().sync();

        } finally {
            group.shutdownGracefully().sync();
        }
    }

}
