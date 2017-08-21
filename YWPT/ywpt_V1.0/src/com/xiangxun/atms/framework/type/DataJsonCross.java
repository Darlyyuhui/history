package com.xiangxun.atms.framework.type;

import java.util.ArrayList;
import java.util.List;

/**
* @author lhh
* 卡口通行车辆
* 向页面传递的对象
**/
public class DataJsonCross<T> {
	private boolean success=true;
	private String message;
	private long totalSize;
	private long maxIndex;
	private long index;
	private String menuid; 
	private String device_code_name;
	private String carDatetime;
	private List<T> list;
	private Object data;
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public DataJsonCross(){
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
	public long getMaxIndex() {
		return maxIndex;
	}
	public void setMaxIndex(long maxIndex) {
		this.maxIndex = maxIndex;
	}
	public long getIndex() {
		return index;
	}
	public void setIndex(long index) {
		this.index = index;
	}
	public String getMenuid() {
		return menuid;
	}
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	public String getDevice_code_name() {
		return device_code_name;
	}
	public void setDevice_code_name(String device_code_name) {
		this.device_code_name = device_code_name;
	}
	public String getCarDatetime() {
		return carDatetime;
	}
	public void setCarDatetime(String carDatetime) {
		this.carDatetime = carDatetime;
	}
	
	
}

