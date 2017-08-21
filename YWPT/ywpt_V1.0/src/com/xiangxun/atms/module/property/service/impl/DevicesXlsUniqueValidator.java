/**
 * 
 */
package com.xiangxun.atms.module.property.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.common.deptment.service.DepartmentService;
import com.xiangxun.atms.common.deptment.vo.Department;
import com.xiangxun.atms.common.dictionary.service.DicService;
import com.xiangxun.atms.common.road.service.RoadInfoService;
import com.xiangxun.atms.common.road.vo.RoadInfo;
import com.xiangxun.atms.common.road.vo.RoadInfoSearch;
import com.xiangxun.atms.framework.compnents.xls.imports.validate.UniqueValidator;
import com.xiangxun.atms.module.property.domain.DeviceCompanyInfo;
import com.xiangxun.atms.module.property.domain.DeviceCompanyInfoSearch;
import com.xiangxun.atms.module.property.domain.DeviceInfo;
import com.xiangxun.atms.module.property.domain.DeviceInfoSearch;
import com.xiangxun.atms.module.property.service.DeviceCompanyInfoService;
import com.xiangxun.atms.module.property.service.DeviceInfoService;

/**
 * 货车信息Xls导入唯一性验证
 * @author kouyunhao
 * @version 1.0
 * Apr 2, 2014
 */
@Service("devicesXlsUniqueValidator")
public class DevicesXlsUniqueValidator implements UniqueValidator {
	
	@Resource
	DeviceInfoService deviceInfoService;
	
	@Resource
	RoadInfoService roadInfoService;
	
	@Resource
	DepartmentService departmentService;
	
	@Resource
	DeviceCompanyInfoService deviceCompanyInfoService;
	
	@Resource
	DicService dicService;

	/* (non-Javadoc)
	 * @see@see com.xiangxun.atms.framework.compnents.xls.imports.validate.UniqueValidator#validate(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean validate(String fieldName, String value) {
		boolean returnFlag = false;
		if(fieldName.equals("device_code")){
			//查询号牌号码是否唯一（库里是否已存在）
			int n = deviceInfoService.countDeviceByCode(value);
			if(n > 0){
				returnFlag = true;
			}
		}else{
			//查询数据字典中是否有对应的编码。
			if(fieldName.equals("device_ip")){
				//查询设备ip
				DeviceInfoSearch deviceInfoSearch = new DeviceInfoSearch();
				deviceInfoSearch.createCriteria().andIpEqualTo(value);
				List<DeviceInfo> selectByExample = deviceInfoService.selectByExample(deviceInfoSearch);
				if(selectByExample != null && selectByExample.size() > 0){
					returnFlag = true;
				}
			}
			if(fieldName.equals("device_road")){
				List<RoadInfo> findByRoadname = roadInfoService.findByRoadname(value);
				if(findByRoadname == null || findByRoadname.isEmpty()){
					returnFlag = true;
				}
			}
			if(fieldName.equals("device_depatment")){
				Department oneByName = departmentService.getOneByName(value);
				if(oneByName == null){
					returnFlag = true;
				}
			}
			if(fieldName.equals("device_business")){
				DeviceCompanyInfoSearch companyInfoSearch = new DeviceCompanyInfoSearch();
				companyInfoSearch.createCriteria().andNameEqualTo(value);
				List<DeviceCompanyInfo> selectByExample2 = deviceCompanyInfoService.selectByExample(companyInfoSearch);
				if(selectByExample2 == null && selectByExample2.isEmpty()){
					returnFlag = true;
				}
			}
		}
		return returnFlag;
	}
	

}
