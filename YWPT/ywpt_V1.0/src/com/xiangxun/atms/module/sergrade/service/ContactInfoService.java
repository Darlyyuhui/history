package com.xiangxun.atms.module.sergrade.service;

import java.util.List;

import com.google.gson.JsonArray;
import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.module.sergrade.domain.ContactInfo;
import com.xiangxun.atms.module.sergrade.domain.ContactInfoSearch;

/**
 * 服务厂商设备责任人信息业务逻辑接口
 * @author kouyunhao
 *
 */
public interface ContactInfoService extends BaseService<ContactInfo, ContactInfoSearch> {
	
	List<ContactInfo> findByIds(List<String> ids);
	
	/**
	 * 获取运维服务商树结构
	 * @param menuid
	 * @return
	 */
	public JsonArray getFactoryJsonArray(String menuid);
	
}
