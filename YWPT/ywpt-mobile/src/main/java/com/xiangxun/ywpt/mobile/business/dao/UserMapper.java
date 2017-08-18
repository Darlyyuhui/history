package com.xiangxun.ywpt.mobile.business.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.xiangxun.ywpt.mobile.business.domain.User;

@Mapper
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
	
	/**
	 * 缓存所有数据字典信息
	 * @return
	 */
	List<Map<String, Object>> getAllDic();
	
}
