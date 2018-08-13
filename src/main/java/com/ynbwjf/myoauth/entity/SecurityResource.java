package com.ynbwjf.myoauth.entity;

import java.util.Date;

public class SecurityResource {
    private Long id;

    private String resourceCode;

    private String resourceName;

    private Long parentId;

    private String resourcePath;

    private String resourceUrl;

    private String bz;

    private Boolean menuflag;

    private Boolean openflag;

    private Integer openmode;

    private Boolean leafflag;

    private String iconcls;

    private Boolean commonModule;

    private String tbr;

    private Date tbrq;

    private String xgr;

    private Date xgrq;

    private Boolean dwflag;

    private Boolean swjgflag;

    private Boolean isDelete;

    private Integer pxid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode == null ? null : resourceCode.trim();
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath == null ? null : resourcePath.trim();
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl == null ? null : resourceUrl.trim();
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }

    public Boolean getMenuflag() {
        return menuflag;
    }

    public void setMenuflag(Boolean menuflag) {
        this.menuflag = menuflag;
    }

    public Boolean getOpenflag() {
        return openflag;
    }

    public void setOpenflag(Boolean openflag) {
        this.openflag = openflag;
    }

    public Integer getOpenmode() {
        return openmode;
    }

    public void setOpenmode(Integer openmode) {
        this.openmode = openmode;
    }

    public Boolean getLeafflag() {
        return leafflag;
    }

    public void setLeafflag(Boolean leafflag) {
        this.leafflag = leafflag;
    }

    public String getIconcls() {
        return iconcls;
    }

    public void setIconcls(String iconcls) {
        this.iconcls = iconcls == null ? null : iconcls.trim();
    }

    public Boolean getCommonModule() {
        return commonModule;
    }

    public void setCommonModule(Boolean commonModule) {
        this.commonModule = commonModule;
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

    public Boolean getDwflag() {
        return dwflag;
    }

    public void setDwflag(Boolean dwflag) {
        this.dwflag = dwflag;
    }

    public Boolean getSwjgflag() {
        return swjgflag;
    }

    public void setSwjgflag(Boolean swjgflag) {
        this.swjgflag = swjgflag;
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