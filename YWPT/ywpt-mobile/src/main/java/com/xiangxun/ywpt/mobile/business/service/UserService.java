package com.xiangxun.ywpt.mobile.business.service;

import com.xiangxun.ywpt.mobile.business.domain.User;

public interface UserService {

	/**
	 * 根据账户获取用户信息，登录用
	 * @param account
	 * @return
	 */
	User getUserByAccount(String account);
	
	/**
	 * 修改用户信息
	 * @param user
	 */
	void updatePwdByAccount(String pwd, String account);
	
	/**
	 * 缓存所有数据字典项
	 */
	void initDic();
	
}
