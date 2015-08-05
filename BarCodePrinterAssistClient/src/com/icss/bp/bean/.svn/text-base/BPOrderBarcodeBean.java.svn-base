package com.icss.bp.bean;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import com.icss.util.date.DateSerializer;

public class BPOrderBarcodeBean {

	private Long obId;

    private String obUuid;

    private Integer obSequence;

    private Date obSortDate;

    private String obCigDeriveCode;

    private String obCustomInfo;

    private String obRetailerCode;

    private String obBcigBarcode;

    private Long obTotalSequence;

    private String obLockStep;

    private String obLockStatus;

    public Long getObId() {
        return obId;
    }

    public void setObId(Long obId) {
        this.obId = obId;
    }

    public String getObUuid() {
        return obUuid;
    }

    public void setObUuid(String obUuid) {
        this.obUuid = obUuid == null ? null : obUuid.trim();
    }

    public Integer getObSequence() {
        return obSequence;
    }

    public void setObSequence(Integer obSequence) {
        this.obSequence = obSequence;
    }

    @JsonSerialize(using = DateSerializer.class)
    public Date getObSortDate() {
        return obSortDate;
    }

    public void setObSortDate(Date obSortDate) {
        this.obSortDate = obSortDate;
    }

    public String getObCigDeriveCode() {
        return obCigDeriveCode;
    }

    public void setObCigDeriveCode(String obCigDeriveCode) {
        this.obCigDeriveCode = obCigDeriveCode == null ? null : obCigDeriveCode.trim();
    }

    public String getObCustomInfo() {
        return obCustomInfo;
    }

    public void setObCustomInfo(String obCustomInfo) {
        this.obCustomInfo = obCustomInfo == null ? null : obCustomInfo.trim();
    }

    public String getObRetailerCode() {
        return obRetailerCode;
    }

    public void setObRetailerCode(String obRetailerCode) {
        this.obRetailerCode = obRetailerCode == null ? null : obRetailerCode.trim();
    }

    public String getObBcigBarcode() {
        return obBcigBarcode;
    }

    public void setObBcigBarcode(String obBcigBarcode) {
        this.obBcigBarcode = obBcigBarcode == null ? null : obBcigBarcode.trim();
    }

    public Long getObTotalSequence() {
        return obTotalSequence;
    }

    public void setObTotalSequence(Long obTotalSequence) {
        this.obTotalSequence = obTotalSequence;
    }

    public String getObLockStep() {
        return obLockStep;
    }

    public void setObLockStep(String obLockStep) {
        this.obLockStep = obLockStep == null ? null : obLockStep.trim();
    }

    public String getObLockStatus() {
        return obLockStatus;
    }

    public void setObLockStatus(String obLockStatus) {
        this.obLockStatus = obLockStatus == null ? null : obLockStatus.trim();
    }
}