package com.icss.pp.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.icss.intfc.IACBusinessControl;
import com.icss.pp.agreement.MsgAgreement;
import com.icss.pp.agreement.MsgCategoryEnum;
import com.icss.pp.agreement.MsgPPEnum;
import com.icss.pp.bean.PPCigInfoBean;
import com.icss.pp.dao.PPCigInfoDao;
import com.icss.socket.user.UserSocketChannel;
import com.icss.util.json.MyPPJsonUtil;
import com.icss.util.log.AcInfoLogBean;
import com.icss.util.mybatis.MybatisSqlSession;

/**
 * 
 * 方法：打码状态|打码反馈抽取线程
 * 
 * 规范：
 * 
 * A:当OI_CODE_FEED_STATE打码状态为0时，抽取
 * 
 * B:当OI_BACK_FEED_STATE反馈状态为0时，抽取
 * 
 * 逻辑：
 * 
 * 从数据库中轮训抽取A
 * 
 * 当A抽取N次后开始抽取一次B
 * 
 * @author FUZHENGWEI 2015年4月20日 11:23:48
 */
public class CodePPCigInfoDateExtractionService implements Runnable {
	// 日志
	private Logger log = Logger.getLogger("AC");
	// 基础日志对象
	private AcInfoLogBean logInfoBean = new AcInfoLogBean();
	// SqlSession
	private static SqlSession session = null;
	// 工业打码计划数据库操作方法
	private PPCigInfoDao cigInfoDao = null;
	// BPBusinessControl
	private IACBusinessControl businessControl = null;
	// 消息协议
	private MsgAgreement msgAgreement = null;
	// sleep count
	private int sleepCount = 0;
	// 打码状态抽取次数
	private int cigInfoCodeCount = 4;

	public CodePPCigInfoDateExtractionService(IACBusinessControl businessControl) {
		try {

			this.businessControl = businessControl;

			// 获得session
			session = MybatisSqlSession.getSqlSessionPrototype();
			// 获得DAO实例
			cigInfoDao = session.getMapper(PPCigInfoDao.class);

			// 协议固定头
			msgAgreement = new MsgAgreement();
			msgAgreement.setMsgCategoryEnum(MsgCategoryEnum.BARCODEPRINTER_PP);
		} catch (Exception e) {

			logInfoBean.setOperation("基础计划数据抽取线程方法");
			logInfoBean.setImpClass("CodePPCigInfoDateExtractionService");
			logInfoBean.setImpMethod("构造函数");
			logInfoBean.setErrMsg(e.getMessage());
			log.error(logInfoBean);

		}

	}

	@Override
	public void run() {

		// json字符串
		String jsonStr = "";

		while (true) {

			try {

				// 抽取打码状态数据
				if (cigInfoCodeCount > 1) {
					List<PPCigInfoBean> cigInfoCodeStateList = cigInfoDao
							.selectCigInfoCodeStateList();

					cigInfoCodeCount--;

					// 数据验证
					if (!doSleep(cigInfoCodeStateList.size())) {
						cigInfoCodeCount = 1;
						continue;
					}

					msgAgreement.setMsgPPEnum(MsgPPEnum.C2S_CIGINFOCODEINFO);
					msgAgreement.setMsgBodyPPCigInfoList(cigInfoCodeStateList);

					// 对象转JSON字符串
					jsonStr = MyPPJsonUtil.entity2Json(msgAgreement);

					if(log.isInfoEnabled()){
						logInfoBean
								.setOperation("C2S_CIGINFOCODEINFO 客户端向服务端传递_条码打码状态");
						logInfoBean
								.setImpClass("CodePPCigInfoDateExtractionService");
						logInfoBean.setImpMethod("Thread run");
						logInfoBean.setOperationResults("jsonStr 长度："
								+ jsonStr.length());
						log.info(logInfoBean);
					}

				}
				// 抽取回送状态数据
				else {
					// 重新设置为20
					cigInfoCodeCount = 4;
					List<PPCigInfoBean> cigInfoBackStateList = cigInfoDao
							.selectCigInfoBackStateList();

					// 数据验证
					if (!doSleep(cigInfoBackStateList.size())) {
						continue;
					}

					msgAgreement.setMsgPPEnum(MsgPPEnum.C2S_CIGINFOBACKINFO);
					msgAgreement.setMsgBodyPPCigInfoList(cigInfoBackStateList);

					// 对象转JSON字符串
					jsonStr = MyPPJsonUtil.entity2Json(msgAgreement);

					logInfoBean
							.setOperation("C2S_CIGINFOBACKINFO 客户端向服务端传递_条码发送信息");
					logInfoBean
							.setImpClass("CodePPCigInfoDateExtractionService");
					logInfoBean.setImpMethod("Thread run");
					logInfoBean.setOperationResults("jsonStr 长度："
							+ jsonStr.length());
					log.warn(logInfoBean);

				}

				if (null != UserSocketChannel.userChannel) {
					if (null != jsonStr && jsonStr.length() > 0) {
						// 通过socket发送数据到服务端
						UserSocketChannel.userChannel.writeAndFlush(jsonStr);
					}
				} else {
					// 短线后自动重连
					businessControl.doIntelligenceReconnection();
				}

				/**
				 * 预留3秒用于接收数据存库
				 */
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			} catch (Exception e) {
				logInfoBean
						.setOperation("C2S_CIGINFOCODEINFO|C2S_CIGINFOBACKINFO");
				logInfoBean.setImpClass("CodePPCigInfoDateExtractionService");
				logInfoBean.setImpMethod("Thread run");
				logInfoBean.setErrMsg(e.getMessage());
				log.error(logInfoBean);
			}

		}

	}

	/**
	 * 控制休眠
	 */
	private boolean doSleep(int size) {

		// 空轮循休眠控制
		if (size < 1) {

			sleepCount++;

			try {
				if (sleepCount >= 5) {
					Thread.sleep(50000);
					sleepCount = 0;
					doCommit();
				} else {
					Thread.sleep(3000);
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			return false;
		} else {
			return true;
		}

	}

	/**
	 * 接收反馈后提交使用的session 此时才会从数据库抽取到数据
	 */
	static public void doCommit() {
		session.commit();
	}

}
