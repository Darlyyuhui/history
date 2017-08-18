package com.xiangxun.epms.mobile.business.domain;

public class User {
    private String id;

    private String account;

    private String pwd;

    private String name;

    private String disabled;

    private String mobile;

    private String deptId;
    
    private String deptName;

    private String memo;

    private String skin;

    private String iprule;

    private String createby;

    /**
     * @return the value of ID
     */
    public String getId() {
        return id;
    }

    /**
    
     *
     * @param id the value for ID
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * @return the value of ACCOUNT
     */
    public String getAccount() {
        return account;
    }

    /**
    
     *
     * @param account the value for ACCOUNT
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * @return the value of PWD
     */
    public String getPwd() {
        return pwd;
    }

    /**
    
     *
     * @param pwd the value for PWD
     */
    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    /**
     * @return the value of NAME
     */
    public String getName() {
        return name;
    }

    /**
    
     *
     * @param name the value for NAME
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return the value of DISABLED
     */
    public String getDisabled() {
        return disabled;
    }

    /**
    
     *
     * @param disabled the value for DISABLED
     */
    public void setDisabled(String disabled) {
        this.disabled = disabled == null ? null : disabled.trim();
    }

    /**
     * @return the value of MOBILE
     */
    public String getMobile() {
        return mobile;
    }

    /**
    
     *
     * @param mobile the value for MOBILE
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * @return the value of DEPTID
     */
    public String getDeptId() {
        return deptId;
    }

    /**
    
     *
     * @param deptid the value for DEPTID
     */
    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    /**
     * @return the value of MEMO
     */
    public String getMemo() {
        return memo;
    }

    /**
    
     *
     * @param memo the value for MEMO
     */
    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    /**
     * @return the value of SKIN
     */
    public String getSkin() {
        return skin;
    }

    /**
    
     *
     * @param skin the value for SKIN
     */
    public void setSkin(String skin) {
        this.skin = skin == null ? null : skin.trim();
    }

    /**
     * @return the value of IPRULE
     */
    public String getIprule() {
        return iprule;
    }

    /**
    
     *
     * @param iprule the value for IPRULE
     */
    public void setIprule(String iprule) {
        this.iprule = iprule == null ? null : iprule.trim();
    }

    /**
     * @return the value of CREATEBY
     */
    public String getCreateby() {
        return createby;
    }

    /**
    
     *
     * @param createby the value for CREATEBY
     */
    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName==null?null:deptName.trim();
	}
    /**
    
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", account=").append(account);
        sb.append(", pwd=").append(pwd);
        sb.append(", name=").append(name);
        sb.append(", disabled=").append(disabled);
        sb.append(", mobile=").append(mobile);
        sb.append(", deptId=").append(deptId);
        sb.append(", deptName=").append(deptName);
        sb.append(", memo=").append(memo);
        sb.append(", skin=").append(skin);
        sb.append(", iprule=").append(iprule);
        sb.append(", createby=").append(createby);
        sb.append("]");
        return sb.toString();
    }

}