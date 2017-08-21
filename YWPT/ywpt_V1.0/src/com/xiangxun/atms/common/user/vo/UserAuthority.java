package com.xiangxun.atms.common.user.vo;

import java.io.Serializable;

/***
 * 用户数据权限
 * @author zhouhaij
 * @May 3, 2013 4:50:22 PM
 */
public class UserAuthority  implements Serializable{
	private static final long serialVersionUID = 3535054754109083867L;

	/**
	 * 用户id
	 */
	private  String userid;
	
	/***
	 * 菜单id
	 */
	private String menuid;
	
	/***
	 * 部门id
	 */
	private String deptid;
	

	/**
	 * 
	 */
	public UserAuthority() {
		super();
	}

	/**
	 * @param userid
	 * @param menuid
	 * @param deptid
	 */
	public UserAuthority(String userid, String menuid, String deptid) {
		super();
		this.userid = userid;
		this.menuid = menuid;
		this.deptid = deptid;
	}

	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * @return the menuid
	 */
	public String getMenuid() {
		return menuid;
	}

	/**
	 * @param menuid the menuid to set
	 */
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	/**
	 * @return the deptid
	 */
	public String getDeptid() {
		return deptid;
	}

	/**
	 * @param deptid the deptid to set
	 */
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	
	
}
