package com.icss.socket.codec;

import java.nio.charset.Charset;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

public class IcssStringDecoder extends MessageToMessageDecoder<ByteBuf> {

	 // TODO Use CharsetDecoder instead.
    private final Charset charset;
    
    /**
     * Creates a new instance with the current system character set.
     */
    public IcssStringDecoder() {
    	
        this(Charset.defaultCharset());
        
    }

    /**
     * Creates a new instance with the specified character set.
     */
    public IcssStringDecoder(Charset charset) {
        if (charset == null) {
            throw new NullPointerException("charset");
        }
        this.charset = charset;
    }
	
    
	protected void decode(ChannelHandlerContext ctx, ByteBuf msg,
			List<Object> out) throws Exception {
		//跳过四个字节00 00 00 00
		msg = msg.skipBytes(4);
		//剩余字节按照指定编码转字符串
		out.add(msg.toString(charset));
	}
	
}
