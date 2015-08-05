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
 * 方法： 基础条码抽取线程
 * 
 * 规范：
 * 
 * A:当OI_BASE_FEED_STATE=0时，完整发送
 * 
 * B:当OI_BASE_FEED_STATE=1时，不发送
 * 
 * C:当OI_BASE_FEED_STATE=2时，只发送id与uuid，用于服务端更新使用
 * 
 * 逻辑：
 * 
 * 从数据库中交替抽取A、C状态
 * 
 * 当A状态为0时，C状态发送N次后再发送A
 * 
 * 当C状态为0时，A状态发送N次后在发送C
 * 
 * 当AC状态都为0时，休眠N秒后再开始发送
 * 
 * @author FUZHENGWEI 2015年4月20日 AM
 * 
 */
public class BasePPCigInfoDateExtractionService implements Runnable {

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
	// 基础数据抽取次数
	private int cigBaseCount = 0;
	// 条码状态抽取次数
	private int cigStateCount = 0;
	// 轮训抽取
	private boolean whichOneEx = true;

	public BasePPCigInfoDateExtractionService(IACBusinessControl businessControl) {
		try {

			this.businessControl = businessControl;
			// 获得session
			session = MybatisSqlSession.getSqlSessionPrototype();
			// 获得DAO实例
			cigInfoDao = session.getMapper(PPCigInfoDao.class);

			msgAgreement = new MsgAgreement();
			msgAgreement.setMsgCategoryEnum(MsgCategoryEnum.BARCODEPRINTER_PP);
			msgAgreement.setMsgPPEnum(MsgPPEnum.C2S_CIGINFOBASEINFO);
		} catch (Exception e) {

			logInfoBean.setOperation("基础计划数据抽取线程方法");
			logInfoBean.setImpClass("BasePPCigInfoDateExtractionService");
			logInfoBean.setImpMethod("构造函数");
			logInfoBean.setErrMsg(e.getMessage());
			log.error(logInfoBean);

		}
	}

	@Override
	public void run() {

		while (true) {

			try {

				// 轮训抽取逻辑
				if (whichOneEx) {
					whichOneEx = false;
					if (cigBaseCount > 0) {
						whichOneEx = true;
						cigBaseCount--;
					}
				} else {
					whichOneEx = true;
					if (cigStateCount > 0) {
						whichOneEx = false;
						cigStateCount--;
					}
				}

				List<PPCigInfoBean> cigInfoList = null;

				// 抽取条码基础信息
				if (!whichOneEx && cigBaseCount < 1) {
					// 从数据库抽取基础信息数据
					cigInfoList = cigInfoDao.selectCigInfoList();
					// 验证抽取数量
					if (cigInfoList.size() <= 0) {
						cigBaseCount = 5;
					}
				}

				// 抽取条码更改信息
				if (whichOneEx && cigStateCount < 1) {
					// 从数据库抽取状态修改数据
					cigInfoList = cigInfoDao.selectCigInfoBaseStateList();
					// 验证抽取数量
					if (cigInfoList.size() <= 0) {
						cigStateCount = 5;
					}
				}

				// 验证是否空数据轮训休眠
				if (!doSleep(cigBaseCount, cigStateCount)) {
					continue;
				}

				// 发送前验证发送数据是否为空或者内容为空
				if (null == cigInfoList || cigInfoList.size() < 1) {
					continue;
				}

				// 设置协议发送内容
				msgAgreement.setMsgBodyPPCigInfoList(cigInfoList);

				// 对象转JSON字符串
				String jsonStr = MyPPJsonUtil.entity2Json(msgAgreement);

				if (log.isInfoEnabled()) {
					logInfoBean
							.setOperation("C2S_CIGINFOBASEINFO 客户端向服务端传递_条码基础信息");
					logInfoBean
							.setImpClass("BasePPCigInfoDateExtractionService");
					logInfoBean.setImpMethod("Thread run");
					logInfoBean.setOperationResults("jsonStr 长度："
							+ jsonStr.length());
					log.info(logInfoBean);
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
						.setOperation("C2S_CIGINFOBASEINFO 客户端向服务端传递_条码基础信息");
				logInfoBean.setImpClass("BasePPCigInfoDateExtractionService");
				logInfoBean.setImpMethod("Thread run");
				logInfoBean.setErrMsg(e.getMessage());
				log.error(logInfoBean);
			}

		}

	}

	/**
	 * 控制休眠
	 */
	private boolean doSleep(int cigBaseCount, int cigStateCount) {

		// 空轮循休眠控制
		if (cigBaseCount > 0 && cigStateCount > 0) {

			cigBaseCount = 0;

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
