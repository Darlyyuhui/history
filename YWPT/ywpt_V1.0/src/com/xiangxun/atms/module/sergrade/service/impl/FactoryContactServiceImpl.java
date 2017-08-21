package com.xiangxun.atms.module.sergrade.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.common.user.service.UserService;
import com.xiangxun.atms.common.user.vo.User;
import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.sergrade.domain.ContactInfo;
import com.xiangxun.atms.module.sergrade.domain.FactoryContact;
import com.xiangxun.atms.module.sergrade.domain.FactoryContactSearch;
import com.xiangxun.atms.module.sergrade.mapper.FactoryContactMapper;
import com.xiangxun.atms.module.sergrade.service.ContactInfoService;
import com.xiangxun.atms.module.sergrade.service.FactoryContactService;

/**
 * 设备责任人与设备信息关联关系业务逻辑接口实现类
 * @author kouyunhao
 *
 */
@Service("factoryContactService")
public class FactoryContactServiceImpl extends AbstractBaseService<FactoryContact, FactoryContactSearch> implements FactoryContactService {

	@Resource
	FactoryContactMapper factoryContactMapper;
	
	@Resource
	ContactInfoService contactInfoService;
	
	@Resource
	UserService userService;
	
	@Override
	protected BaseMapper<FactoryContact, FactoryContactSearch> getBaseMapper() {
		return factoryContactMapper;
	}

	@Override
	public List<FactoryContact> findDeviceIdList(List<String> contactids) {
		FactoryContactSearch example = new FactoryContactSearch();
		example.createCriteria().andContactidIn(contactids);
		return factoryContactMapper.selectByExample(example);
	}
	
	@Override
	public List<FactoryContact> findByContactId(String contactId) {
		FactoryContactSearch example = new FactoryContactSearch();
		example.createCriteria().andContactidEqualTo(contactId);
		return factoryContactMapper.selectByExample(example);
	}

	@Override
	public List<FactoryContact> findByDeviceId(String deviceId) {
		FactoryContactSearch example = new FactoryContactSearch();
		example.createCriteria().andDeviceidEqualTo(deviceId);
		return factoryContactMapper.selectByExample(example);
	}

	@Override
	public List<User> findContactList(String deviceId) {
		List<String> idList = new ArrayList<String>();
		List<FactoryContact> ids = this.findByDeviceId(deviceId);
		if(ids != null && ids.size() != 0){
			for(FactoryContact contact : ids){
				ContactInfo cotactinfo = contactInfoService.getById(contact.getContactid());
				if(cotactinfo != null){
					idList.add(cotactinfo.getUserid());
				}
			}
		}
		return userService.findByIds(idList);
	}
	
	/***
	 * 获取已分配责任设备数量
	 * @param map
	 * @return list
	 */
	@Override
	public List<FactoryContact> getReport(Map<String, Object> map) {
		//获取列表
		List<FactoryContact> list = factoryContactMapper.selectList(map);
		return list;
	}

}
