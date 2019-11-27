package com.challenge.bank.account.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

@Component
public class Server {

	@Autowired
	private HttpEndpointInitializer httpEndpointInitializer;
	
	private ChannelFuture channelFuture;
	private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    
	public void run(int port) throws InterruptedException {
		bossGroup = new NioEventLoopGroup();
	    workerGroup = new NioEventLoopGroup();
	    try {
	        ServerBootstrap server = new ServerBootstrap(); 
	        server.group(bossGroup, workerGroup)
	         .channel(NioServerSocketChannel.class) 
	         .childHandler(httpEndpointInitializer)
	         
	         .option(ChannelOption.SO_BACKLOG, 128)        
	         .childOption(ChannelOption.SO_KEEPALIVE, true); 
	
	        channelFuture = server.bind(port).sync(); 	
	        channelFuture.channel().closeFuture().sync();
	    } finally {
	        workerGroup.shutdownGracefully();
	        bossGroup.shutdownGracefully();
	    }	
		
	}
	
	public void stop() {
		ChannelFuture close = channelFuture.channel().close();
		close.awaitUninterruptibly();
		
	    if (workerGroup != null) {
	        workerGroup.shutdownGracefully();
	        workerGroup = null;
	    }

	    if (bossGroup != null) {
	        bossGroup.shutdownGracefully();
	        bossGroup = null;
	    }
	}
	
}
