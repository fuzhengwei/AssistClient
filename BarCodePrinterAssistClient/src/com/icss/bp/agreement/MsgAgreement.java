package com.icss.bp.agreement;

import java.util.List;

import com.icss.bp.bean.BPOrderInfoBean;
import com.icss.bp.bean.VOrderCodeStateBean;

public class MsgAgreement {

	/**
	 * MsgCategoryEnum 消息类别[商业打码软件、工业打码软件、扫描软件]
	 */
	private MsgCategoryEnum msgCategoryEnum;

	/**
	 * MsgBarCodePrinterEnum 商业打码软件消息类型
	 */
	private MsgBPEnum msgBPEnum;

	/**
	 * msgBodyBPOrderBaseInfo 商业打码_基础信息消息体
	 */
	private List<BPOrderInfoBean> msgBodyBPOrderBaseInfoList;

	/**
	 * msgBodyBPOrderCodeInfo 商业打码_打码信息消息体
	 */
	private List<VOrderCodeStateBean> msgBodyBPOrderCodeInfoList;

	/**
	 * 反馈信息
	 */
	private List<MsgFeedBack> msgFeedBackList;

	public MsgCategoryEnum getMsgCategoryEnum() {
		return msgCategoryEnum;
	}

	public void setMsgCategoryEnum(MsgCategoryEnum msgCategoryEnum) {
		this.msgCategoryEnum = msgCategoryEnum;
	}

	public MsgBPEnum getMsgBPEnum() {
		return msgBPEnum;
	}

	public void setMsgBPEnum(MsgBPEnum msgBPEnum) {
		this.msgBPEnum = msgBPEnum;
	}

	public List<BPOrderInfoBean> getMsgBodyBPOrderBaseInfoList() {
		return msgBodyBPOrderBaseInfoList;
	}

	public void setMsgBodyBPOrderBaseInfoList(
			List<BPOrderInfoBean> msgBodyBPOrderBaseInfoList) {
		this.msgBodyBPOrderBaseInfoList = msgBodyBPOrderBaseInfoList;
	}

	public List<VOrderCodeStateBean> getMsgBodyBPOrderCodeInfoList() {
		return msgBodyBPOrderCodeInfoList;
	}

	public void setMsgBodyBPOrderCodeInfoList(
			List<VOrderCodeStateBean> msgBodyBPOrderCodeInfoList) {
		this.msgBodyBPOrderCodeInfoList = msgBodyBPOrderCodeInfoList;
	}

	public List<MsgFeedBack> getMsgFeedBackList() {
		return msgFeedBackList;
	}

	public void setMsgFeedBackList(List<MsgFeedBack> msgFeedBackList) {
		this.msgFeedBackList = msgFeedBackList;
	}

}
