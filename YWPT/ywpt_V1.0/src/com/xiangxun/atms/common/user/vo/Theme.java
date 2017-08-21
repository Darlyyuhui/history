package com.xiangxun.atms.common.user.vo;

import java.io.Serializable;

//@Document(collection="SYS_USER_THEME")
public class Theme implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4827360959023897009L;

//	@Indexed
    private String id;

//    @Indexed
    private String userId;

//    @Indexed
    private String theme;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme == null ? null : theme.trim();
    }
}