package com.icss.intfc;

/**
 * 业务控制接口
 * 
 * @author FUZHENGWEI
 * 
 */
public interface IACBusinessControl {

	/**
	 * 
	 * doInitBaseBusiness
	 * 
	 * 初始化基础业务数据
	 * 
	 */
	public abstract void doInitBaseBusiness();

	/**
	 * doInitNettyClient
	 */
	public abstract void doInitNettyClient();

	/**
	 * 
	 * doOnlyOpenNettyToAddThreadPool
	 * 
	 * 重新初始化Netty添加到现有线程池
	 * 
	 */
	public abstract void doIntelligenceReconnection();

	/**
	 * 
	 * doOpenThreadPool
	 * 
	 * 开启线程池
	 * 
	 */
	public abstract void doOpenThreadPool();

	/**
	 * doCloseThreadPool
	 * 
	 * 关闭线程池
	 * 
	 */
	public abstract void doCloseThreadPool();

}