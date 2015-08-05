package com.icss.junit.sqlserver;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import com.icss.bp.agreement.MsgFeedBack;
import com.icss.bp.bean.BPBatchBean;
import com.icss.bp.bean.BPOrderInfoBean;
import com.icss.bp.bean.VOrderCodeStateBean;
import com.icss.bp.dao.BPBatchDao;
import com.icss.bp.dao.BPOrderInfoDao;
import com.icss.util.mybatis.MybatisSqlSession;

public class SqlServerJunit {

	private SqlSession session = null;
	
	private BPBatchDao batchDao = null;
	
	private BPOrderInfoDao orderInfoDao = null;
	
	@Before
	public void before() {
		
		session = MybatisSqlSession.getSqlSessionSingle();
		
	    batchDao = session.getMapper(BPBatchDao.class);
		
	    orderInfoDao = session.getMapper(BPOrderInfoDao.class);
	    
		System.out.println("初始化 SqlSession ... ...");
	}
	
	@Test
	public void testSelectBean(){
		
		BPBatchBean batchBean = batchDao.selectByPrimaryKey(158L);
		
		System.out.println(batchBean);
		
	}
	
	@Test
	public void testSelectOrderList(){
		
		List<BPOrderInfoBean> orderInfoList = orderInfoDao.selectBaseOrderInfoList();
		
		
		for(BPOrderInfoBean orderInfo : orderInfoList){
			
			System.out.println(orderInfo.getOiSortDate());
			
		}
	}
	
	@Test
	public void testSelectOrderCodeList(){
		
		List<VOrderCodeStateBean> orderCodeList = orderInfoDao.selectCodeOrderInfoList();
		
		for (VOrderCodeStateBean orderCode:orderCodeList) {
			System.out.println(orderCode);
		}
		
	}
	
	@Test
	public void 测试批量更新(){
		
		List<MsgFeedBack> msgFeedBackList = new ArrayList<MsgFeedBack>();
		
		MsgFeedBack mfb_01 = new MsgFeedBack();
		mfb_01.setId("d02c74c3-b76d-4c47-b026-f0101e2a1d58");
		
		MsgFeedBack mfb_02 = new MsgFeedBack();
		mfb_02.setId("637482a9-c7ed-47eb-8684-eb4519ccd8f9");
		
		MsgFeedBack mfb_03 = new MsgFeedBack();
		mfb_03.setId("ad3b156c-1f55-4acc-a3c5-4368ad9a08bd");
		
		msgFeedBackList.add(mfb_01);
		msgFeedBackList.add(mfb_02);
		msgFeedBackList.add(mfb_03);
		
		int i = orderInfoDao.updateBaseFeedStateByOrderUUID(msgFeedBackList);
		
		session.commit();
		
		System.out.println(i);
	}
	

}
