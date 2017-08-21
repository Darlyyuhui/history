package com.xiangxun.atms.common.user.service;

import com.google.gson.JsonArray;

public interface SysMobileUserRoleService {

	/**
	 * 获取用户的资源，将查出的信息转成json
	 * @param userId
	 * @return JsonArray对象
	 */
	JsonArray getUserResourceToJson(String userId);
	
}
