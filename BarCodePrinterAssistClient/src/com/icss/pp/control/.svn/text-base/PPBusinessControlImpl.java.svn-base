package com.icss.pp.control;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.icss.intfc.IACBusinessControl;
import com.icss.pp.service.BasePPArrangeDateExtractionService;
import com.icss.pp.service.BasePPCigInfoDateExtractionService;
import com.icss.pp.service.CodePPCigInfoDateExtractionService;
import com.icss.socket.netty.NettyClient;
import com.icss.util.date.SyncTime;
import com.icss.util.log.AcInfoLogBean;

/**
 * IPPBusinessControl
 * 
 * @author FUZHENGWEI
 * 
 *         2015年4月3日 12:14:31
 * 
 *         工业打码业务控制
 */
public class PPBusinessControlImpl implements IACBusinessControl {
	// 日志
	private Logger log = Logger.getLogger("AC");
	// 线程池
	private ExecutorService es;
	// NettySocketClient
	private NettyClient nettyClient = null;
	// 基础计划数据抽取线程
	private BasePPArrangeDateExtractionService basePPArrangeDateExtractionService = null;
	// 基础条码数据&条码更新状态数据抽取线程
	private BasePPCigInfoDateExtractionService basePPCigInfoDateExtractionService = null;
	// 打码状态&回送状态数据抽取线程
	private CodePPCigInfoDateExtractionService codePPCigInfoDateExtractionService = null;
	// 基础日志对象
	private AcInfoLogBean logInfoBean = new AcInfoLogBean();
	
	public PPBusinessControlImpl() {
		// 初始化
		doInitBaseBusiness();
		
		if(log.isInfoEnabled()){
			logInfoBean.setOperation("工业打码业务控制初始化完成");
			logInfoBean.setImpClass("PPBusinessControlImpl");
			logInfoBean.setImpMethod("构造函数");
			logInfoBean.setOperationResults("初始化完成");
			
			log.info(logInfoBean);
		}
		
	}

	@Override
	public void doInitBaseBusiness() {
		//启动软件修改本地时间为国家局标准时间
		//doModifyLocalTime();
		// 实例化NettyClient
		doInitNettyClient();
		// 实例化自己业务
		doPPDateExtractionService();
	}

	/**
	 * 修改本地时间为国家局标准时间
	 */
	public void doModifyLocalTime(){
		SyncTime syncTime = new SyncTime();
		syncTime.doModifyLocalTimeByGJJVPN();
	}
	
	@Override
	public void doInitNettyClient() {
		try {
			if (null == nettyClient) {
				// 实例化NettyClient
				nettyClient = new NettyClient();
			}
		} catch (Exception e) {
			logInfoBean.setOperation("初始化socket客户端失败");
			logInfoBean.setImpClass("PPBusinessControlImpl");
			logInfoBean.setImpMethod("doInitNettyClient");
			logInfoBean.setErrMsg(e.getMessage());
			log.error(logInfoBean);
		}
	}

	public void doPPDateExtractionService() {

		try {

			if (null == basePPArrangeDateExtractionService) {
				basePPArrangeDateExtractionService = new BasePPArrangeDateExtractionService(
						this);
			}

			if (null == basePPCigInfoDateExtractionService) {
				basePPCigInfoDateExtractionService = new BasePPCigInfoDateExtractionService(
						this);
			}
			
			if(null == codePPCigInfoDateExtractionService){
				codePPCigInfoDateExtractionService = new CodePPCigInfoDateExtractionService(this);
			}

		} catch (Exception e) {
			logInfoBean.setOperation("初始化 basePPArrangeDateExtractionService 失败");
			logInfoBean.setImpClass("PPBusinessControlImpl");
			logInfoBean.setImpMethod("doInitNettyClient");
			logInfoBean.setErrMsg(e.getMessage());
			log.error(logInfoBean);
		}

	}

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
					logInfoBean.setImpClass("PPBusinessControlImpl");
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
			logInfoBean.setImpClass("PPBusinessControlImpl");
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
				logInfoBean.setImpClass("PPBusinessControlImpl");
				logInfoBean.setImpMethod("doOpenThreadPool");
				logInfoBean.setErrMsg(e.getMessage());
				log.error(logInfoBean);
			}
		}

		// 基础数据抽取线程
		es.execute(basePPArrangeDateExtractionService);
		// 基础条码数据&条码更新状态数据抽取线程【结转计划】
		es.execute(basePPCigInfoDateExtractionService);
		// 打码状态&回送状态数据抽取线程
		es.execute(codePPCigInfoDateExtractionService);
	}

	@Override
	public void doCloseThreadPool() {
		try {
			// 退出Executor
			es.shutdown();

		} catch (Exception e) {
			logInfoBean.setOperation("线程池关闭异常");
			logInfoBean.setImpClass("PPBusinessControlImpl");
			logInfoBean.setImpMethod("doCloseThreadPool");
			logInfoBean.setErrMsg(e.getMessage());
			log.error(logInfoBean);
		}

	}

}
