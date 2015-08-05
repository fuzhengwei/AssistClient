package com.icss.bp.control;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.icss.bp.service.BaseBPDateExtractionService;
import com.icss.bp.service.CodeBPDateExtractionService;
import com.icss.intfc.IACBusinessControl;
import com.icss.socket.netty.NettyClient;
import com.icss.util.log.AcInfoLogBean;

/**
 * IBPBusinessControl
 * 
 * @author FUZHENGWEI
 * 
 *         2015年4月3日 12:14:31
 * 
 *         打码业务控制
 */
public class BPBusinessControlImpl implements IACBusinessControl {

	// 日志
	private Logger log = Logger.getLogger("AC");
	// 线程池
	private ExecutorService es;
	// NettySocketClient
	private NettyClient nettyClient = null;
	// 基础数据线程
	private BaseBPDateExtractionService baseBPDateExtractionService = null;
	// 打码数据线程
	private CodeBPDateExtractionService codeBPDateExtractionService = null;
	// 基础日志对象
	private AcInfoLogBean logInfoBean = new AcInfoLogBean();
	
	public BPBusinessControlImpl() {
		// 初始化
		doInitBaseBusiness();
		
		if(log.isInfoEnabled()){
			logInfoBean.setOperation("商业打码控制层初始化完成");
			logInfoBean.setImpClass("BPBusinessControlImpl");
			logInfoBean.setImpMethod("构造函数");
			logInfoBean.setOperationResults("初始化完成");
			
			log.info(logInfoBean);
		}
		
	}

	/* (non-Javadoc)
	 * @see com.icss.bp.control.ACBusinessControl#doInitBaseBusiness()
	 */
	@Override
	public void doInitBaseBusiness() {

		// 实例化NettyClient
		doInitNettyClient();
		// 实例化数据抽取
		doBPDateExtractionService();

	}

	/* (non-Javadoc)
	 * @see com.icss.bp.control.ACBusinessControl#doInitNettyClient()
	 */
	@Override
	public void doInitNettyClient() {
		try {
			if (null == nettyClient) {
				// 实例化NettyClient
				nettyClient = new NettyClient();
			}
		} catch (Exception e) {
			logInfoBean.setOperation("初始化socket客户端失败");
			logInfoBean.setImpClass("BPBusinessControlImpl");
			logInfoBean.setImpMethod("doInitNettyClient");
			logInfoBean.setErrMsg(e.getMessage());
			log.error(logInfoBean);
		}
	}

	public void doBPDateExtractionService() {

		try {
			if (null == baseBPDateExtractionService) {
				// 实例化抽取基础数据线程
				baseBPDateExtractionService = new BaseBPDateExtractionService(
						this);
			}

			if (null == codeBPDateExtractionService) {
				// 实例化抽取打码数据线程
				codeBPDateExtractionService = new CodeBPDateExtractionService(
						this);
			}

		} catch (Exception e) {
			logInfoBean.setOperation("初始化 baseBPDateExtractionService and codeBPDateExtractionService 失败");
			logInfoBean.setImpClass("BPBusinessControlImpl");
			logInfoBean.setImpMethod("doInitNettyClient");
			logInfoBean.setErrMsg(e.getMessage());
			log.error(logInfoBean);
		}

	}

	/* (non-Javadoc)
	 * @see com.icss.bp.control.ACBusinessControl#doIntelligenceReconnection()
	 */
	@Override
	public void doIntelligenceReconnection() {

		// es 不为空，非Shutdown
		if (null != es && !es.isShutdown()) {

			// 如果为空重新实例化
			if (null == nettyClient) {
				nettyClient = new NettyClient();
			}

			// 校验是否开启socket连接
			while (!nettyClient.isActiveSocketClient()) {

				try {
					System.out.println("5 秒钟断线重连 ... ...");

					// NettyClient启动线程
					es.execute(nettyClient);

					Thread.sleep(5000);

				} catch (InterruptedException e) {
					logInfoBean.setOperation("socket 重连失败信息");
					logInfoBean.setImpClass("BPBusinessControlImpl");
					logInfoBean.setImpMethod("doIntelligenceReconnection");
					logInfoBean.setErrMsg(e.getMessage());
					log.error(logInfoBean);
				}

			}
		} else {

			// 初始化基础业务
			doInitBaseBusiness();

			// 如果线程池已关闭，那么重新初始化
			doOpenThreadPool();

		}

	}

	/* (non-Javadoc)
	 * @see com.icss.bp.control.ACBusinessControl#doOpenThreadPool()
	 */
	@Override
	public void doOpenThreadPool() {

		es = Executors.newCachedThreadPool();

		// NettyClient启动线程
		es.execute(nettyClient);

		// 休眠5秒，连续等待
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			logInfoBean.setOperation("线程池");
			logInfoBean.setImpClass("BPBusinessControlImpl");
			logInfoBean.setImpMethod("doOpenThreadPool");
			logInfoBean.setErrMsg(e.getMessage());
			log.error(logInfoBean);
		}

		// 校验是否开启socket连接
		while (!nettyClient.isActiveSocketClient()) {

			try {
				System.out.println("5 秒钟断线重连 ... ...");

				// NettyClient启动线程
				es.execute(nettyClient);
				Thread.sleep(5000);

			} catch (InterruptedException e) {
				logInfoBean.setOperation("socket 重连");
				logInfoBean.setImpClass("BPBusinessControlImpl");
				logInfoBean.setImpMethod("doOpenThreadPool");
				logInfoBean.setErrMsg(e.getMessage());
				log.error(logInfoBean);
			}

		}

		// 基础数据抽取线程
		es.execute(baseBPDateExtractionService);

		// 打码数据抽取线程
	    es.execute(codeBPDateExtractionService);
	}

	/* (non-Javadoc)
	 * @see com.icss.bp.control.ACBusinessControl#doCloseThreadPool()
	 */
	@Override
	public void doCloseThreadPool() {

		try {
			// 退出Executor
			es.shutdown();

		} catch (Exception e) {
			logInfoBean.setOperation("线程池关闭异常");
			logInfoBean.setImpClass("BPBusinessControlImpl");
			logInfoBean.setImpMethod("doCloseThreadPool");
			logInfoBean.setErrMsg(e.getMessage());
			log.error(logInfoBean);
		}

	}

}
