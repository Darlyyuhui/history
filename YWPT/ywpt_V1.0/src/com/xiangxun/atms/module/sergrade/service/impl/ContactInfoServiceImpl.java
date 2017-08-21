package com.xiangxun.atms.module.sergrade.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.sergrade.domain.ContactInfo;
import com.xiangxun.atms.module.sergrade.domain.ContactInfoSearch;
import com.xiangxun.atms.module.sergrade.domain.ContactInfoSearch.Criteria;
import com.xiangxun.atms.module.sergrade.domain.FactoryInfo;
import com.xiangxun.atms.module.sergrade.mapper.ContactInfoMapper;
import com.xiangxun.atms.module.sergrade.service.ContactInfoService;
import com.xiangxun.atms.module.sergrade.service.FactoryInfoService;

/**
 * 服务厂商设备责任人信息业务逻辑接口实现类
 * @author kouyunhao
 *
 */
@Service("contactInfoService")
public class ContactInfoServiceImpl extends AbstractBaseService<ContactInfo, ContactInfoSearch> implements ContactInfoService {

	@Resource
	ContactInfoMapper contactInfoMapper;
	
	@Resource
	FactoryInfoService factoryInfoService;
	
	@Override
	protected BaseMapper<ContactInfo, ContactInfoSearch> getBaseMapper() {
		return contactInfoMapper;
	}

	@Override
	public List<ContactInfo> findByIds(List<String> ids) {
		ContactInfoSearch example = new ContactInfoSearch();
		Criteria criteria = example.createCriteria();
		if(ids != null && ids.size() != 0){
			criteria.andIdIn(ids);
		}
		return contactInfoMapper.selectByExample(example);
	}
	
	/**
	 * 获取运维服务商树结构
	 * @param menuid
	 * @return
	 */
	@Override
	public JsonArray getFactoryJsonArray(String menuid) {
		JsonArray factoryJsonArray = new JsonArray();
		//运维服务商
		List<FactoryInfo> factoryList = factoryInfoService.findAll();
		getFactoryJsonArray(factoryList,factoryJsonArray,menuid);
		return factoryJsonArray;
	}
	
	/**
	 * 运维服务商页面需要的树结构json字符
	 * @param deptId
	 * @return
	 */
	private void getFactoryJsonArray(List<FactoryInfo> factoryList,JsonArray factoryJsonArray,String menuid) {
		if(factoryList == null) return;
		for (FactoryInfo factoryInfo : factoryList) {
			JsonObject json = new JsonObject();
			json.addProperty("id",factoryInfo.getId());
			json.addProperty("name",factoryInfo.getName());
	
			json.addProperty("checked","false");
			factoryJsonArray.add(json);
		}
	}

}
