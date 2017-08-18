package com.xiangxun.epms.mobile.business.service;

import com.xiangxun.epms.mobile.business.domain.User;

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
}
