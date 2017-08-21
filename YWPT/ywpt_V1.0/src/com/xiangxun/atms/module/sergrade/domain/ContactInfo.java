package com.xiangxun.atms.module.sergrade.domain;

import java.io.Serializable;
import com.xiangxun.atms.common.user.vo.User;

//@Document(collection="SERGRADE_CONTACT_INFO")
public class ContactInfo extends User implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4097210771901966796L;

//	@Indexed
    private String id;

//    @Indexed
    private String userid;

//    @Indexed
    private String factoryid;
 
//    @Indexed
    private String hasdel;
    
    //所属服务公司
    private String factoryName;
    //姓名
    private String userName;
    //账号
    private String account;
    //联系方式
    private String mobile;
    //备注
    private String memo;
    //删除标示
    private Boolean disabled;
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getFactoryid() {
        return factoryid;
    }

    public void setFactoryid(String factoryid) {
        this.factoryid = factoryid == null ? null : factoryid.trim();
    }

    public String getHasdel() {
        return hasdel;
    }

    public void setHasdel(String hasdel) {
        this.hasdel = hasdel == null ? null : hasdel.trim();
    }
    
	/**
	 * @return the factoryName
	 */
	public String getFactoryName() {
		return factoryName;
	}

	/**
	 * @param factoryName the factoryName to set
	 */
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @param memo the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * @return the disabled
	 */
	public Boolean getDisabled() {
		return disabled;
	}

	/**
	 * @param disabled the disabled to set
	 */
	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}
    
    
}