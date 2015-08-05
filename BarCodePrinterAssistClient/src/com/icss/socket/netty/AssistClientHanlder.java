package com.icss.socket.netty;

import com.icss.socket.service.MsgAgreementHandleService;
import com.icss.socket.service.impl.IAssistClinetBPService;
import com.icss.socket.service.impl.IAssistClinetPPService;
import com.icss.socket.user.UserSocketChannel;
import com.icss.util.globle.BaseConfig;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class AssistClientHanlder extends ChannelHandlerAdapter {

	// 消息处理
	private MsgAgreementHandleService msgHandleService;

	// 构造函数
	public AssistClientHanlder() {

		// 根据配置实例化
		switch (BaseConfig.ACType) {
		case 1:
			msgHandleService = new IAssistClinetBPService();
			break;
		case 2:
			msgHandleService = new IAssistClinetPPService();
			break;
		default:
			break;
		}

	}

	/*
	 * channelAction
	 * 
	 * channel 通道 action 活跃的
	 * 
	 * 当客户端主动链接服务端的链接后，这个通道就是活跃的了。也就是客户端与服务端建立了通信通道并且可以传输数据
	 */
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		System.out.println("客户端与服务端通道-开启：" + ctx.channel().localAddress()
				+ "channelActive");

		// 绑定channel
		UserSocketChannel.userChannel = ctx;

	}

	/*
	 * channelInactive
	 * 
	 * channel 通道 Inactive 不活跃的
	 * 
	 * 当客户端主动断开服务端的链接后，这个通道就是不活跃的。也就是说客户端与服务端的关闭了通信通道并且不可以传输数据
	 */
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {

		System.out.println("客户端与服务端通道-关闭：" + ctx.channel().localAddress()
				+ "channelInactive");

		// 捕获关闭
		ctx.close();

		UserSocketChannel.userChannel = null;
	}

	/*
	 * channelRead
	 * 
	 * channel 通道 Read 读
	 * 
	 * 简而言之就是从通道中读取数据，也就是服务端接收客户端发来的数据 但是这个数据在不进行解码时它是ByteBuf类型的后面例子我们在介绍
	 */
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		msgHandleService.doHandleMsg(msg);
	}

	/*
	 * channelReadComplete
	 * 
	 * channel 通道 Read 读取 Complete 完成
	 * 
	 * 在通道读取完成后会在这个方法里通知，对应可以做刷新操作 ctx.flush()
	 */
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	/*
	 * exceptionCaught
	 * 
	 * exception 异常 Caught 抓住
	 * 
	 * 抓住异常，当发生异常的时候，可以做一些相应的处理，比如打印日志、关闭链接
	 */
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {

		UserSocketChannel.userChannel = null;

		ctx.close();
		System.out.println("异常退出:" + cause.getMessage());
	}

}
