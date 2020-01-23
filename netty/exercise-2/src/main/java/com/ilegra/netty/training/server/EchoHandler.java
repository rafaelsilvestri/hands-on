package com.ilegra.netty.training.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledDirectByteBuf;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

@ChannelHandler.Sharable
public class EchoHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        String data = in.toString(CharsetUtil.UTF_8);
        System.out.println("Server received: " + data);

        String[] numbers = data.split("-");


        int result = Service.sum(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]));

        ByteBuf out = Unpooled.buffer();
        out.writeBytes(String.valueOf(result).getBytes(CharsetUtil.UTF_8));
        ctx.writeAndFlush(out);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("Qualquer coisa").addListener(ChannelFutureListener.CLOSE);
    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        cause.printStackTrace();
//        ctx.close();
//    }
}
