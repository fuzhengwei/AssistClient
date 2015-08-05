package com.icss.pp.dao;

import java.util.List;

import com.icss.pp.agreement.MsgFeedBack;
import com.icss.pp.bean.PPCigInfoBean;

public interface PPCigInfoDao {

	/**
	 * 抽取条码基础数据
	 * 
	 * @return List<PPCigInfoBean>
	 */
	List<PPCigInfoBean> selectCigInfoList();

	/**
	 * 抽取条码状态数据[计划结转]
	 * 
	 * @return List<PPCigInfoBean>
	 */
	List<PPCigInfoBean> selectCigInfoBaseStateList();

	/**
	 * 抽取条码打码数据 [基础数据已发送的，未给服务端发反馈的，已经打码的]
	 * 
	 * @return
	 */
	List<PPCigInfoBean> selectCigInfoCodeStateList();

	/**
	 * 抽取条码反馈数据[基础数据已发送的，未给服务端发送反馈的，已经向质量追溯平台回传的]
	 * 
	 * @return
	 */
	List<PPCigInfoBean> selectCigInfoBackStateList();

	/**
	 * 结转计划更新基础数据已发送数据状态为待发送部分数据
	 * 
	 * @param baluuid
	 * @return
	 */
	int updateCigInfoBaseFeedState(String baluuid);

	/**
	 * 更新基础数据发送状态为1已发送
	 * @param id
	 * @return
	 */
	int updateCigInfoBaseStateListById(List<MsgFeedBack> list);
	
	/**
	 *  更新打码状态数据发送状态为1已发送
	 * @param id
	 * @return
	 */
	int updateCigInfoCodeStateListById(List<MsgFeedBack> list);
	
	/**
	 *  更新回送状态数据发送状态为1已发送
	 * @param id
	 * @return
	 */
	int updateCigInfoBackStateListById(List<MsgFeedBack> list);
	
}