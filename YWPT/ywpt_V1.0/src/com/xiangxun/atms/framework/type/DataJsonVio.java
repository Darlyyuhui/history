package com.xiangxun.atms.framework.type;

import java.util.ArrayList;
import java.util.List;

/**
* @author lhh
* 违法详情页面封装
* 向页面传递的对象
**/
public class DataJsonVio<T> {
	private boolean success=true;
	private String message;
	private int page;//页数
	private String ids;//本页的id拼接字符串
	private String idsnext;//下一页的id拼接字符串
	private String idslast;//上一页的id拼接字符串
	private String firstId;//分页数据第一条数据id
	private String lastId;//分页数据最后一条数据id
	private long totalSize;
	private long maxIndex;
	private long index;
	private String menuid; 
	private String device_code_name;
	private String carDatetime;
	private String delTime;//违法删除审核删除时间 add by kouyunhao 2014-07-04
	private List<T> list;
	private Object data;
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public DataJsonVio(){
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
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getIdsnext() {
		return idsnext;
	}
	public void setIdsnext(String idsnext) {
		this.idsnext = idsnext;
	}
	public String getIdslast() {
		return idslast;
	}
	public void setIdslast(String idslast) {
		this.idslast = idslast;
	}
	public String getFirstId() {
		return firstId;
	}
	public void setFirstId(String firstId) {
		this.firstId = firstId;
	}
	public String getLastId() {
		return lastId;
	}
	public void setLastId(String lastId) {
		this.lastId = lastId;
	}
	public String getDelTime() {
		return delTime;
	}
	public void setDelTime(String delTime) {
		this.delTime = delTime;
	}
	
	
}

