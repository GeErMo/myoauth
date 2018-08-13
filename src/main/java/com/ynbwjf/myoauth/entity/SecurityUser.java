package com.ynbwjf.myoauth.entity;

import java.util.Date;

public class SecurityUser {
    private Long id;

    private String username;

    private String password;

    private String xm;

    private String dh;

    private String dzyx;

    private Long dwId;

    private String dwMc;

    private Long bmId;

    private String bmMc;

    private Long swjgbm;

    private String swjgmc;

    private Boolean accountnonexpired;

    private Boolean accountnonlocked;

    private Boolean credentialsnonexpired;

    private Boolean enabled;

    private String bz;

    private String tbr;

    private Date tbrq;

    private String xgr;

    private Date xgrq;

    private Boolean isDelete;

    private Boolean isLimit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm == null ? null : xm.trim();
    }

    public String getDh() {
        return dh;
    }

    public void setDh(String dh) {
        this.dh = dh == null ? null : dh.trim();
    }

    public String getDzyx() {
        return dzyx;
    }

    public void setDzyx(String dzyx) {
        this.dzyx = dzyx == null ? null : dzyx.trim();
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

    public Boolean getAccountnonexpired() {
        return accountnonexpired;
    }

    public void setAccountnonexpired(Boolean accountnonexpired) {
        this.accountnonexpired = accountnonexpired;
    }

    public Boolean getAccountnonlocked() {
        return accountnonlocked;
    }

    public void setAccountnonlocked(Boolean accountnonlocked) {
        this.accountnonlocked = accountnonlocked;
    }

    public Boolean getCredentialsnonexpired() {
        return credentialsnonexpired;
    }

    public void setCredentialsnonexpired(Boolean credentialsnonexpired) {
        this.credentialsnonexpired = credentialsnonexpired;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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

    public Boolean getIsLimit() {
        return isLimit;
    }

    public void setIsLimit(Boolean isLimit) {
        this.isLimit = isLimit;
    }
}