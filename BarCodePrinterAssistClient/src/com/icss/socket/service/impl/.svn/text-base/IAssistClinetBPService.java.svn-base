package com.icss.socket.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.icss.bp.agreement.MsgAgreement;
import com.icss.bp.agreement.MsgBPEnum;
import com.icss.bp.agreement.MsgCategoryEnum;
import com.icss.bp.dao.BPOrderInfoDao;
import com.icss.bp.service.BaseBPDateExtractionService;
import com.icss.bp.service.CodeBPDateExtractionService;
import com.icss.socket.service.MsgAgreementHandleService;
import com.icss.util.json.MyBPJsonUtil;
import com.icss.util.log.AcInfoLogBean;
import com.icss.util.mybatis.MybatisSqlSession;

/**
 * IAssistClinetBPService
 * 
 * 商业打码数据处理服务
 * 
 * @author FUZHENGWEI
 * 
 */
public class IAssistClinetBPService implements MsgAgreementHandleService {

	// 日志
	private Logger log = Logger.getLogger("AC");
	// SqlSession
	private SqlSession session = null;
	// OrderDao
	private BPOrderInfoDao orderInfoDao = null;
	// 基础日志对象
	private AcInfoLogBean logInfoBean = new AcInfoLogBean();

	public IAssistClinetBPService() {
		// 获得session
		session = MybatisSqlSession.getSqlSessionPrototype();
		// 获得dao操作
		orderInfoDao = session.getMapper(BPOrderInfoDao.class);
	}

	public void doHandleMsg(Object msg) {

		try {

			// 字符串反序列化
			MsgAgreement msgAgreement = MyBPJsonUtil.json2Entity(msg);

			// 消息处理
			if (msgAgreement.getMsgCategoryEnum() == MsgCategoryEnum.BARCODEPRINTER_BP) {

				// 基础数据反馈
				if (msgAgreement.getMsgBPEnum() == MsgBPEnum.S2C_ORDERBASEFEED) {
					try {
						int xx = orderInfoDao
								.updateBaseFeedStateByOrderUUID(msgAgreement
										.getMsgFeedBackList());
						// 提交
						session.commit();
						// 提交
						BaseBPDateExtractionService.doCommit();

						if(log.isInfoEnabled()){
							logInfoBean.setOperation("S2C_ORDERBASEFEED 服务端向客户端传递_基础订单信息反馈");
							logInfoBean.setImpClass("AssistClientHanlder");
							logInfoBean.setImpMethod("channelRead");
							logInfoBean.setOperationResults("更新数据数量：" + xx);

							// 添加日志
							log.info(logInfoBean);
						}
						
					} catch (Exception e) {
						logInfoBean.setOperation("S2C_ORDERBASEFEED 服务端向客户端传递_基础订单信息反馈");
						logInfoBean.setImpClass("AssistClientHanlder");
						logInfoBean.setImpMethod("channelRead");
						logInfoBean.setErrMsg(e.getMessage());
						log.error(logInfoBean);
					}
				}
				// 打码数据反馈
				else if (msgAgreement.getMsgBPEnum() == MsgBPEnum.S2C_ORDERCODEFEED) {

					try {
						int xx = orderInfoDao
								.updateCodeFeedStateByOrderUUID(msgAgreement
										.getMsgFeedBackList());
						// 提交
						session.commit();

						if(log.isInfoEnabled()){
							logInfoBean.setOperation("S2C_ORDERCODEFEED 服务端向客户端传递_打码订单信息反馈");
							logInfoBean.setImpClass("AssistClientHanlder");
							logInfoBean.setImpMethod("channelRead");
							logInfoBean.setOperationResults("更新数据数量：" + xx);

							log.info(logInfoBean);
						}
						
					} catch (Exception e) {
						logInfoBean.setOperation("S2C_ORDERCODEFEED 服务端向客户端传递_打码订单信息反馈");
						logInfoBean.setImpClass("AssistClientHanlder");
						logInfoBean.setImpMethod("channelRead");
						logInfoBean.setErrMsg(e.getMessage());
						log.error(logInfoBean);
					}
				}

			} else {
				logInfoBean.setOperation("接收服务端向客户端传递信息，根据枚举判断业务类型");
				logInfoBean.setImpClass("AssistClientHanlder");
				logInfoBean.setImpMethod("channelRead");
				logInfoBean.setErrMsg("接收到异常枚举："+msgAgreement.getMsgCategoryEnum().toString());
				log.error(logInfoBean);
			}

		} catch (Exception e) {
			session.rollback();
			
			logInfoBean.setOperation("接收服务端向客户端传递信息");
			logInfoBean.setImpClass("AssistClientHanlder");
			logInfoBean.setImpMethod("channelRead");
			logInfoBean.setErrMsg(msg+"\r\n"+e.getMessage());
			log.error(logInfoBean);
			
		} finally {
			// 提交
			CodeBPDateExtractionService.doCommit();
		}

	}

}
