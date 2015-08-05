package com.icss.socket.codec;

import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

public class IcssStringEncoder extends MessageToMessageEncoder<CharSequence> {

	// TODO Use CharsetEncoder instead.
	private final Charset charset;

	private final byte[] headByte = { 0, 0, 0, 0 }, bottomByte = { 3 };

	private String headByteString = new String(headByte);

	private String bottomByteString = new String(bottomByte);

	/**
	 * Creates a new instance with the current system character set.
	 */
	public IcssStringEncoder() {
		this(Charset.defaultCharset());
	}

	/**
	 * Creates a new instance with the specified character set.
	 */
	public IcssStringEncoder(Charset charset) {
		if (charset == null) {
			throw new NullPointerException("charset");
		}
		this.charset = charset;
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, CharSequence msg,
			List<Object> out) throws Exception {

		if (msg.length() == 0) {
			return;
		}

		out.add(ByteBufUtil.encodeString(ctx.alloc(), CharBuffer
				.wrap(headByteString + msg + bottomByteString), charset));

		 //仅供测试【缺少头部信息00 00 00 00】
//		 out.add(ByteBufUtil.encodeString(ctx.alloc(), CharBuffer.wrap(msg +
//		 bottomByteString), charset));
	}

}
