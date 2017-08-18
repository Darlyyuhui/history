package com.xiangxun.atms.module.base.service;

public interface BaseService {

	/**
	 * 校验编号是否重复
	 * @param tableName  查询的表名
	 * @param codeColumn 编号列名
	 * @param code 要查询的编号值
	 * @return true=不重复 false=重复
	 */
	boolean checkCode(String tableName, String codeColumn, String code);
	
}
