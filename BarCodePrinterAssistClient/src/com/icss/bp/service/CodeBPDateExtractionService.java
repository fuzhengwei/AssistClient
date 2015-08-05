package com.icss.bp.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.icss.bp.agreement.MsgAgreement;
import com.icss.bp.agreement.MsgBPEnum;
import com.icss.bp.agreement.MsgCategoryEnum;
import com.icss.bp.bean.VOrderCodeStateBean;
import com.icss.bp.dao.BPOrderInfoDao;
import com.icss.intfc.IACBusinessControl;
import com.icss.socket.user.UserSocketChannel;
import com.icss.util.json.MyBPJsonUtil;
import com.icss.util.log.AcInfoLogBean;
import com.icss.util.mybatis.MybatisSqlSession;

/**
 * 打码数据抽取线程
 * 
 * @author Administrator
 * 
 * @version 1.0
 * 
 */
public class CodeBPDateExtractionService implements Runnable {
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

	public CodeBPDateExtractionService(IACBusinessControl businessControl) {

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
			List<VOrderCodeStateBean> vOrderCodeStateList = orderInfoDao
					.selectCodeOrderInfoList();

			// 空轮循休眠控制
			if (vOrderCodeStateList.size() < 1) {

				sleepCount++;

				try {
					if (sleepCount >= 5) {
						Thread.sleep(10000);
						sleepCount = 0;
					} else {
						Thread.sleep(3000);
					}

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				doCommit();
				
				continue;
			}

			// 按照协议封装
			MsgAgreement msgAgreement = new MsgAgreement();
			msgAgreement.setMsgCategoryEnum(MsgCategoryEnum.BARCODEPRINTER_BP);
			msgAgreement
					.setMsgBPEnum(MsgBPEnum.C2S_ORDERCODEINFO);
			msgAgreement.setMsgBodyBPOrderCodeInfoList(vOrderCodeStateList);

			// 对象转json字符串
			String jsonStr = MyBPJsonUtil.entity2Json(msgAgreement);

			if (null != UserSocketChannel.userChannel) {
				// 通过socket发送数据到服务端
				UserSocketChannel.userChannel.writeAndFlush(jsonStr);
			} else {
				businessControl.doIntelligenceReconnection();
			}

			// 清空
			vOrderCodeStateList.clear();

			if(log.isInfoEnabled()){
				logBean.setOperation("C2S_ORDERCODEINFO 客户端向服务端传递_打码订单信息");
				logBean.setImpClass("CodeBPDateExtractionService");
				logBean.setImpMethod("Thread run");
				logBean.setOperationResults("jsonStr 长度：" + jsonStr.length());

				log.info(logBean);
			}
			
			/**
			 * 预留3秒用于接收数据存库
			 */
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				logBean.setOperation("C2S_ORDERCODEINFO 客户端向服务端传递_打码订单信息");
				logBean.setImpClass("CodeBPDateExtractionService");
				logBean.setImpMethod("Thread run");
				logBean.setErrMsg(e.getMessage());
				
				log.error(logBean);
			}
		}

	}

	/**
	 * 接收反馈后提交使用的session 此时才会从数据库抽取到数据
	 */
	public static void doCommit() {
		session.commit();
	}

}
