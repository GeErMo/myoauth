package com.ynbwjf.myoauth.entity;

import java.util.Date;

public class SecurityRole {
    private Long id;

    private String roleCode;

    private String roleName;

    private String bz;

    private Long dwId;

    private String dwMc;

    private Long swjgbm;

    private String swjgmc;

    private String tbr;

    private Date tbrq;

    private String xgr;

    private Date xgrq;

    private Boolean isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
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

    public Long getSwjgbm() {
        return swjgbm;
    }

    public void setSwjgbm(Long swjgbm) {
        this.swjgbm = swjgbm;
    }

    public String getSwjgmc() {
        return swjgmc;
    }

    public void setSwjgmc(String swjgmc) {
        this.swjgmc = swjgmc == null ? null : swjgmc.trim();
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
}