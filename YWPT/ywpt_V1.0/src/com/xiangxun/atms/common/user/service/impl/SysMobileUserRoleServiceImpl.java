package com.xiangxun.atms.common.user.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.xiangxun.atms.common.user.dao.SysMobileUserRoleMapper;
import com.xiangxun.atms.common.user.service.SysMobileUserRoleService;
import com.xiangxun.atms.common.system.vo.SystemResource;
import com.xiangxun.atms.framework.util.StringUtils;

///ywpt_v1.0/src/com/xiangxun/atms/common/system/vo/SystemResource.java
@Service
public class SysMobileUserRoleServiceImpl implements SysMobileUserRoleService {

	@Resource
	SysMobileUserRoleMapper sysMobileUserRoleMapper;
	
	@Override
	public JsonArray getUserResourceToJson(String userId) {
		//获取用户拥有权限的菜单资源
		List<SystemResource> list = sysMobileUserRoleMapper.getResourceByUserId(userId);
		
		//转成map  key=parentId
		Map<String, List<SystemResource>> map = new HashMap<String, List<SystemResource>>();
		List<SystemResource> temp = null;
		String key = null;
		for (SystemResource sr : list) {
			key = sr.getParentid();
			temp = map.get(key);
			if (temp == null) {
				temp = new ArrayList<SystemResource>();
			}
			temp.add(sr);
			map.put(key, temp);
		}
		//根节点存在map中的key值
		String rootKey = "0";
		
		List<SystemResource> rootList = map.get(rootKey);
		if (rootList == null) {
			return new JsonArray();
		}
		JsonArray array = new JsonArray();
		
		JsonObject tempObj = null;
		temp = null;
		for (SystemResource sr : rootList) {
			tempObj = new JsonObject();
			tempObj.addProperty("id", sr.getId());
			tempObj.addProperty("name", sr.getName());
			if (StringUtils.isNotEmpty(sr.getContent())) {
				tempObj.addProperty("url", sr.getContent());
			}
			//添加子菜单
			this.addChildren(sr.getId(), map, tempObj);
			array.add(tempObj);
		}
		return array;
	}
	
	/**
	 * 添加子节点
	 * @param key map的key值/未使用
	 * @param map
	 * @param obj
	 */
	private void addChildren(String key, Map<String, List<SystemResource>> map, JsonObject obj) {
		List<SystemResource> list = map.get(key);
		if (list == null) {
			return;
		}
		JsonArray array = new JsonArray();
		JsonObject tempObj = null;
		String type = "";
		for (SystemResource sr : list) {
			type = sr.getType();
			
			tempObj = new JsonObject();
			tempObj.addProperty("id", sr.getId());
			tempObj.addProperty("name", sr.getName());
			if (StringUtils.isNotEmpty(sr.getContent())) {
				tempObj.addProperty("url", sr.getContent());
			}
			//添加子菜单
			this.addChildren(sr.getId(), map, tempObj);
			array.add(tempObj);
		}
		if (type.equals("1")) {
			obj.add("roles", array);
		}else {
			obj.add("children", array);
		}
		
	}


}
