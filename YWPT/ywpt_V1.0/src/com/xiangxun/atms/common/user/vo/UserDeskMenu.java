package com.xiangxun.atms.common.user.vo;

import java.io.Serializable;

public class UserDeskMenu  implements Serializable{
	private static final long serialVersionUID = 7332466594747686786L;

	private String id;

    private String menuid;

    private String userid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid == null ? null : menuid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }
}