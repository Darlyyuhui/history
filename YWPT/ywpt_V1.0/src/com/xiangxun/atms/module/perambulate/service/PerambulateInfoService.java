package com.xiangxun.atms.module.perambulate.service;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.module.perambulate.domain.Filedescribe;
import com.xiangxun.atms.module.perambulate.domain.PerambulateInfo;
import com.xiangxun.atms.module.perambulate.domain.PerambulateInfoSearch;

/**
 * 巡检模块业务接口
 * @author kouyunhao
 *
 */
public interface PerambulateInfoService  extends BaseService<PerambulateInfo, PerambulateInfoSearch> {

	/***
	 * 导出EXCEL专用
	 * @return
	 */
	public List<PerambulateInfo> selectByExampleDiy(Map<String, Object> searchParams,String sortType);
	
	List getCountByDeviceType(Map<String, Object> map);
	List getCountByUser(Map<String, Object> map);
	
	
	/**
	 * 巡检报告导出获取文件描述
	 */
	public List<Filedescribe> getFileMes(String Bydate); 
}

