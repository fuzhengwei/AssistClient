package com.icss.pp.bean;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.icss.util.date.DateSerializer;
import com.icss.util.globle.BaseConfig;

public class PPArrangeBean {

	// 当前计划存库操作【1 增、2删、3改、4 查】
	private int type = 1;
	// ID 自增主键
	private Long id;
	// 原表中id
	private Long ppid;
	// 唯一id【工业打码】【服务端数据库为了区分各个库表存在次字段，各个客户端虚拟出此字段】
	private String printerid;
	// 计划序列号
	private Integer planno;
	// 排产标识 F:生产点排产 S:国家局排产 A:提前生产
	private String arrangeflag;
	// 管理机A表排产基本表UUID
	private String arrangeuuid;
	// 排产计划表UUID 主键，对应管理机C表排产基本表
	private String baluuid;
	// 结转前原排产计划表UUID 排产计划结转前计划UUID 再次结转的计划，该UUID还是最原始计划的UUID
	private String baluuidOriginal;
	// 管理机B表排产操作日期
	private Date barrangedate;
	// 生产日期
	private Date producedate;
	// 牌号规格代码
	private String cigbrandcode;
	// 品牌规格名称
	private String cigbrand;
	// 经营方式
	private Integer tradetype;
	// 班次编号
	private String classcode;
	// 结转前班次编号
	private String classcodeOriginal;
	// 排产量
	private Integer arrangenum;
	// 下发量
	private Integer downnum;
	// 已打印量
	private Integer printnum;
	// 已打码量
	private Integer applynum;
	// 初始化时间
	private Date inittime;
	// 计划开始执行时间
	private Date starttime;
	// 计划执行结束时间
	private Date endtime;
	// 计划结转状态
	private Short planflag;
	// 计划实施完成标识
	private Short planfinishflag;
	// 回送标识
	private Short sendbackflag;
	// 确认取码完毕管理机反馈状态
	private Short confirmflag;
	// 计划类型
	private Short arrangetype;
	// 车间内部编号
	private String workshopcode;
	// 车间名称
	private String workshopname;
	// 联营加工单据所属合同号
	private String contractNo;
	// 发货方编号
	private String sendCode;
	// 发货方名称
	private String sendName;
	// 需入库量
	private Integer inPnum;
	// 不可识别件码量
	private Integer notRecognisePnum;
	// 条码类型
	private Integer scannum;
	// 基础数据抽取状态
	private String oiBaseFeedState;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
		// 设置虚拟id给服务端用
		setPpid(id);
	}

	public Long getPpid() {
		return ppid;
	}

	public void setPpid(Long ppid) {
		this.ppid = ppid;
	}

	public String getPrinterid() {
		return printerid;
	}

	public void setPrinterid(String printerid) {
		this.printerid = printerid;
	}

	public Integer getPlanno() {
		return planno;
	}

	public void setPlanno(Integer planno) {
		this.planno = planno;
	}

	public String getArrangeflag() {
		return arrangeflag;
	}

	public void setArrangeflag(String arrangeflag) {
		this.arrangeflag = arrangeflag == null ? null : arrangeflag.trim();
	}

	public String getArrangeuuid() {
		return arrangeuuid;
	}

	public void setArrangeuuid(String arrangeuuid) {
		this.arrangeuuid = arrangeuuid == null ? null : arrangeuuid.trim();
	}

	public String getBaluuid() {
		return baluuid;
	}

	public void setBaluuid(String baluuid) {
		this.baluuid = baluuid == null ? null : baluuid.trim();
	}

	public String getBaluuidOriginal() {
		return baluuidOriginal;
	}

	public void setBaluuidOriginal(String baluuidOriginal) {
		this.baluuidOriginal = baluuidOriginal == null ? null : baluuidOriginal
				.trim();
	}

	@JsonSerialize(using = DateSerializer.class)
	public Date getBarrangedate() {
		return barrangedate;
	}

	public void setBarrangedate(Date barrangedate) {
		this.barrangedate = barrangedate;
	}

	@JsonSerialize(using = DateSerializer.class)
	public Date getProducedate() {
		return producedate;
	}

	public void setProducedate(Date producedate) {
		this.producedate = producedate;
	}

	public String getCigbrandcode() {
		return cigbrandcode;
	}

	public void setCigbrandcode(String cigbrandcode) {
		this.cigbrandcode = cigbrandcode == null ? null : cigbrandcode.trim();
	}

	public String getCigbrand() {
		return cigbrand;
	}

	public void setCigbrand(String cigbrand) {
		this.cigbrand = cigbrand == null ? null : cigbrand.trim();
	}

	public Integer getTradetype() {
		return tradetype;
	}

	public void setTradetype(Integer tradetype) {
		this.tradetype = tradetype;
	}

	public String getClasscode() {
		return classcode;
	}

	public void setClasscode(String classcode) {
		this.classcode = classcode == null ? null : classcode.trim();
	}

	public String getClasscodeOriginal() {
		return classcodeOriginal;
	}

	public void setClasscodeOriginal(String classcodeOriginal) {
		this.classcodeOriginal = classcodeOriginal == null ? null
				: classcodeOriginal.trim();
	}

	public Integer getArrangenum() {
		return arrangenum;
	}

	public void setArrangenum(Integer arrangenum) {
		this.arrangenum = arrangenum;
	}

	public Integer getDownnum() {
		return downnum;
	}

	public void setDownnum(Integer downnum) {
		this.downnum = downnum;
	}

	public Integer getPrintnum() {
		return printnum;
	}

	public void setPrintnum(Integer printnum) {
		this.printnum = printnum;
	}

	public Integer getApplynum() {
		return applynum;
	}

	public void setApplynum(Integer applynum) {
		this.applynum = applynum;
	}

	@JsonSerialize(using = DateSerializer.class)
	public Date getInittime() {
		return inittime;
	}

	public void setInittime(Date inittime) {
		this.inittime = inittime;
	}

	@JsonSerialize(using = DateSerializer.class)
	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	@JsonSerialize(using = DateSerializer.class)
	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Short getPlanflag() {
		return planflag;
	}

	public void setPlanflag(Short planflag) {
		this.planflag = planflag;
	}

	public Short getPlanfinishflag() {
		return planfinishflag;
	}

	public void setPlanfinishflag(Short planfinishflag) {
		this.planfinishflag = planfinishflag;
	}

	public Short getSendbackflag() {
		return sendbackflag;
	}

	public void setSendbackflag(Short sendbackflag) {
		this.sendbackflag = sendbackflag;
	}

	public Short getConfirmflag() {
		return confirmflag;
	}

	public void setConfirmflag(Short confirmflag) {
		this.confirmflag = confirmflag;
	}

	public Short getArrangetype() {
		return arrangetype;
	}

	public void setArrangetype(Short arrangetype) {
		this.arrangetype = arrangetype;
	}

	public String getWorkshopcode() {
		return workshopcode;
	}

	public void setWorkshopcode(String workshopcode) {
		this.workshopcode = workshopcode == null ? null : workshopcode.trim();
	}

	public String getWorkshopname() {
		return workshopname;
	}

	public void setWorkshopname(String workshopname) {
		this.workshopname = workshopname == null ? null : workshopname.trim();
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo == null ? null : contractNo.trim();
	}

	public String getSendCode() {
		return sendCode;
	}

	public void setSendCode(String sendCode) {
		this.sendCode = sendCode == null ? null : sendCode.trim();
	}

	public String getSendName() {
		return sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName == null ? null : sendName.trim();
	}

	public Integer getInPnum() {
		return inPnum;
	}

	public void setInPnum(Integer inPnum) {
		this.inPnum = inPnum;
	}

	public Integer getNotRecognisePnum() {
		return notRecognisePnum;
	}

	public void setNotRecognisePnum(Integer notRecognisePnum) {
		this.notRecognisePnum = notRecognisePnum;
	}

	public Integer getScannum() {
		return scannum;
	}

	public void setScannum(Integer scannum) {
		this.scannum = scannum;
	}

	public String getOiBaseFeedState() {
		return oiBaseFeedState;
	}

	public void setOiBaseFeedState(String oiBaseFeedState) {
		this.oiBaseFeedState = oiBaseFeedState == null ? null : oiBaseFeedState
				.trim();
	}
}