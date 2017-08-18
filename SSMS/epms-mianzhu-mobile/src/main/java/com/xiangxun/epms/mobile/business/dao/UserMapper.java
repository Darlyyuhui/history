package com.xiangxun.epms.mobile.business.dao;

import java.util.List;
import java.util.Map;

import com.xiangxun.epms.mobile.business.domain.User;

public interface UserMapper {

	/**
	 * 根据账户名查找用户信息
	 * @param account
	 * @return
	 */
	List<User> getUserByAccount(String account);
	
	/**
	 * 修改密码
	 * @param args
	 */
	void updatePwdByAccount(Map<String, Object> args);
}
