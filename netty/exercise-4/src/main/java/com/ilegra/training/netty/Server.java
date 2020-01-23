package com.ilegra.training.netty;

import io.servicetalk.http.netty.HttpServers;
import io.servicetalk.http.router.jersey.HttpJerseyRouterBuilder;
import io.servicetalk.transport.api.ServerContext;

public class Server {

	public static void main(String[] args) throws Exception {
		// Create configurable starter for HTTP server.
		ServerContext serverContext = HttpServers.forPort(8080)
				.listenStreamingAndAwait(new HttpJerseyRouterBuilder()
						.buildStreaming(new Application()));


		// Blocks and awaits shutdown of the server this ServerContext represents.
		serverContext.awaitShutdown();
	}
}
