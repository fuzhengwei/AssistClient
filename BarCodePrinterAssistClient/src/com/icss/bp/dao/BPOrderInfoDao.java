package com.icss.bp.dao;

import java.util.List;

import com.icss.bp.agreement.MsgFeedBack;
import com.icss.bp.bean.BPOrderInfoBean;
import com.icss.bp.bean.VOrderCodeStateBean;

public interface BPOrderInfoDao {

    /**
     * selectBaseOrderInfoList
     * 
     * This method is create by fuzhengwei
     * This method corresponds to the database table list BP_ORDER_INFO
     * 
     * @return
     */
    List<BPOrderInfoBean> selectBaseOrderInfoList();
    
    /**
     * selectCodeOrderInfoList
     * 
     * This method is create by fuzhengwei
     * This method corresponds to the database table list VOrderCodeStateBean
     * @return
     * 
     */
    List<VOrderCodeStateBean> selectCodeOrderInfoList();
    
    /**
     * updateBaseFeedStateByOrderUUID
     * 
     * This method is create by fuzhengwei
     * This method corresponds to the database table BP_ORDER_INFO
     *
     * @param oiUuid
     * @return
     */
    int updateBaseFeedStateByOrderUUID(List<MsgFeedBack> list);
    
    /**
     * updateCodeFeedStateByOrderUUID
     * 
     * This method is create by fuzhengwei
     * This method corresponds to the database table BP_ORDER_INFO
     * 
     * @param oiUuid
     * @return
     */
    int updateCodeFeedStateByOrderUUID(List<MsgFeedBack> list);
}