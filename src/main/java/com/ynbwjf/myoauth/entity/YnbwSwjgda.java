package com.ynbwjf.myoauth.entity;

public class YnbwSwjgda {
    private Long id;

    private String swjgmc;

    private Long parentId;

    private Long swjgbm;

    private String bz;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSwjgmc() {
        return swjgmc;
    }

    public void setSwjgmc(String swjgmc) {
        this.swjgmc = swjgmc == null ? null : swjgmc.trim();
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getSwjgbm() {
        return swjgbm;
    }

    public void setSwjgbm(Long swjgbm) {
        this.swjgbm = swjgbm;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }
}