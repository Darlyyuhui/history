package com.xiangxun.epms.mobile.business.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.epms.mobile.business.dao.UserMapper;
import com.xiangxun.epms.mobile.business.domain.User;
import com.xiangxun.epms.mobile.business.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	UserMapper userMapper;
	
	@Override
	public User getUserByAccount(String account) {
		List<User> list = userMapper.getUserByAccount(account);
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public void updatePwdByAccount(String pwd, String account) {
		Map<String, Object> args = new HashMap<>();
		args.put("account", account);
		args.put("pwd", pwd);
		userMapper.updatePwdByAccount(args);
	}
}
