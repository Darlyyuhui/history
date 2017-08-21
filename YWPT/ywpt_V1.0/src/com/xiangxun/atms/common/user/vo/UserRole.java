package com.xiangxun.atms.common.user.vo;

import java.io.Serializable;

/***
 * 用户角色关系
 * @author zhouhaij
 */
public class UserRole  implements Serializable{
	private static final long serialVersionUID = -1101422859053194786L;
	//用户id
	private String userId;
	//角色id
	private String roleId;
	public UserRole() {
		super();
	}
	/**
	 * @param userId
	 * @param roleId
	 */
	public UserRole(String userId, String roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
}
