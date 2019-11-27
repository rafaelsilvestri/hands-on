package com.challenge.bank.account.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

@Component
public class HttpEndpointInitializer extends ChannelInitializer<SocketChannel>{

	@Autowired
	private HttpEndpointHandler httpEndpointHandler;
	
    @Override
    public void initChannel(SocketChannel channel) throws Exception {
		ChannelPipeline pipeline = channel.pipeline();
		pipeline.addLast(new HttpRequestDecoder());
		pipeline.addLast(new HttpObjectAggregator(1048576));
		pipeline.addLast(new HttpResponseEncoder());
		pipeline.addLast(httpEndpointHandler);
    }
}
