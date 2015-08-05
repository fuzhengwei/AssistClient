package com.icss.pp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.icss.pp.agreement.MsgAgreement;
import com.icss.pp.agreement.MsgCategoryEnum;
import com.icss.intfc.IACBusinessControl;
import com.icss.pp.agreement.MsgPPEnum;
import com.icss.pp.bean.PPArrangeBean;
import com.icss.pp.dao.PPArrangeDao;
import com.icss.pp.dao.PPCigInfoDao;
import com.icss.socket.user.UserSocketChannel;
import com.icss.util.json.MyPPJsonUtil;
import com.icss.util.log.AcInfoLogBean;
import com.icss.util.mybatis.MybatisSqlSession;

/**
 * 基础计划数据抽取线程
 * 
 * 从数据库中抽取计划
 * 
 * 1.当计划没有被抽取，那么直接发送
 * 
 * 2.当计划有新增计划，那么发送新增计划与原计划，同时把新增计划下的条码发送状态归0，为未发送
 * 
 * 3.每次从数据库中抽取的计划与内存中存在的计划做对比，当计划有内容更新，发送更新字段
 * 
 * @author FUZHENGWEI 2015年4月17日
 */
public class BasePPArrangeDateExtractionService implements Runnable {
	// 日志
	private Logger log = Logger.getLogger("AC");
	// 基础日志对象
	private AcInfoLogBean logInfoBean = new AcInfoLogBean();
	// SqlSession
	private static SqlSession session = null;
	private static SqlSession sessionCig = null;
	// 工业打码计划数据库操作方法
	private PPArrangeDao arrangeDao = null;
	// 工业打码条码数据库操作方法
	private PPCigInfoDao cigInfoDao = null;
	// BPBusinessControl
	private IACBusinessControl businessControl = null;
	// 消息协议
	private MsgAgreement msgAgreement = null;
	// sleep count
	private int sleepCount = 0;

	// 计划键值对集合<id,计划>
	private Map<Long, PPArrangeBean> arrangeMap = null;
	// 等待删除集合【比对后在线程内删除】
	private List<PPArrangeBean> waitDelArrangeList = null;

	public BasePPArrangeDateExtractionService(IACBusinessControl businessControl) {
		try {
			// 获得session
			session = MybatisSqlSession.getSqlSessionPrototype();
			// 获得dao操作
			arrangeDao = session.getMapper(PPArrangeDao.class);

			// 获得session
			sessionCig = MybatisSqlSession.getSqlSessionPrototype();
			// 获得dao操作
			cigInfoDao = sessionCig.getMapper(PPCigInfoDao.class);
			// 获得业务控制
			this.businessControl = businessControl;

			// 实例化计划map集合
			arrangeMap = new HashMap<Long, PPArrangeBean>();

			waitDelArrangeList = new ArrayList<PPArrangeBean>();

			msgAgreement = new MsgAgreement();
			msgAgreement.setMsgCategoryEnum(MsgCategoryEnum.BARCODEPRINTER_PP);
			msgAgreement.setMsgPPEnum(MsgPPEnum.C2S_ARRANGEBASEINFO);
		} catch (Exception e) {

			logInfoBean.setOperation("基础计划数据抽取线程方法");
			logInfoBean.setImpClass("BasePPArrangeDateExtractionService");
			logInfoBean.setImpMethod("构造函数");
			logInfoBean.setErrMsg(e.getMessage());
			log.error(logInfoBean);

		}

	}

	@Override
	public void run() {

		while (true) {

			try {
				// 从计划表抽取待发送计划
				List<PPArrangeBean> arrangeList = arrangeDao
						.selectArrangeList();
				// 休眠控制
				if (!doSleep(arrangeList.size())) {
					continue;
				}
				// 发送内容校验
				arrangeList.removeAll(doCheckArrangeList(arrangeList));

				if (arrangeList == null || arrangeList.size() < 1) {
					continue;
				}
				// 设置协议发送内容
				msgAgreement.setMsgBodyPPArrangeList(arrangeList);

				// 对象转JSON字符串
				String jsonStr = MyPPJsonUtil.entity2Json(msgAgreement);

				if(log.isInfoEnabled()){
					logInfoBean
							.setOperation("C2S_ARRANGEBASEINFO 客户端向服务端传递_计划基础信息");
					logInfoBean.setImpClass("BasePPArrangeDateExtractionService");
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
					System.out.println("断线后自动重连");
					// 断线后自动重连
					businessControl.doIntelligenceReconnection();
				}

				/**
				 * 预留3秒用于接收数据存库
				 */
				try {
					System.out.println("休眠3秒钟");
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			} catch (Exception e) {
				logInfoBean
						.setOperation("C2S_ARRANGEBASEINFO 客户端向服务端传递_计划基础信息");
				logInfoBean.setImpClass("BasePPArrangeDateExtractionService");
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
				} else {
					Thread.sleep(3000);
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			doCommit();

			return false;
		} else {
			return true;
		}

	}

	/**
	 * 检查待发送计划集合
	 * 
	 * @param arrangeList
	 */
	public List<PPArrangeBean> doCheckArrangeList(
			List<PPArrangeBean> arrangeList) {

		waitDelArrangeList.clear();

		// 判断是否有结转计划[如果本地内存有计划，且新获得的计划大于本地内存计划那么存在结转计划]
		if (arrangeMap.keySet().size() > 0
				&& arrangeList.size() > arrangeMap.keySet().size()) {
			try {
				// 遍历集合
				for (PPArrangeBean ppArrangeBean : arrangeList) {

					// 执行数据库操作将结转后的计划已经发送了的把发送状态全部标记为2
					if (null == arrangeMap.get(ppArrangeBean.getId())) {
						// 执行数据库操作
						cigInfoDao.updateCigInfoBaseFeedState(ppArrangeBean
								.getBaluuid());
					}

				}
			} catch (Exception e) {
				sessionCig.rollback();
			} finally {
				sessionCig.commit();
			}

		}

		// 计划验证
		for (PPArrangeBean ppArrangeBean : arrangeList) {

			// 判断内存中是否存在当前计划
			if (null == arrangeMap.get(ppArrangeBean.getId())) {
				// 不存在就添加进去
				arrangeMap.put(ppArrangeBean.getId(), ppArrangeBean);
				// 更改对象发送类型
				if (ppArrangeBean.getOiBaseFeedState().equals("1")) {
					// 数据做为更新数据发送
					ppArrangeBean.setType(3);
				}

			} else {

				// 可变字段，与内存中的计划做对比，如果完全相同，则把此计划删除
				PPArrangeBean contrastModel = arrangeMap.get(ppArrangeBean
						.getId());
				// 还需新增
				if (contrastModel.getApplynum().equals(
						ppArrangeBean.getApplynum())) {
					waitDelArrangeList.add(ppArrangeBean);
				}

			}

		}

		return waitDelArrangeList;

	}

	/**
	 * 接收反馈后提交使用的session 此时才会从数据库抽取到数据
	 */
	static public void doCommit() {
		session.commit();
	}

}
