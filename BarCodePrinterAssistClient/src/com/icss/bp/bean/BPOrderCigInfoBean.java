package com.icss.bp.bean;

import java.math.BigDecimal;

public class BPOrderCigInfoBean {

	private Long ociId;

    private String ociUuid;

    private String ociCigBrand;

    private String ociCigTrademark;

    private String ociIsAbnormal;

    private String ociIsPrint;

    private Integer ociOrderNum;

    private BigDecimal ociOrderNum1;

    private BigDecimal ociActualPrice;

    private BigDecimal ociStanderdPrice;

    private String ociLockStep;

    private String ociLockStatus;

    private String ociCustomInfo;

    
    public Long getOciId() {
        return ociId;
    }

    public void setOciId(Long ociId) {
        this.ociId = ociId;
    }

    public String getOciUuid() {
        return ociUuid;
    }

    public void setOciUuid(String ociUuid) {
        this.ociUuid = ociUuid == null ? null : ociUuid.trim();
    }

    public String getOciCigBrand() {
        return ociCigBrand;
    }

    public void setOciCigBrand(String ociCigBrand) {
        this.ociCigBrand = ociCigBrand == null ? null : ociCigBrand.trim();
    }

    public String getOciCigTrademark() {
        return ociCigTrademark;
    }

    public void setOciCigTrademark(String ociCigTrademark) {
        this.ociCigTrademark = ociCigTrademark == null ? null : ociCigTrademark.trim();
    }

    public String getOciIsAbnormal() {
        return ociIsAbnormal;
    }

    public void setOciIsAbnormal(String ociIsAbnormal) {
        this.ociIsAbnormal = ociIsAbnormal == null ? null : ociIsAbnormal.trim();
    }

    public String getOciIsPrint() {
        return ociIsPrint;
    }

    public void setOciIsPrint(String ociIsPrint) {
        this.ociIsPrint = ociIsPrint == null ? null : ociIsPrint.trim();
    }

    public Integer getOciOrderNum() {
        return ociOrderNum;
    }

    public void setOciOrderNum(Integer ociOrderNum) {
        this.ociOrderNum = ociOrderNum;
    }

    public BigDecimal getOciOrderNum1() {
        return ociOrderNum1;
    }

    public void setOciOrderNum1(BigDecimal ociOrderNum1) {
        this.ociOrderNum1 = ociOrderNum1;
    }

    public BigDecimal getOciActualPrice() {
        return ociActualPrice;
    }

    public void setOciActualPrice(BigDecimal ociActualPrice) {
        this.ociActualPrice = ociActualPrice;
    }

    public BigDecimal getOciStanderdPrice() {
        return ociStanderdPrice;
    }

    public void setOciStanderdPrice(BigDecimal ociStanderdPrice) {
        this.ociStanderdPrice = ociStanderdPrice;
    }

    public String getOciLockStep() {
        return ociLockStep;
    }

    public void setOciLockStep(String ociLockStep) {
        this.ociLockStep = ociLockStep == null ? null : ociLockStep.trim();
    }

    public String getOciLockStatus() {
        return ociLockStatus;
    }

    public void setOciLockStatus(String ociLockStatus) {
        this.ociLockStatus = ociLockStatus == null ? null : ociLockStatus.trim();
    }

    public String getOciCustomInfo() {
        return ociCustomInfo;
    }

    public void setOciCustomInfo(String ociCustomInfo) {
        this.ociCustomInfo = ociCustomInfo == null ? null : ociCustomInfo.trim();
    }

	@Override
	public String toString() {
		return "BPOrderCigInfoBean [ociId=" + ociId + ", ociUuid=" + ociUuid
				+ ", ociCigBrand=" + ociCigBrand + ", ociCigTrademark="
				+ ociCigTrademark + ", ociIsAbnormal=" + ociIsAbnormal
				+ ", ociIsPrint=" + ociIsPrint + ", ociOrderNum=" + ociOrderNum
				+ ", ociOrderNum1=" + ociOrderNum1 + ", ociActualPrice="
				+ ociActualPrice + ", ociStanderdPrice=" + ociStanderdPrice
				+ ", ociLockStep=" + ociLockStep + ", ociLockStatus="
				+ ociLockStatus + ", ociCustomInfo=" + ociCustomInfo + "]";
	}
    
}