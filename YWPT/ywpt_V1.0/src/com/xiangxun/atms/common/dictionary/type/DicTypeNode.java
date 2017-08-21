package com.xiangxun.atms.common.dictionary.type;

import java.io.Serializable;

/***
 * 字典类型node
 * 
 * @since 1.0
 * @author zhouhaij 
 * createTime 2013-11-20
 */
public class DicTypeNode implements Serializable {
	private static final long serialVersionUID = -7979440667465618846L;
	/***
	 * 类型编码
	 */
	private String key;
	/***
	 * 名称
	 */
	private String name;
	/***
	 * 生成编码
	 */
	private String gencode;

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the gencode
	 */
	public String getGencode() {
		return gencode;
	}

	/**
	 * @param gencode
	 *            the gencode to set
	 */
	public void setGencode(String gencode) {
		this.gencode = gencode;
	}
}
