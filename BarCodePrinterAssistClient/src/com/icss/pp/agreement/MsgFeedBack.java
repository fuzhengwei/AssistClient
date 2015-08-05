package com.icss.pp.agreement;

public class MsgFeedBack {

	// id
	private String id;
	// 状态【true 成功，false 失败】
	private boolean state;
	// 信息
	private String info;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "MsgFeedBack [id=" + id + ", state=" + state + ", info=" + info
				+ "]";
	}

}
