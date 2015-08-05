package com.icss.bp.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.icss.bp.agreement.MsgAgreement;
import com.icss.bp.agreement.MsgBPEnum;
import com.icss.bp.agreement.MsgCategoryEnum;
import com.icss.bp.bean.BPOrderInfoBean;
import com.icss.bp.dao.BPOrderInfoDao;
import com.icss.intfc.IACBusinessControl;
import com.icss.socket.user.UserSocketChannel;
import com.icss.util.json.MyBPJsonUtil;
import com.icss.util.log.AcInfoLogBean;
import com.icss.util.mybatis.MybatisSqlSession;

/**
 * 基础数据抽取线程
 * 
 * @author Administrator
 * 
 * @version 1.0
 * 
 */
public class BaseBPDateExtractionService implements Runnable {
	// 日志
	private Logger log = Logger.getLogger("AC");
	// SqlSession
	private static SqlSession session = null;
	// OrderDao
	private BPOrderInfoDao orderInfoDao = null;
	// BPBusinessControl
	private IACBusinessControl businessControl = null;
	// sleep count
	private int sleepCount = 0;
	// 日志对象
	private AcInfoLogBean logBean = new AcInfoLogBean();
	
	public BaseBPDateExtractionService(IACBusinessControl businessControl) {

		// 获得session
		session = MybatisSqlSession.getSqlSessionPrototype();
		// 获得dao操作
		orderInfoDao = session.getMapper(BPOrderInfoDao.class);
		// 获得业务控制
		this.businessControl = businessControl;
	}

	@Override
	public void run() {

		while (true) {

			// 从数据库抽取订单基础数据
			List<BPOrderInfoBean> orderInfoList = orderInfoDao
					.selectBaseOrderInfoList();

			// 空轮循休眠控制
			if (orderInfoList.size() < 1) {

				sleepCount++;

				try {
					if (sleepCount >= 5) {
						Thread.sleep(50000);
						sleepCount = 0;
						// 轮询自动提交
						doCommit();
						
						if(log.isInfoEnabled()){
							logBean.setOperation("轮训休眠等待超时处理");
							logBean.setImpClass("BaseBPDateExtractionService");
							logBean.setImpMethod("Thread run");
							logBean.setOperationResults("发送数据后长时间收不到反馈或数据库已无数据，自动提交 ，进行数据重发");
							
							log.info(logBean);
						}
						
					} else {
						Thread.sleep(3000);
					}

				} catch (InterruptedException e) {
				}
				continue;
			}

			// 按照协议封装
			MsgAgreement msgAgreement = new MsgAgreement();
			msgAgreement.setMsgCategoryEnum(MsgCategoryEnum.BARCODEPRINTER_BP);
			msgAgreement
					.setMsgBPEnum(MsgBPEnum.C2S_ORDERBASEINFO);
			msgAgreement.setMsgBodyBPOrderBaseInfoList(orderInfoList);

			// 对象转JSON字符串
			String jsonStr = MyBPJsonUtil.entity2Json(msgAgreement);

			if (null != UserSocketChannel.userChannel) {
				if (null != jsonStr && jsonStr.length() > 0) {
					// 通过socket发送数据到服务端
					UserSocketChannel.userChannel.writeAndFlush(jsonStr);
				}
			} else {
				// 短线后自动重连
				businessControl.doIntelligenceReconnection();
			}

			orderInfoList.clear();
			
			if(log.isInfoEnabled()){
				logBean.setOperation("C2S_BASEORDERINFO 客户端向服务端传递_基础订单信息");
				logBean.setImpClass("BaseBPDateExtractionService");
				logBean.setImpMethod("Thread run");
				logBean.setOperationResults("jsonStr 长度："+jsonStr.length());
				
				log.info(logBean);
			}
			
			/**
			 * 预留3秒用于接收数据存库
			 */
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				
				logBean.setOperation("C2S_BASEORDERINFO 客户端向服务端传递_基础订单信息");
				logBean.setImpClass("BaseBPDateExtractionService");
				logBean.setImpMethod("Thread run");
				logBean.setErrMsg(e.getMessage());
				
				log.error(logBean);
			}
		}

	}
	
	/**
	 * 接收反馈后提交使用的session 此时才会从数据库抽取到数据
	 */
	static public void doCommit() {
		session.commit();
	}

}
