package com.xiangxun.atms.module.sergrade.service;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.common.user.vo.User;
import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.module.sergrade.domain.FactoryContact;
import com.xiangxun.atms.module.sergrade.domain.FactoryContactSearch;

/**
 * 设备责任人与设备信息关联关系业务逻辑接口
 * @author kouyunhao
 *
 */
public interface FactoryContactService extends BaseService<FactoryContact, FactoryContactSearch> {
	
	List<FactoryContact> findDeviceIdList(List<String> contactids);
	
	List<FactoryContact> findByContactId(String contactId);
	
	List<FactoryContact> findByDeviceId(String deviceId);
	
	List<User> findContactList(String deviceId);

	/***
	 * 获取已分配责任设备列表
	 * @param map
	 * @return list
	 */
	List<FactoryContact> getReport(Map<String, Object> map);
}
