package com.ynbwjf.myoauth.entity;

public class SecurityRoleToResource {
    private Long id;

    private Long roleId;

    private Long resourceId;

    private Long dwId;

    private Long swjgbm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Long getDwId() {
        return dwId;
    }

    public void setDwId(Long dwId) {
        this.dwId = dwId;
    }

    public Long getSwjgbm() {
        return swjgbm;
    }

    public void setSwjgbm(Long swjgbm) {
        this.swjgbm = swjgbm;
    }
}