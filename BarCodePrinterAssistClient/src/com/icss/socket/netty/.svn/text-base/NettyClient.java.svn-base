package com.icss.socket.netty;

import com.icss.util.globle.BaseConfig;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient implements Runnable {

	// ChannelFuture
	private ChannelFuture f;

	/**
	 * isActiveSocketServer
	 * 
	 * @return boolean
	 */
	public boolean isActiveSocketClient() {
		try {
			if (f != null) {
				return f.channel().isActive();
			} else {
				return false;
			}

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void run() {

		EventLoopGroup group = new NioEventLoopGroup();

		try {

			Bootstrap b = new Bootstrap();

			b.group(group); // group 组
			b.channel(NioSocketChannel.class); // channel 通道
			b.option(ChannelOption.TCP_NODELAY, true); // option 选项
			b.handler(new ChildChannelHandler()); // handler 处理

			// 发起异步链接
			f = b.connect(BaseConfig.inetHost, BaseConfig.inetPort);

			// 等待客户端链路关闭
			f.channel().closeFuture().sync();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}

	}

}
