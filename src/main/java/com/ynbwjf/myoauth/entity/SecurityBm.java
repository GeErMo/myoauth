package com.ynbwjf.myoauth.entity;

import java.util.Date;

public class SecurityBm {
    private Long bmId;

    private String bmMc;

    private Long dwId;

    private String dwMc;

    private Long parentId;

    private String bz;

    private String tbr;

    private Date tbrq;

    private String xgr;

    private Date xgrq;

    private Boolean isDelete;

    private Integer pxid;

    public Long getBmId() {
        return bmId;
    }

    public void setBmId(Long bmId) {
        this.bmId = bmId;
    }

    public String getBmMc() {
        return bmMc;
    }

    public void setBmMc(String bmMc) {
        this.bmMc = bmMc == null ? null : bmMc.trim();
    }

    public Long getDwId() {
        return dwId;
    }

    public void setDwId(Long dwId) {
        this.dwId = dwId;
    }

    public String getDwMc() {
        return dwMc;
    }

    public void setDwMc(String dwMc) {
        this.dwMc = dwMc == null ? null : dwMc.trim();
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }

    public String getTbr() {
        return tbr;
    }

    public void setTbr(String tbr) {
        this.tbr = tbr == null ? null : tbr.trim();
    }

    public Date getTbrq() {
        return tbrq;
    }

    public void setTbrq(Date tbrq) {
        this.tbrq = tbrq;
    }

    public String getXgr() {
        return xgr;
    }

    public void setXgr(String xgr) {
        this.xgr = xgr == null ? null : xgr.trim();
    }

    public Date getXgrq() {
        return xgrq;
    }

    public void setXgrq(Date xgrq) {
        this.xgrq = xgrq;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getPxid() {
        return pxid;
    }

    public void setPxid(Integer pxid) {
        this.pxid = pxid;
    }
}