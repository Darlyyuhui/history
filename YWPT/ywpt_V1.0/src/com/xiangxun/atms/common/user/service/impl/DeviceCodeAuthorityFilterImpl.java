package com.xiangxun.atms.common.user.service.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.xiangxun.atms.common.deptment.vo.Department;
import com.xiangxun.atms.common.system.service.ParamsService;
import com.xiangxun.atms.common.user.service.DeviceCodeAuthorityFilter;
import com.xiangxun.atms.common.user.service.ModuleValidator;
import com.xiangxun.atms.framework.security.OperatorDetails;
import com.xiangxun.atms.framework.security.SpringSecurityUtils;
import com.xiangxun.atms.module.property.domain.DeviceInfo;
import com.xiangxun.atms.module.property.service.DeviceInfoService;

/**
 * <p>业务模块中根据deviceCode来进行数据权限的过滤</p>
 * @author zhouhaij
 * @since 1.0
 * @version
 */
public class DeviceCodeAuthorityFilterImpl extends AbstractAuthorityContext implements DeviceCodeAuthorityFilter{

	List<ModuleValidator>  validators;
	
	@Resource
	DeviceInfoService deviceInfoService;

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.user.service.DeviceCodeAuthorityFilter#hasDeviceCode(java.lang.String)
	 */
	@Override
	public boolean hasDeviceCode(String menuid) {
		boolean result = false;
		for (ModuleValidator validator : validators) {
			result = validator.isDeviceCode(menuid, result);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.user.service.DeviceCodeAuthorityFilter#isEnable(java.lang.String)
	 */
	@Override
	public boolean isEnable(String menuid) {
		boolean result = false;
		for (ModuleValidator validator : validators) {
			result = validator.isEnable(menuid);
		}
		return result;
	}

	/**
	 * @param validators the validators to set
	 */
	public void setValidators(List<ModuleValidator> validators) {
		this.validators = validators;
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.user.service.impl.AbstractAuthorityContext#getBasePerssionSQL()
	 */
	@Override
	protected String getBasePerssionSQL() {
		OperatorDetails details = SpringSecurityUtils.getCurrentUser();
		//获取当前部门的子部门
		List<Department> departs = getAllDepartmentByUserId(details.getDeptId());
		List<String>  ids= new ArrayList<String>();
		Set<String> idSet = new HashSet<String>();
		for (int i = 0; i < departs.size(); i++) {
			idSet.add(departs.get(i).getId());
		}
		for (String string : idSet) {
			ids.add(string);
		}
		if(ids.isEmpty()){
			return null;
		}
		List<String> codes =  deviceInfoService.getCodesByDepartmentId(ids);
		String[] code = toDeviceCodes(codes);
		if(code==null){
			return null;
		}
		
		//修复设备总数小于3时报sql错误的bug
		if(codes.size()<3 && !codes.isEmpty()){
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < codes.size(); i++) {
					sb.append("'"+codes.get(i)+"',");
			}
			return MessageFormat.format(UserAuthoritySQL.BASE_DEVICE_AUTHORITY_SQL,StringUtils.removeEnd(sb.toString(),","));
		}
		
		return MessageFormat.format(UserAuthoritySQL.DEVICE_AUTHORITY_SQL,code[0],code[1],code[2]);
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.user.service.impl.AbstractAuthorityContext#getBasePerssionExtSQL(java.lang.String)
	 */
	@Override
	protected String getBasePerssionExtSQL(String menuid) {
		//key为部门id
		Map<String, List<String>>  map = getUsersAuthorityMap(menuid);
		List<String>  ids= new ArrayList<String>();
		Set<String> idSet = new HashSet<String>();
		for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
			String type = (String) it.next();
			idSet.add(type);
		}
		for (String string : idSet) {
			ids.add(string);
		}
		
		if(ids.isEmpty()){
			return null;
		}
		
		List<String> codes =  deviceInfoService.getCodesByDepartmentId(ids);
		String[] code = toDeviceCodes(codes);
		if(code==null){
			return null;
		}
		//修复设备总数小于3时报sql错误的bug
		if(codes.size()<3 && !codes.isEmpty()){
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < codes.size(); i++) {
					sb.append("'"+codes.get(i)+"',");
			}
			return MessageFormat.format(UserAuthoritySQL.BASE_DEVICE_AUTHORITY_SQL,StringUtils.removeEnd(sb.toString(),","));
		}
		
		return MessageFormat.format(UserAuthoritySQL.DEVICE_AUTHORITY_SQL,code[0],code[1],code[2]);
	}
	
	//将devicode分成3份
	private String[] toDeviceCodes(List<String> list){
		//初始化
		String[] str = new String[]{"a","b","c"};
		//如果该部门没有设备
		if(list.isEmpty()){
			//获取系统参数
			String isBlankDeviceShowAll = ParamsService.SYSTEM_PARAMS.get("isBlankDeviceShowAll");
			if("0".equals(isBlankDeviceShowAll)){
				//不显示任何数据 
				return new String[]{"'000000000000'","'000000000000'","'000000000000'"};
			}else{
				//此语句将会显示所有的数据
				return null;
			}
		}
		Multimap<String, String> multimap = HashMultimap.create();
		for (int i=0;i< list.size();i++) {
			String node = str[i%3];
			multimap.put(node,list.get(i));
		}
		
		for (int i = 0; i < str.length; i++) {
			StringBuilder sb = new StringBuilder();
			for (String string : multimap.get(str[i])) {
				sb.append("'"+string+"',");
			}
			str[i] = StringUtils.removeEnd(sb.toString(),",");
		}
		return str;
	}
	
	
	
	protected List<DeviceInfo> getBaseAuthorityCode() {
		OperatorDetails details = SpringSecurityUtils.getCurrentUser();
		//获取当前部门的子部门
		List<Department> departs = getAllDepartmentByUserId(details.getDeptId());
		List<String>  ids= new ArrayList<String>();
		Set<String> idSet = new HashSet<String>();
		for (int i = 0; i < departs.size(); i++) {
			idSet.add(departs.get(i).getId());
		}
		for (String string : idSet) {
			ids.add(string);
		}
		if(ids.isEmpty()){
			return null;
		}
		List<String> codes =  deviceInfoService.getCodesByDepartmentId(ids);
		List<DeviceInfo> list =new ArrayList<DeviceInfo>();;
		for (String string2 : codes) {
			DeviceInfo deInfo = deviceInfoService.selectDevlistByCode(string2);
			list.add(deInfo);
		}
		
		return list;
	}
	
	protected List<DeviceInfo> getAuthorityCode() {
		OperatorDetails details = SpringSecurityUtils.getCurrentUser();
		//获取当前部门的子部门
		List<Department> departs = getAllDepartmentByUserId(details.getDeptId());
		List<String>  ids= new ArrayList<String>();
		Set<String> idSet = new HashSet<String>();
		for (int i = 0; i < departs.size(); i++) {
			idSet.add(departs.get(i).getId());
		}
		for (String string : idSet) {
			ids.add(string);
		}
		if(ids.isEmpty()){
			return null;
		}
		List<String> codes =  deviceInfoService.getCodesByDepartmentId(ids);
		List<DeviceInfo> list =new ArrayList<DeviceInfo>();;
		for (String string2 : codes) {
			DeviceInfo deInfo = deviceInfoService.selectDevlistByCode(string2);
			list.add(deInfo);
		}
		
		return list;
		
	}
	
	/**
	 * 返回权限设备编号
	 * lhh新加
	 */
	protected List<DeviceInfo> getAuthorityCode(String menuid) {
		//key为部门id
		Map<String, List<String>>  map = getUsersAuthorityMap(menuid);
		List<String>  ids= new ArrayList<String>();
		Set<String> idSet = new HashSet<String>();
		for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
			String type = (String) it.next();
			idSet.add(type);
		}
		for (String string : idSet) {
			ids.add(string);
		}
		
		if(ids.isEmpty()){
			return null;
		}
		
		List<String> codes =  deviceInfoService.getCodesByDepartmentId(ids);
		List<DeviceInfo> list =new ArrayList<DeviceInfo>();;
		for (String string2 : codes) {
			DeviceInfo deInfo = deviceInfoService.selectDevlistByCode(string2);
			list.add(deInfo);
		}
		
		return list;
	}
	
}
