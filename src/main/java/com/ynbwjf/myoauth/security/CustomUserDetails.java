package com.ynbwjf.myoauth.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;

/**
 * 自定义用户信息封装类
 */
public class CustomUserDetails implements UserDetails {

    private static final long serialVersionUID = 3373670278460239444L;
    private String username;
    private String password;
    private Set<GrantedAuthority> authoritys;//权限集合
    private boolean accountnonexpired;//账号是否过期
    private boolean accountnonlocked;//账号是否锁定
    private boolean credentialsnonexpired;//账号凭证是否未过期
    private boolean enabled;//账号是否可用
    private String realname;//账号的真实姓名
    private String email;//用户的邮箱
    private Long id; //用户id
    private Long dwId;//服务单位id
    private Long bmId;//部门id
    private Long swjgBm;//税务机关编码
    private String dh;//用户电话
    private String ip;
    private Boolean isLimit;

    public CustomUserDetails() {
    }

    public CustomUserDetails(String username, String password,  Collection<? extends GrantedAuthority>authoritys, boolean accountnonexpired,
                             boolean accountnonlocked, boolean credentialsnonexpired, boolean enabled, String realname, String email,
                             Long id, Long dwId, Long bmId, Long swjgBm,String dh, Boolean isLimit) {
        super();
        if (username == null || "".equals(username) || password == null)
            throw new IllegalArgumentException("Cannot pass null or empty values to constructor-不能通过构造方法");
        this.username = username;
        this.password = password;
        this.authoritys = Collections.unmodifiableSet(sortAuthorities(authoritys));
        this.accountnonexpired = accountnonexpired;
        this.accountnonlocked = accountnonlocked;
        this.credentialsnonexpired = credentialsnonexpired;
        this.enabled = enabled;
        this.realname = realname;
        this.email = email;
        this.id = id;
        this.dwId = dwId;
        this.bmId = bmId;
        this.swjgBm = swjgBm;
        this.dh = dh;
        this.isLimit = isLimit;
        System.out.println("customUserDetails="+this.toString());

    }



    @Override
    public int hashCode() {
        return this.getUsername().hashCode();
    }

    @Override
    public String toString() {
        return this.username;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authoritys;
    }

    @Override
    public String getPassword() {
        return this.getPassword();
    }

    @Override
    public String getUsername() {
        return this.getUsername();
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<GrantedAuthority> getAuthoritys() {
        return authoritys;
    }

    public void setAuthoritys(Set<GrantedAuthority> authoritys) {
        this.authoritys = authoritys;
    }

    public boolean isAccountnonexpired() {
        return accountnonexpired;
    }

    public void setAccountnonexpired(boolean accountnonexpired) {
        this.accountnonexpired = accountnonexpired;
    }

    public boolean isAccountnonlocked() {
        return accountnonlocked;
    }

    public void setAccountnonlocked(boolean accountnonlocked) {
        this.accountnonlocked = accountnonlocked;
    }

    public boolean isCredentialsnonexpired() {
        return credentialsnonexpired;
    }

    public void setCredentialsnonexpired(boolean credentialsnonexpired) {
        this.credentialsnonexpired = credentialsnonexpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDwId() {
        return dwId;
    }

    public void setDwId(Long dwId) {
        this.dwId = dwId;
    }

    public Long getBmId() {
        return bmId;
    }

    public void setBmId(Long bmId) {
        this.bmId = bmId;
    }

    public Long getSwjgBm() {
        return swjgBm;
    }

    public void setSwjgBm(Long swjgBm) {
        this.swjgBm = swjgBm;
    }

    public String getDh() {
        return dh;
    }

    public void setDh(String dh) {
        this.dh = dh;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Boolean getLimit() {
        return isLimit;
    }

    public void setLimit(Boolean limit) {
        isLimit = limit;
    }
    private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities){
        Assert.notNull(authorities,"Cannot pass a null GrantedAuthority collection-不能通过一个空的GrantedAuthority集合");
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<GrantedAuthority>(new AuthorityComparator());
        for (GrantedAuthority grantedAuthority : authorities) {
            Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements-不能包含任何零元素集合");
            sortedAuthorities.add(grantedAuthority);
        }
        return sortedAuthorities;

    }
    private static class AuthorityComparator implements Comparator<GrantedAuthority>,Serializable {

        /**
         *
         */
        private static final long serialVersionUID = 1250105540340434547L;

        @Override
        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            if(g1.getAuthority() == null){
                return 1;
            }
            if(g2.getAuthority() == null){
                return -1;
            }
            return g1.getAuthority().compareTo(g2.getAuthority());
        }

    }
}
