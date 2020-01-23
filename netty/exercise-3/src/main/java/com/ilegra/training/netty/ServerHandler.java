package com.ilegra.training.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

public class ServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        FullHttpResponse response = null;

        if (HttpMethod.GET.equals(request.method()) && request.uri().startsWith("/training/netty/sum")) {
            QueryStringDecoder docoder = new QueryStringDecoder(request.uri());
            Integer num1 = Integer.valueOf(docoder.parameters().get("num1").get(0));
            Integer num2 = Integer.valueOf(docoder.parameters().get("num2").get(0));

            int result = Service.sum(num1, num2);

            response = new DefaultFullHttpResponse(request.protocolVersion(),
                    HttpResponseStatus.OK, Unpooled.wrappedBuffer(Integer.toString(result).getBytes()));
        } else {
            response = new DefaultFullHttpResponse(request.protocolVersion(),
                    HttpResponseStatus.NOT_FOUND);
        }

        response.headers()
                .set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.TEXT_PLAIN)
                .set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes())
                .set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE);

        ChannelFuture channelFuture = ctx.write(response);
        channelFuture.addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
