package com.icss.socket.service;

/**
 * 消息协议处理接口
 * 
 * 用于处理；商业打码、工业打码，数据
 * 
 * @author FUZHENGWEI
 *
 */
public interface MsgAgreementHandleService {

	/**
	 * 处理消息
	 * 根据不同消息类型进行存库
	 * @param msg
	 */
	public void doHandleMsg(Object msg);
	
}
