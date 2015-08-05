package com.icss.pp.dao;

import java.util.List;

import com.icss.pp.agreement.MsgFeedBack;
import com.icss.pp.bean.PPArrangeBean;

public interface PPArrangeDao {

	/**
	 * 查询计划数量，每次查询10组
	 * @return
	 */
	List<PPArrangeBean> selectArrangeList();

	/**
	 * 根据id修改OI_BASE_FEED_STATE状态为1已发送
	 * @param id
	 * @return
	 */
	int updateArrangeBaseFeedStateListById(List<MsgFeedBack> list);

}