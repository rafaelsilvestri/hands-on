package com.challenge.bank.account.server;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpHeaderValues.APPLICATION_JSON;
import static io.netty.handler.codec.http.HttpHeaderValues.CLOSE;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpResponseStatus.NOT_FOUND;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.challenge.bank.account.model.TransactionModel;
import com.challenge.bank.account.service.AccountService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.QueryStringDecoder;

@Component
@Sharable
public class HttpEndpointHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

	private static ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private AccountService accountService;
	
	
	@Override
	protected void channelRead0(ChannelHandlerContext context, FullHttpRequest fullHttpRequest) throws Exception {
		if (fullHttpRequest instanceof FullHttpRequest) {

			FullHttpResponse response = null;

			if (HttpMethod.POST.equals(fullHttpRequest.method()) && "/account/deposit".equals(fullHttpRequest.uri())) {
				response = deposit(fullHttpRequest);
				
			} else if (HttpMethod.GET.equals(fullHttpRequest.method()) && fullHttpRequest.uri().startsWith("/account/history")) {
				response = history(fullHttpRequest);
			}

			if (response == null) {
				response = createResponse(fullHttpRequest, NOT_FOUND, "url not found".getBytes());
			}

			boolean keepAlive = HttpUtil.isKeepAlive(fullHttpRequest);
			if (keepAlive) {
				if (!fullHttpRequest.protocolVersion().isKeepAliveDefault()) {
					response.headers().set(CONNECTION, KEEP_ALIVE);
				}
			} else {
				response.headers().set(CONNECTION, CLOSE);
			}

			ChannelFuture channelFuture = context.write(response);

			if (!keepAlive) {
				channelFuture.addListener(ChannelFutureListener.CLOSE);
			}
		}
	}

	private FullHttpResponse deposit(FullHttpRequest fullHttpRequest)
			throws IOException, JsonParseException, JsonMappingException, JsonProcessingException {

		ByteBuf content = fullHttpRequest.content();

		String jsonRequest = content.toString(StandardCharsets.UTF_8);
		TransactionRequest transaction = objectMapper.readValue(jsonRequest, TransactionRequest.class);
		TransactionModel transactionModel = new TransactionModel();
		transactionModel.setId(transaction.getId());
		transactionModel.setValue(transaction.getValue());
		
		accountService.deposit(transactionModel);
		
		String json = objectMapper.writeValueAsString(transaction);
		return createResponse(fullHttpRequest, OK, json.getBytes());	
	}

	private FullHttpResponse history(FullHttpRequest fullHttpRequest) throws JsonProcessingException {
		QueryStringDecoder decoder = new QueryStringDecoder(fullHttpRequest.uri());	
		List<String> accountIds = decoder.parameters().get("accountId");
		
		if(accountIds == null || accountIds.isEmpty()) {			
			return createResponse(fullHttpRequest, BAD_REQUEST, "account id is required".getBytes());			
		}
		
		List<Double> history = accountService.history(accountIds.get(0));
		
		String json = objectMapper.writeValueAsString(history);				
		return createResponse(fullHttpRequest, OK, json.getBytes());	
	}
	

	private FullHttpResponse createResponse(FullHttpRequest fullHttpRequest, HttpResponseStatus status, byte[] result) {
		FullHttpResponse response = new DefaultFullHttpResponse(fullHttpRequest.protocolVersion(), status,
				Unpooled.wrappedBuffer(result));
		response.headers().set(CONTENT_TYPE, APPLICATION_JSON).setInt(CONTENT_LENGTH,
				response.content().readableBytes());
		return response;
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext context) {
		context.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
		cause.printStackTrace();
		context.close();
	}

}
