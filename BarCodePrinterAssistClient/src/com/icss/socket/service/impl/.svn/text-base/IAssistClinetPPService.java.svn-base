package com.icss.socket.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.icss.pp.agreement.MsgAgreement;
import com.icss.pp.agreement.MsgCategoryEnum;
import com.icss.pp.agreement.MsgPPEnum;
import com.icss.pp.dao.PPArrangeDao;
import com.icss.pp.dao.PPCigInfoDao;
import com.icss.pp.service.BasePPArrangeDateExtractionService;
import com.icss.pp.service.BasePPCigInfoDateExtractionService;
import com.icss.pp.service.CodePPCigInfoDateExtractionService;
import com.icss.socket.service.MsgAgreementHandleService;
import com.icss.util.json.MyPPJsonUtil;
import com.icss.util.log.AcInfoLogBean;
import com.icss.util.mybatis.MybatisSqlSession;

public class IAssistClinetPPService implements MsgAgreementHandleService {
	// 日志
	private Logger log = Logger.getLogger("AC");
	// SqlSession sessionArrange
	private SqlSession sessionArrange = null;
	// SqlSession sessionCigInfo
	private SqlSession sessionCigInfo = null;
	// 计划数据库操作
	private PPArrangeDao arrangeDao = null;
	// 条码数据库操作
	private PPCigInfoDao cigInfoDao = null;
	// 基础日志对象
	private AcInfoLogBean logInfoBean = new AcInfoLogBean();
	
	public IAssistClinetPPService() {

		sessionArrange = MybatisSqlSession.getSqlSessionPrototype();
		sessionCigInfo = MybatisSqlSession.getSqlSessionPrototype();

		arrangeDao = sessionArrange.getMapper(PPArrangeDao.class);
		cigInfoDao = sessionCigInfo.getMapper(PPCigInfoDao.class);
	}

	@Override
	public void doHandleMsg(Object msg) {

		try {
			// 字符串反序列化
			MsgAgreement msgAgreement = MyPPJsonUtil.json2Entity(msg);

			// 消息处理
			if (msgAgreement.getMsgCategoryEnum() == MsgCategoryEnum.BARCODEPRINTER_PP) {

				// S2C_ARRANGEBASEFEED 服务端向客户端传递_基础信息反馈
				if (msgAgreement.getMsgPPEnum() == MsgPPEnum.S2C_ARRANGEBASEFEED) {
					try {
						int count = arrangeDao.updateArrangeBaseFeedStateListById(msgAgreement.getMsgFeedBackList());
						
						
						if(log.isInfoEnabled()){
							logInfoBean.setOperation("S2C_ARRANGEBASEFEED 服务端向客户端传递_基础信息反馈");
							logInfoBean.setImpClass("IAssistClinetPPService");
							logInfoBean.setImpMethod("doHandleMsg");
							logInfoBean.setOperationResults("更新数据数量：" + count);
							
							log.info(logInfoBean);
						}
						
						
						
					} catch (Exception e) {
						sessionArrange.rollback();
						
						logInfoBean.setOperation("S2C_ARRANGEBASEFEED 服务端向客户端传递_基础信息反馈");
						logInfoBean.setImpClass("IAssistClinetPPService");
						logInfoBean.setImpMethod("doHandleMsg");
						logInfoBean.setErrMsg(e.getMessage());
						
						log.error(logInfoBean);
						
					} finally{
						sessionArrange.commit();
						// 帮助提交
						BasePPArrangeDateExtractionService.doCommit();
					}
				}
				// S2C_CIGINFOBASEFEED 服务端向客户端传递_条码基础信息反馈
				else if (msgAgreement.getMsgPPEnum() == MsgPPEnum.S2C_CIGINFOBASEFEED) {

					try {
						int count = cigInfoDao.updateCigInfoBaseStateListById(msgAgreement.getMsgFeedBackList());
						
						if(log.isInfoEnabled()){
							logInfoBean.setOperation("S2C_CIGINFOBASEFEED 服务端向客户端传递_条码基础信息反馈");
							logInfoBean.setImpClass("IAssistClinetPPService");
							logInfoBean.setImpMethod("doHandleMsg");
							logInfoBean.setOperationResults("更新数据数量：" + count);
							
							log.info(logInfoBean);
						}
						
					} catch (Exception e) {
						sessionCigInfo.rollback();

						logInfoBean.setOperation("S2C_CIGINFOBASEFEED 服务端向客户端传递_条码基础信息反馈");
						logInfoBean.setImpClass("IAssistClinetPPService");
						logInfoBean.setImpMethod("doHandleMsg");
						logInfoBean.setErrMsg(e.getMessage());
						
						log.error(logInfoBean);
					}finally{
						sessionCigInfo.commit();
						BasePPCigInfoDateExtractionService.doCommit();
					}
					
				}
				// S2C_CIGINFOCODEFEED 服务端向客户端传递_条码打码状态反馈
				else if (msgAgreement.getMsgPPEnum() == MsgPPEnum.S2C_CIGINFOCODEFEED) {
					try {
						int count = cigInfoDao.updateCigInfoCodeStateListById(msgAgreement.getMsgFeedBackList());
						
						if(log.isInfoEnabled()){
							logInfoBean.setOperation("S2C_CIGINFOCODEFEED 服务端向客户端传递_条码打码状态反馈");
							logInfoBean.setImpClass("IAssistClinetPPService");
							logInfoBean.setImpMethod("doHandleMsg");
							logInfoBean.setOperationResults("更新数据数量：" + count);
							
							log.info(logInfoBean);
						}
						
					} catch (Exception e) {
						sessionCigInfo.rollback();
						logInfoBean.setOperation("S2C_CIGINFOCODEFEED 服务端向客户端传递_条码打码状态反馈");
						logInfoBean.setImpClass("IAssistClinetPPService");
						logInfoBean.setImpMethod("doHandleMsg");
						logInfoBean.setErrMsg(e.getMessage());
						
						log.error(logInfoBean);
					}finally{
						sessionCigInfo.commit();
						CodePPCigInfoDateExtractionService.doCommit();
					}
				}
				// S2C_CIGINFOBACKFEED 服务端向客户端传递_条码发送信息反馈
				else if (msgAgreement.getMsgPPEnum() == MsgPPEnum.S2C_CIGINFOBACKFEED) {
					try {
						int count = cigInfoDao.updateCigInfoBackStateListById(msgAgreement.getMsgFeedBackList());
						
						if(log.isInfoEnabled()){
							logInfoBean.setOperation("S2C_CIGINFOBACKFEED 服务端向客户端传递_条码发送信息反馈");
							logInfoBean.setImpClass("IAssistClinetPPService");
							logInfoBean.setImpMethod("doHandleMsg");
							logInfoBean.setOperationResults("更新数据数量：" + count);
							
							log.info(logInfoBean);
						}
						
					} catch (Exception e) {
						sessionCigInfo.rollback();
						
						logInfoBean.setOperation("S2C_CIGINFOCODEFEED 服务端向客户端传递_条码打码状态反馈");
						logInfoBean.setImpClass("IAssistClinetPPService");
						logInfoBean.setImpMethod("doHandleMsg");
						logInfoBean.setErrMsg(e.getMessage());
						
						log.error(logInfoBean);
						
					}finally{
						sessionCigInfo.commit();
						CodePPCigInfoDateExtractionService.doCommit();
					}
				} else {
					// 0概率出现
					System.out.println("数据异常");
				}

			} else {
				// 0概率出现
				System.out.println("异常消息");
			}

		} catch (Exception e) {
			
			logInfoBean.setOperation("工业打码socket接收消息处理");
			logInfoBean.setImpClass("IAssistClinetPPService");
			logInfoBean.setImpMethod("doHandleMsg");
			logInfoBean.setErrMsg(e.getMessage());
			
			log.error(logInfoBean);
		}

	}

}
