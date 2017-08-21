package com.xiangxun.atms.module.gis.domain;

import java.util.ArrayList;
import java.util.List;

public class Module {
	private String id;
	private String name;
	private String url;
	private List<Module> children = new ArrayList<Module>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<Module> getChildren() {
		return children;
	}
	public void setChildren(List<Module> children) {
		this.children = children;
	}
}
