/**
 * 
 */
package com.xiangxun.atms.common.system.vo;

/**
 * 用户桌面导航栏临时类
 * @author kouyunhao
 * @version 1.0
 * Apr 15, 2013
 */
public class UserDeskMenuBarTemp {
	
	// 路径
	private String content;
	// 名称
	private String name;
	// 图标
	private String picname;
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the picname
	 */
	public String getPicname() {
		return picname;
	}
	/**
	 * @param picname the picname to set
	 */
	public void setPicname(String picname) {
		this.picname = picname;
	}
	/**
	 * @param content
	 * @param name
	 * @param picname
	 */
	public UserDeskMenuBarTemp(String content, String name, String picname) {
		super();
		this.content = content;
		this.name = name;
		this.picname = picname;
	}
	/**
	 * 
	 */
	public UserDeskMenuBarTemp() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
