package com.xiangxun.atms.module.geoServer.domain;

import java.util.Map;

import net.sf.json.JSONObject;

public class Graphic{

	/**
	 * 几何体
	 */
	private Geometry geo;

	/**
	 * 属性表
	 */
	private Map<String,String> attributes;

	/**
	 * 符号字符串
	 */
	private String symbol;

	public Graphic(){
		
	}

	public Graphic(Geometry geometry,Map<String,String> attr,String symbol){
		this.geo = geometry;
		this.attributes = attr;
		this.symbol = symbol;
	}

	public Geometry getGeo() {
		return geo;
	}

	public void setGeo(Geometry geometry) {
		this.geo = geometry;
	}

	public Map<String,String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String,String> attributes) {
		this.attributes = attributes;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String toString(){
		return "{geo:'"+this.geo.toString()+"',attributes:'"+JSONObject.fromObject(this.attributes).toString()+"',symbol:'"+this.symbol+"'}";
	}

}
