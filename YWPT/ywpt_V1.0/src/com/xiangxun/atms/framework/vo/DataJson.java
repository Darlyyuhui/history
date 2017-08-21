package com.xiangxun.atms.framework.vo;

import java.util.ArrayList;
import java.util.List;

/**
* @author wxm 2011-11-01
* 向页面传递的对象
**/
public class DataJson<T> {
	private boolean success=true;
	private String message;
	private long totalSize;
	private List<T> list;
	private Object data;
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public DataJson(){
		this.totalSize=0;
		this.list = new ArrayList<T>();
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
}

