package com.xiangxun.atms.common.user.vo;

import java.io.Serializable;

public class RoleResources  implements Serializable{
	private static final long serialVersionUID = -8039287326588084316L;
	private String roleId;
    private String resourceId;
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId == null ? null : resourceId.trim();
    }
}