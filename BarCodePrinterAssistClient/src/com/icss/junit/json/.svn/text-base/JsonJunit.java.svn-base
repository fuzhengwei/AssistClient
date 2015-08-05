package com.icss.junit.json;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icss.bp.bean.BPBatchBean;
import com.icss.bp.bean.BPOrderInfoBean;
import com.icss.bp.dao.BPBatchDao;
import com.icss.bp.dao.BPOrderInfoDao;
import com.icss.util.mybatis.MybatisSqlSession;

public class JsonJunit {

private SqlSession session = null;
	
	private BPBatchDao batchDao = null;
	
	private BPOrderInfoDao orderInfoDao = null;
	
	// JSON对象
	private ObjectMapper objectMapper = null;
	// 写入流
	private Writer strWriter = null;
	
	@Before
	public void before() {
		
		session = MybatisSqlSession.getSqlSessionSingle();
		
	    batchDao = session.getMapper(BPBatchDao.class);
		
	    orderInfoDao = session.getMapper(BPOrderInfoDao.class);
	    
	    objectMapper = new ObjectMapper();
		strWriter = new StringWriter();
	    
		System.out.println("初始化 SqlSession ... ...");
	}
	
	@Test
	public void testJson(){
		
		List<BPOrderInfoBean> orderInfoList = orderInfoDao.selectBaseOrderInfoList();
		
		System.out.println(orderInfoList);
		
		String strJson = "";
		
		try {
			objectMapper.writeValue(strWriter, orderInfoList);
			strJson = strWriter.toString();
			strWriter.flush();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		System.out.println(strJson);
		
	}
	

}
