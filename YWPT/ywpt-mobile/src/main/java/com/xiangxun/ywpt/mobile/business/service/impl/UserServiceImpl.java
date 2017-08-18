package com.xiangxun.ywpt.mobile.business.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.ywpt.mobile.business.cache.DicCache;
import com.xiangxun.ywpt.mobile.business.dao.UserMapper;
import com.xiangxun.ywpt.mobile.business.domain.Dic;
import com.xiangxun.ywpt.mobile.business.domain.User;
import com.xiangxun.ywpt.mobile.business.service.UserService;

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

	@Override
	public void initDic() {
		List<Map<String, Object>> list = userMapper.getAllDic();
		
		List<Dic> temp = null;
		Dic d = null;
		String key = null;
		for (Map<String, Object> map : list) {
			key = this.objToString(map.get("TYPE"));
			d = new Dic(this.objToString(map.get("CODE")), this.objToString(map.get("NAME")));
			temp = DicCache.DIC_MAP.get(key);
			if (temp == null) {
				temp = new ArrayList<Dic>();
			}
			temp.add(d);
			DicCache.DIC_MAP.put(key, temp);
		}
	}
	
	private String objToString(Object obj) {
		if (obj == null) {
			return null;
		}
		return obj.toString();
	}
	
}
