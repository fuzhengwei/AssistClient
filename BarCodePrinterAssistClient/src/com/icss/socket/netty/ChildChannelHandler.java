package com.icss.socket.netty;

import java.nio.charset.Charset;

import com.icss.socket.codec.IcssStringDecoder;
import com.icss.socket.codec.IcssStringEncoder;
import com.icss.socket.common.CharsetEnum;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

	
	@Override
	protected void initChannel(SocketChannel e) throws Exception {

		// 粘包处理中以03为结尾并处理后去掉03
		e.pipeline().addLast(
				new DelimiterBasedFrameDecoder(1024 * 512, true,
						new ByteBuf[] { Unpooled
								.wrappedBuffer(new byte[] { 03 }) }));
		// "GBK"
		e.pipeline().addLast(
				new IcssStringDecoder(
						Charset.forName(CharsetEnum.GBK.getName())));
		// "GBK"
		e.pipeline().addLast(
				new IcssStringEncoder(
						Charset.forName(CharsetEnum.GBK.getName())));
		// 信息处理
		e.pipeline().addLast(new AssistClientHanlder());

	}

}
