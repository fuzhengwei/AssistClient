package com.icss.bp.dao;

import com.icss.bp.bean.BPOrderCigInfoBean;

public interface BPOrderCigInfoDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BP_ORDER_CIG_INFO
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long ociId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BP_ORDER_CIG_INFO
     *
     * @mbggenerated
     */
    int insert(BPOrderCigInfoBean record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BP_ORDER_CIG_INFO
     *
     * @mbggenerated
     */
    int insertSelective(BPOrderCigInfoBean record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BP_ORDER_CIG_INFO
     *
     * @mbggenerated
     */
    BPOrderCigInfoBean selectByPrimaryKey(Long ociId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BP_ORDER_CIG_INFO
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(BPOrderCigInfoBean record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BP_ORDER_CIG_INFO
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(BPOrderCigInfoBean record);
}