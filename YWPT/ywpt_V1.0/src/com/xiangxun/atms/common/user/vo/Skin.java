package com.xiangxun.atms.common.user.vo;

import java.io.Serializable;


//@Document(collection="SYS_USER_SKIN")
public class Skin implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 589269360499738522L;

//	@Indexed
    private String id;

 //   @Indexed
    private String userId;

//    @Indexed
    private String skin;

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

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin == null ? null : skin.trim();
    }
}