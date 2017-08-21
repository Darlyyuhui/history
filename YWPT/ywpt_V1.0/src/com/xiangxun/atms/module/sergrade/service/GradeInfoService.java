
package com.xiangxun.atms.module.sergrade.service;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.module.sergrade.domain.GradeInfo;
import com.xiangxun.atms.module.sergrade.domain.GradeInfoSearch;

/**
 * 服务级别信息服务
 * @author guikaiping
 * @version 1.0
 */
public interface GradeInfoService extends BaseService<GradeInfo,GradeInfoSearch> {
	/***
	 * 查询服务级别列表信息服务
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param sortType
	 * @param menuid
	 * @return
	 */
	public Page getReport(Map<String, Object> map, int pageNo, int pageSize, String sortType, String menuid);
	
	/***
     * 根据编号获取对象
     * @param model
     * @return GradeInfo
     */
	public List<GradeInfo> getByCode(String code);
	
	/**
	 * 获取所有级别信息
	 * @return
	 */
	public List<GradeInfo> findAll();
}
