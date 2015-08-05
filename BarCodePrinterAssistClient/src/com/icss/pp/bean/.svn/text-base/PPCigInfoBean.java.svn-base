package com.icss.pp.bean;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.icss.util.date.DateSerializer;
import com.icss.util.globle.BaseConfig;
import com.icss.util.xor.IcssXor;

public class PPCigInfoBean {

	// 唯一id【工业打码】【服务端数据库为了区分各个库表存在次字段，各个客户端虚拟出此字段】
    private String printerid;
	// 主键 自动增长
	private Long id;
	// 原表中id
	private Long ppid;
	// 排产计划表UUID
	private String baluuid;
	// 牌号规格代码
	private String cigbrand;
	// 牌号规格
	private String cigbrandcode;
	// 产地
	private String factcode;
	// 打码生产时间
	private Date producedate;
	// 打码自然时间
	private Date naturaldate;
	// 经营方式
	private Short tradetype;
	// 序列号
	private String serialnum;
	// 计划生产日期
	private Date planproducedate;
	// 码段标识
	private String barcodeflag;
	// 打码标识
	private Short printstatus;
	// 回送标识
	private Short sendbackflag;
	// 扫描标识
	private Short scanstate;
	// 生产信息
	private String productinfo;
	// 基础数据
	private String oiBaseFeedState;
	// 打码状态
	private String oiCodeFeedState;
	// 回送状态
	private String oiBackFeedState;
	
	public String getPrinterid() {
		return printerid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
		setPpid(id);
	}

	public Long getPpid() {
		return ppid;
	}

	public void setPpid(Long ppid) {
		this.ppid = ppid;
	}

	public String getBaluuid() {
		return baluuid;
	}

	public void setBaluuid(String baluuid) {
		this.baluuid = baluuid == null ? null : baluuid.trim();
	}

	public String getCigbrand() {
		return cigbrand;
	}

	public void setCigbrand(String cigbrand) {
		this.cigbrand = cigbrand == null ? null : cigbrand.trim();
	}
	
	public String getCigbrandcode() {
		return cigbrandcode;
	}

	public void setCigbrandcode(String cigbrandcode) {
		this.cigbrandcode = cigbrandcode;
	}

	public String getFactcode() {
		return factcode;
	}

	public void setFactcode(String factcode) {
		this.factcode = factcode == null ? null : factcode.trim();
	}

	@JsonSerialize(using = DateSerializer.class)
	public Date getProducedate() {
		return producedate;
	}

	public void setProducedate(Date producedate) {
		this.producedate = producedate;
	}

	@JsonSerialize(using = DateSerializer.class)
	public Date getNaturaldate() {
		return naturaldate;
	}

	public void setNaturaldate(Date naturaldate) {
		this.naturaldate = naturaldate;
	}

	public Short getTradetype() {
		return tradetype;
	}

	public void setTradetype(Short tradetype) {
		this.tradetype = tradetype;
	}

	public String getSerialnum() {
		return IcssXor.DecodeString(serialnum);
	}

	public void setSerialnum(String serialnum) {
		this.serialnum = serialnum == null ? null : serialnum.trim();
	}

	@JsonSerialize(using = DateSerializer.class)
	public Date getPlanproducedate() {
		return planproducedate;
	}

	public void setPlanproducedate(Date planproducedate) {
		this.planproducedate = planproducedate;
	}

	public String getBarcodeflag() {
		return barcodeflag;
	}

	public void setBarcodeflag(String barcodeflag) {
		this.barcodeflag = barcodeflag == null ? null : barcodeflag.trim();
	}

	public Short getPrintstatus() {
		return printstatus;
	}

	public void setPrintstatus(Short printstatus) {
		this.printstatus = printstatus;
	}

	public Short getSendbackflag() {
		return sendbackflag;
	}

	public void setSendbackflag(Short sendbackflag) {
		this.sendbackflag = sendbackflag;
	}

	public Short getScanstate() {
		return scanstate;
	}

	public void setScanstate(Short scanstate) {
		this.scanstate = scanstate;
	}

	public String getProductinfo() {
		return productinfo;
	}

	public void setProductinfo(String productinfo) {
		this.productinfo = productinfo == null ? null : productinfo.trim();
	}

	public String getOiCodeFeedState() {
		return oiCodeFeedState;
	}

	public void setOiCodeFeedState(String oiCodeFeedState) {
		this.oiCodeFeedState = oiCodeFeedState == null ? null : oiCodeFeedState
				.trim();
	}

	public String getOiBackFeedState() {
		return oiBackFeedState;
	}

	public void setOiBackFeedState(String oiBackFeedState) {
		this.oiBackFeedState = oiBackFeedState == null ? null : oiBackFeedState
				.trim();
	}

	public String getOiBaseFeedState() {
		return oiBaseFeedState;
	}

	public void setOiBaseFeedState(String oiBaseFeedState) {
		this.oiBaseFeedState = oiBaseFeedState == null ? null : oiBaseFeedState
				.trim();
	}
}