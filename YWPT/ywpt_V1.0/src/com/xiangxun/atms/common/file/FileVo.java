package com.xiangxun.atms.common.file;

import java.io.Serializable;

public class FileVo implements Serializable {

	private static final long serialVersionUID = -9057783696430978787L;
	String filename;
	String downloadpath;
	String filesize;
	String lastModify;
	//查询条件说明
	String queryInfo;
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return this.filename;
	}

	/**
	 * @param filename
	 *            the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * @return the downloadpath
	 */
	public String getDownloadpath() {
		return this.downloadpath;
	}

	/**
	 * @param downloadpath
	 *            the downloadpath to set
	 */
	public void setDownloadpath(String downloadpath) {
		this.downloadpath = downloadpath;
	}

	/**
	 * @return the filesize
	 */
	public String getFilesize() {
		return this.filesize;
	}

	/**
	 * @param filesize
	 *            the filesize to set
	 */
	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}

	/**
	 * @return the lastModify
	 */
	public String getLastModify() {
		return this.lastModify;
	}

	/**
	 * @param lastModify the lastModify to set
	 */
	public void setLastModify(String lastModify) {
		this.lastModify = lastModify;
	}

	/**
	 * @return the queryInfo
	 */
	public String getQueryInfo() {
		return queryInfo;
	}

	/**
	 * @param queryInfo the queryInfo to set
	 */
	public void setQueryInfo(String queryInfo) {
		this.queryInfo = queryInfo;
	}
	
	

}